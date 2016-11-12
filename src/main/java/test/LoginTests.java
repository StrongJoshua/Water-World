package test;

/**
 * Created by rajmi on 11/12/2016.
 */

import edu.gatech.scrumbags.fxapp.MainFXApplication;
import edu.gatech.scrumbags.model.Authorization;
import edu.gatech.scrumbags.model.User;
import edu.gatech.scrumbags.model.WaterLocation;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;



/** Tests out login and logout methods
 *
 * @author Rishi */
public class LoginTests {

	@Before
	public void createAccounts () {
		User user = new User("North", "Borth", "NorthBorth", Authorization.admin);
		MainFXApplication.client.registerUser (user, "XxSuPeRSecure1337xX");
	}

	@Test
	public void testLogin () {
	MainFXApplication.client.loginUser("NorthBorth", "XxSuPeRSecure1337xX");
	}

	@Test
	public void testLogout () {
		MainFXApplication.client.logout();
	}

	@After
	public void deleteAccounts () {
		MainFXApplication.client.deleteAccount();
	}
}
