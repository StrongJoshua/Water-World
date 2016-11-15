package test;

/**
 * Created by rajmi on 11/12/2016.
 */

import edu.gatech.scrumbags.fxapp.MainFXApplication;
import edu.gatech.scrumbags.model.Authorization;
import edu.gatech.scrumbags.model.User;
import edu.gatech.scrumbags.model.WaterLocation;
import edu.gatech.scrumbags.networking.Client;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;



/** Tests out login and logout methods
 *
 * @author Rishi */
public class LoginTests {
	Client client;
	String username = "NorthBorth";
	String password = "XxSuPeRSecure1337xX";

	@Before
	public void createAccounts () {
		client = new Client();
		client.start();
		while (!client.isConnected()) {
			try {
				Thread.sleep(5);
			} catch (Exception e) {
			}
		}
		User user = new User("North", "Borth", username, Authorization.admin);
		client.jUnitRegister (username, password);
	}

	@Test
	public void testIncorrectUsername () {
		for(int i = 0; i < 100; i++) {
			User user = client.loginUser(randomUsername(), password);
			assertTrue(user == null);
		}
		client.logout();
	}

	@Test
	public void testIncorrectPassword () {
		for(int i = 0; i < 100; i++) {
			User user = client.loginUser(username, randomUsername());
			assertTrue(user == null);
		}
		client.logout();
	}

	@Test
	public void testCorrectLogin () {
		User user = client.loginUser(username, password);
		assertTrue(client.isLoggedIn());
		assertTrue(user != null);
		client.logout();
	}

	@Test
	public void testCaseInsensitiveUsername () {
		User user = client.loginUser(username.toLowerCase(), password);
		assertTrue(client.isLoggedIn());
		assertTrue(user != null);
		client.logout();
	}
	@Test
	public void testCaseSensitivePassword () {
		User user = client.loginUser(username, password.toLowerCase());
		assertTrue(user == null);
		client.logout();
	}
	@Test
	public void testLogout () {
		client.loginUser(username,password);
		client.logout();
		assertFalse(client.isLoggedIn());
	}

	@After
	public void deleteAccounts () {
		client.jUnitDelete();
		client.quit();
		while (client.isConnected()) {
			try {
				Thread.sleep(5);
			} catch (Exception e) {
			}
		}
	}

	private String randomUsername () {
		return "jUnit-" + (int)(Math.random() * 10000);
	}
}
