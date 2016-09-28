
package edu.gatech.scrumbags.controller;

import edu.gatech.scrumbags.fxapp.MainFXApplication;
import edu.gatech.scrumbags.fxapp.MainFXApplication.Scenes;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class LoggedInController {
	@FXML private Button logout;

	@FXML
	public void initialize () {
		if (MainFXApplication.userInfo == null) {
			MainFXApplication.loadScene(Scenes.welcome);
		}
	}

	@FXML
	public void handleLogoutPressed () {
		MainFXApplication.userInfo = null;
		MainFXApplication.loadScene(Scenes.welcome);
	}
}
