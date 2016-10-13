package edu.gatech.scrumbags.controller;

import edu.gatech.scrumbags.fxapp.MainFXApplication;
import edu.gatech.scrumbags.fxapp.MainFXApplication.Scenes;
import javafx.fxml.FXML;

/**
 * The controller for the welcome view, the initial view when opening the app.
 *
 * @author Beau Mitchell
 */
public class WelcomeController {
	@FXML public void initialize () {
		if (MainFXApplication.userInfo != null) {
			MainFXApplication.loadScene(Scenes.main);
		}
	}

	/**
	 * Loads the login view.
	 */
	@FXML public void handleLoginPressed () {
		MainFXApplication.loadScene(Scenes.login);
	}

	/**
	 * Loads the registration view.
	 */
	@FXML public void handleRegisterPressed () {
		MainFXApplication.loadScene(Scenes.registration);
	}
}
