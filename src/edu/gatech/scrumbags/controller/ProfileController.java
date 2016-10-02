
package edu.gatech.scrumbags.controller;

import edu.gatech.scrumbags.fxapp.MainFXApplication;
import edu.gatech.scrumbags.fxapp.MainFXApplication.Scenes;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class ProfileController {
	@FXML private Label userFirstLast;
	@FXML private Label userAccountType;
	@FXML private Button logout;

	@FXML
	public void initialize () {
		if (MainFXApplication.userInfo == null) {
			MainFXApplication.loadScene(Scenes.welcome);
		}

		userFirstLast.setText(MainFXApplication.userInfo.getFullName());
		String account = MainFXApplication.userInfo.getAccountType().toString();
		userAccountType.setText(account.substring(0, 1).toUpperCase() + account.substring(1));
	}

	@FXML
	public void handleLogoutPressed () {
		MainFXApplication.userInfo = null;
		MainFXApplication.loadScene(Scenes.welcome);
	}
}
