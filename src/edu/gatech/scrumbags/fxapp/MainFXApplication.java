package edu.gatech.scrumbags.fxapp;

import java.io.IOException;
import java.net.URL;

import edu.gatech.scrumbags.model.User;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainFXApplication extends Application {
	public enum Scenes {
		welcome("/edu/gatech/scrumbags/view/WelcomeView.fxml"), login(
				"/edu/gatech/scrumbags/view/LoginView.fxml"), loggedIn("/edu/gatech/scrumbags/view/LoggedInView.fxml");

		private String path;

		private Scenes(String path) {
			this.path = path;
		}

		private String getPath() {
			return path;
		}
	}

	public static final String version = "0.2.0";

	public static Stage mainStage;
	public static User userInfo;

	@Override
	public void start(Stage primaryStage) {
		mainStage = primaryStage;
		loadScene(Scenes.welcome);
		primaryStage.show();
	}

	public static void loadScene(Scenes scene) {
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

	public static void main(String[] args) {
		launch(args);
	}
}
