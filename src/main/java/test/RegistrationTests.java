
package test;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import edu.gatech.scrumbags.networking.Client;
import edu.gatech.scrumbags.networking.messages.Message;

/** Class that tests interaction between the client and server for registration.
 * @author Jan Risse */
public class RegistrationTests {
    Client client;

    @Before
    public void initialize () {
        client = new Client();
        client.start();
        while (!client.isConnected()) {
            try {
                Thread.sleep(5);
            } catch (Exception ignored) {
            }
        }
    }

    @After
    public void dispose () {
        client.quit();
        while (client.isConnected()) {
            try {
                Thread.sleep(5);
            } catch (Exception ignored) {
            }
        }
    }

    @Test
    public void receivesCorrectMessageReturnType () {
        assertEquals("Message returned type should be 'userInfo'.", Message.MessageType.userInfo,
            client.jUnitRegister("", "").getType());
    }

    @Test
    public void testNoUsernameRegister () {
        checkFailed("Should not be able to register without a username.", "", "password");
    }

    @Test
    public void testUsernameTooShort () {
        checkFailed("Username must be 6 or more characters.", "jUnit", "password");
    }

    @Test
    public void testUsernameTooLong () {
        checkFailed("Username must be 20 or fewer characters.", "jUnit1jUnit2jUnit3jUnit4", "password");
    }

    @Test
    public void testNoPasswordRegister () {
        checkFailed("Should not be able to register without a password.", randomUsername(), "");
    }

    @Test
    public void testPasswordTooShort () {
        checkFailed("Password must be 6 or more characters.", randomUsername(), "pass");
    }

    @Test
    public void testPasswordTooLong () {
        checkFailed("Password must be 40 or fewer characters.", randomUsername(), "12345678901234567890123456789012345678901");
    }

    @Test
    public void testExistingUsername () {
        String username = randomUsername();
        client.jUnitRegister(username, "password");
        client.logout();
        checkFailed("Should not be able to register account with existing username.", username, "password");
        client.loginUser(username, "password");
        client.jUnitDelete();
    }

    @Test
    public void testSuccessfulRegistration () {
        assertEquals("Valid credentials shouldn't fail.", 6,
            client.jUnitRegister(randomUsername(), "password").getPayload().length);
        client.jUnitDelete();
    }

    private void checkFailed (String message, String username, String password) {
        assertEquals(message, 0, client.jUnitRegister(username, password).getPayload().length);
    }

    private String randomUsername () {
        return "jUnit-" + (int)(Math.random() * 10000);
    }
}
