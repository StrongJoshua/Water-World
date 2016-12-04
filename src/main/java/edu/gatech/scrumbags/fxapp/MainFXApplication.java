
package edu.gatech.scrumbags.fxapp;

import java.io.IOException;
import java.util.*;

import edu.gatech.scrumbags.controller.MainController;
import edu.gatech.scrumbags.model.Authorization;
import edu.gatech.scrumbags.model.HistoricalReport;
import edu.gatech.scrumbags.model.User;
import edu.gatech.scrumbags.model.WaterLocation;
import edu.gatech.scrumbags.model.WaterPurityReport;
import edu.gatech.scrumbags.model.WaterReport;
import edu.gatech.scrumbags.model.WaterSourceReport;
import edu.gatech.scrumbags.networking.Client;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/** The main class for the FX app.
 *
 * @author Beau Mitchell */
public class MainFXApplication extends Application {
    /** An enum that contains shortcuts to all the views.
     *
     * @author Jan Risse */
    public enum Scenes {
        welcome("/view/WelcomeView.fxml"),
        login("/view/LoginView.fxml"),
        main("/view/MainView.fxml"),
        profile("/view/ProfileView.fxml"),
        registration("/view/RegistrationView.fxml"),
        allReports("/view/AllReportsView.fxml"),
        waterSourceReport("/view/WaterSourceReportView.fxml"),
        waterPurityReport("/view/WaterPurityReportView.fxml"),
        historicalReport("/view/HistoricalReportView.fxml"),
        historicalReportGraph("/view/HistoricalReportGraphView.fxml");

        private String path;

        Scenes (String path) {
            this.path = path;
        }

        /** @return The scene's path. */
        private String getPath () {
            return path;
        }
    }

    public static final String version = "1.0.0.0";

    public static List<WaterReport> waterReports;
    public static List<Integer> years;
    private static MainController mapController;

    private static Stage mainStage;
    public static User userInfo;
    public static Client client;
    public static Map<WaterSourceReport, List<WaterPurityReport>> purityMap = new HashMap<>();

    private static WaterSourceReport lastUsedSourceReport;
    private static HistoricalReport lastUsedHistoricalReport;

    private static boolean shouldReconnect;

    @Override
    public void start (Stage primaryStage) {
        mainStage = primaryStage;
        loadScene(Scenes.welcome);
        primaryStage.show();

        shouldReconnect = true;
        disconnect();

        waterReports = new ArrayList<>();

        years = new ArrayList<>();
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        for (int i = currentYear; i >= 2000; i--) {
            years.add(i);
        }
    }

    @Override
    public void stop () {
        shouldReconnect = false;
        client.quit();
    }

    /** Loads a scene into the FX app.
     *
     * @param scene A constant from the {@link Scenes} enum. */
    public static void loadScene (Scenes scene) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainFXApplication.class.getResource(scene.getPath()));
            Parent root = loader.load();
            mainStage.setTitle("Clean Water by SCRUMbags for CS 2340");
            Scene newScene = new Scene(root);
            mainStage.setScene(newScene);
            newScene.getStylesheets().add("/css/main.css");
            if (scene == Scenes.main) {
                mapController = loader.getController();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Launch the application
     * @param args the arguments passed in
     */
    public static void main (String[] args) {
        launch(args);
    }

    /** Checks the given username and password against all registered users.
     *
     * @param username The username to check.
     * @param password The password to compare.
     * @return The Authorized user object the given username and password correspond to (if one exists). */
    public static User authorizeUser (String username, String password) {
        return client.loginUser(username, password);
    }

    /** See {@link User Authorized(String, String, String, String, Authorization)}
     *
     * @param first User's first name
     * @param last User's last name
     * @param username User's username
     * @param password User's password
     * @param accountType User's account type
     * @return The Authorized user object that was created, if the given username does not already exist. */
    public static User createAccount (String first, String last, String username, String password, Authorization accountType) {
        return client.registerUser(new User(first, last, username, accountType), password);
    }

    /** Sets the last used source report.
     * @param report the last used source report */
    public static void setLastUsedSourceReport (WaterSourceReport report) {
        lastUsedSourceReport = report;
    }

    /** Gets the last used source report.
     * @return the last used source report */
    public static WaterSourceReport getLastUsedSourceReport () {
        return lastUsedSourceReport;
    }

    /** Sets the last used historical report.
     * @param report the last used report */
    public static void setLastUsedHistoricalReport (HistoricalReport report) {
        lastUsedHistoricalReport = report;
    }

    /** Gets the last used historical report.
     * @return the last used report */
    public static HistoricalReport getLastUsedHistoricalReport () {
        return lastUsedHistoricalReport;
    }

    /** This method will reattempt connection if it is ever lost */
    public static void disconnect () {
        if (!shouldReconnect) return;
        userInfo = null;
        client = new Client();
        client.start();
        System.out.println("Reconnecting");
    }

    /** Will log the client out when the logout button is pressed */
    public static void logout () {
        waterReports.clear();
        client.logout();
    }

    /**
     * Sets map center to location
     * @param location the location the map should center to
     */
    public static void setMapLocation (WaterLocation location) {
        if (null == mapController) {
            throw new IllegalStateException("Cannot set Map location before map has been loaded.");
        }
        mapController.setMapCoordinates(location.getLatitude(), location.getLongitude());
    }
}
