
package edu.gatech.scrumbags.fxapp;

import java.io.IOException;
import java.util.ArrayList;

import edu.gatech.scrumbags.model.AccountType;
import edu.gatech.scrumbags.model.Authorized;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/** The main class for the FX app.
 * @author Beau Mitchell */
public class MainFXApplication extends Application {
	/** An enum that contains shortcuts to all the views.
	 * @author Jan Risse */
	public enum Scenes {
		welcome("/edu/gatech/scrumbags/view/WelcomeView.fxml"), login("/edu/gatech/scrumbags/view/LoginView.fxml"),
		main("/edu/gatech/scrumbags/view/MainView.fxml"), profile("/edu/gatech/scrumbags/view/ProfileView.fxml"),
		registration("/edu/gatech/scrumbags/view/RegistrationView.fxml");

		private String path;

		private Scenes (String path) {
			this.path = path;
		}

		/** @return The scene's path. */
		private String getPath () {
			return path;
		}
	}

	public static final String version = "0.5.0.0";

	public static ArrayList<Authorized> allUsers;

	public static Stage mainStage;
	public static Authorized userInfo;

	@Override
	public void start (Stage primaryStage) {
		mainStage = primaryStage;
		loadScene(Scenes.welcome);
		primaryStage.show();
		allUsers = new ArrayList<>();
		createAccount("SCRUMBags", "2340", "SCRUMBags", "2340", AccountType.admin);
	}

	/** Loads a scene into the FX app.
	 * @param scene A constant from the {@link Scenes} enum. */
	public static void loadScene (Scenes scene) {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainFXApplication.class.getResource(scene.getPath()));
			Parent root = loader.load();
			mainStage.setTitle("Clean Water by SCRUMbags for CS 2340");
			mainStage.setScene(new Scene(root));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main (String[] args) {
		launch(args);
	}

	/** Checks the given username and password against all registered users.
	 * @param username The username to check.
	 * @param password The password to compare.
	 * @return The Authorized user object the given username and password correspond to (if one exists). */
	public static Authorized authorizeUser (String username, String password) {
		for (Authorized auth : allUsers) {
			if (auth.authenticate(username, password)) return auth;
		}
		return null;
	}

	/** See {@link Authorized#Authorized(String, String, String, String, AccountType)}
	 * @return The Authorized user object that was created, if the given username does not already exist. */
	public static Authorized createAccount (String first, String last, String username, String password,
		AccountType accountType) {
		for (Authorized auth : allUsers) {
			if (auth.getUsername().equals(username)) return null;
		}
		Authorized auth = new Authorized(first, last, username, password, accountType);
		allUsers.add(auth);
		return auth;
	}
}
