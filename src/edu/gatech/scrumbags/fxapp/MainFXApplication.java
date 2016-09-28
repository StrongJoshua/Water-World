
package edu.gatech.scrumbags.fxapp;

import java.io.IOException;
import java.util.ArrayList;

import edu.gatech.scrumbags.model.Authorized;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainFXApplication extends Application {
	public enum Scenes {
		welcome("/edu/gatech/scrumbags/view/WelcomeView.fxml"), login("/edu/gatech/scrumbags/view/LoginView.fxml"), loggedIn(
			"/edu/gatech/scrumbags/view/LoggedInView.fxml"), registration("/edu/gatech/scrumbags/view/RegistrationView.fxml");

		private String path;

		private Scenes (String path) {
			this.path = path;
		}

		private String getPath () {
			return path;
		}
	}

	public static final String version = "0.4.3.0";

	public static ArrayList<Authorized> allUsers;

	public static Stage mainStage;
	public static Authorized userInfo;

	@Override
	public void start (Stage primaryStage) {
		mainStage = primaryStage;
		loadScene(Scenes.welcome);
		primaryStage.show();
	}

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

	public static Authorized authorizeUser (String username, String password) {
		for (Authorized auth : allUsers) {
			if (auth.authenticate(username, password)) return auth;
		}
		return null;
	}

	public static Authorized createAccount (String first, String last, String username, String password, String accountType) {
		Authorized auth = new Authorized(first, last, username, password);
		allUsers.add(auth);
		return auth;
	}
}
