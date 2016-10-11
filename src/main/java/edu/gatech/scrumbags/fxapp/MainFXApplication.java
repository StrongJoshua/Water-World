
package edu.gatech.scrumbags.fxapp;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import edu.gatech.scrumbags.model.AccountType;
import edu.gatech.scrumbags.model.Authorized;
import edu.gatech.scrumbags.model.WaterLocation;
import edu.gatech.scrumbags.model.WaterSourceReport;
import edu.gatech.scrumbags.networking.Client;
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
		welcome("/view/WelcomeView.fxml"), login("/view/LoginView.fxml"),
		main("/view/MainView.fxml"), profile("/view/ProfileView.fxml"),
		registration("/view/RegistrationView.fxml"),
		allReports("/view/AllReportsView.fxml"),
		waterSourceReport("/view/WaterSourceReportView.fxml");

		private String path;

		Scenes (String path) {
			this.path = path;
		}

		/** @return The scene's path. */
		private String getPath () {
			return path;
		}
	}

	public static final String version = "0.5.2.1";

	public static ArrayList<Authorized> allUsers;
	public static List<WaterSourceReport> waterReports;

	public static Stage mainStage;
	public static Authorized userInfo;
	public static Client client;

	@Override
	public void start (Stage primaryStage) {
		mainStage = primaryStage;
		loadScene(Scenes.welcome);
		primaryStage.show();
		allUsers = new ArrayList<>();
		createAccount("SCRUMBags", "2340", "SCRUMBags", "2340", AccountType.admin);
		waterReports = new ArrayList<>();
		waterReports.add(new WaterSourceReport(new WaterLocation(0d, 0d),
				"A sample water report.", "Potable", "Bill",
				new Date(1460000000000L)));
		waterReports.add(new WaterSourceReport(new WaterLocation(33.7490, 84.3880),
				"Another sample water report.", "Treatable", "Francis"));
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
		client = new Client();
		launch(args);
	}

	/** Checks the given username and password against all registered users.
	 * @param username The username to check.
	 * @param password The password to compare.
	 * @return The Authorized user object the given username and password correspond to (if one exists). */
	public static Authorized authorizeUser (String username, String password) {
		for (Authorized auth : allUsers) {
			if (auth.authenticate(username, password)) {
				client.loginUser(auth);
				return auth;
			}
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

		client.registerUser(auth);

		return auth;
	}

	public static void addWaterSourceReport(WaterLocation loc, String title,
											String condition, String name, Date d) {
		WaterSourceReport report = new WaterSourceReport(loc, title, condition, name, d);
		waterReports.add(report);
		client.sendWaterReport(report);
	}
}
