package edu.gatech.scrumbags.fxapp;

import edu.gatech.scrumbags.controller.MainController;
import edu.gatech.scrumbags.model.*;
import edu.gatech.scrumbags.networking.Client;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * The main class for the FX app.
 *
 * @author Beau Mitchell
 */
public class MainFXApplication extends Application {
	/**
	 * An enum that contains shortcuts to all the views.
	 *
	 * @author Jan Risse
	 */
	public enum Scenes {
		welcome("/view/WelcomeView.fxml"), login("/view/LoginView.fxml"), main("/view/MainView.fxml"), profile(
			"/view/ProfileView.fxml"), registration("/view/RegistrationView.fxml"), allReports(
			"/view/AllReportsView.fxml"), waterSourceReport("/view/WaterSourceReportView.fxml"),
			waterPurityReport("view/WaterPurityReportView.fxml");

		private String path;

		Scenes (String path) {
			this.path = path;
		}

		/**
		 * @return The scene's path.
		 */
		private String getPath () {
			return path;
		}
	}

	public static final String version = "0.6.1.0";

	public static ArrayList<User> allUsers;
	public static List<WaterReport> waterReports;
	public static MainController mapController;

	public static Stage mainStage;
	public static User userInfo;
	public static Client client;
	
	private static boolean shouldReconnect;

	@Override public void start (Stage primaryStage) {
		mainStage = primaryStage;
		shouldReconnect = true;
		disconnect();
		loadScene(Scenes.welcome);
		primaryStage.show();
		allUsers = new ArrayList<>();
		//createAccount("SCRUMBags", "2340", "SCRUMBags", "2340", Authorization.admin);
		waterReports = new ArrayList<>();
		//waterReports.add(new WaterSourceReport(new WaterLocation(0d, 0d), WaterType.Other, WaterCondition.Treatable_Muddy, "Bill", new Date(1460000000000L)));
		waterReports.add(
			new WaterSourceReport(new WaterLocation(33.7490, -84.3880), WaterType.Bottled, WaterCondition.Treatable_Clear,
				"Francis"));
	}
	
	@Override
	public void stop() {
		shouldReconnect = false;
		client.quit();
	}


	/**
	 * Loads a scene into the FX app.
	 *
	 * @param scene A constant from the {@link Scenes} enum.
	 */
	public static void loadScene (Scenes scene) {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainFXApplication.class.getResource(scene.getPath()));
			Parent root = loader.load();
			mainStage.setTitle("Clean Water by SCRUMbags for CS 2340");
			mainStage.setScene(new Scene(root));
			if (scene == Scenes.main) {
				mapController = loader.getController();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main (String[] args) {
		launch(args);
	}

	/**
	 * Checks the given username and password against all registered users.
	 *
	 * @param username The username to check.
	 * @param password The password to compare.
	 * @return The Authorized user object the given username and password correspond to (if one exists).
	 */
	public static User authorizeUser (String username, String password) {
		return client.loginUser(username, password);
	}

	/**
	 * See {@link User Authorized(String, String, String, String, Authorization)}
	 *
	 * @return The Authorized user object that was created, if the given username does not already exist.
	 */
	public static User createAccount (String first, String last, String username, String password, Authorization accountType) {
		for (User auth : allUsers) {
			if (auth.getUsername().equals(username))
				return null;
		}
		User auth = new User(first, last, username, accountType);
		allUsers.add(auth);

		client.registerUser(auth, password);

		return auth;
	}

	/**
	 * Saves water report
	 * @param loc Water location
	 * @param title report title
	 * @param condition water condition
	 * @param name if other
	 */
	//public static void addWaterSourceReport (WaterLocation loc, WaterType title, WaterCondition condition, String name, Date d) {
	public static void addWaterSourceReport (WaterLocation loc, WaterType title, WaterCondition condition, String name) {
		WaterSourceReport report = new WaterSourceReport(loc, title, condition, name);
		waterReports.add(report);
		client.sendWaterReport(report);
	}

	/**
	 * This method will reattempt connection if it is ever lost
	 */
	public static void disconnect () {
		if(!shouldReconnect) return;
		userInfo = null;
		client = new Client();
		client.start();
		System.out.println("Reconnecting");
	}

	/**
	 * Will log the client out when the logout button is pressed
	 */
	public static void logout()
	{
		client.logout();
	}

	public static void setMapLocation(WaterLocation location) {
		if (null == mapController) {
			throw new IllegalStateException("Cannot set Map location before map has been loaded.");
		}
		mapController.setMapCoords(location.getLatitude(), location.getLongitude());
	}
 }
