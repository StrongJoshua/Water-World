package edu.gatech.scrumbags.controller;

import edu.gatech.scrumbags.fxapp.MainFXApplication;
import edu.gatech.scrumbags.fxapp.MainFXApplication.Scenes;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

/**
 * The controller for the main view.
 *
 * @author Kevin Lun
 */
public class MainController {
	@FXML private Button waterSourceReportButton;
	@FXML private Button waterPurityReportButton;
	@FXML private Button historicalReportButton;
	@FXML private Button allReportsButton;
	@FXML private Button profileButton;
	@FXML private Button logoutButton;

	@FXML public void handleWaterSourceReportPressed () {
		//MainFXApplication.loadScene(Scenes.waterSourceReport);
	}

	@FXML public void handleWaterPurityReportPressed () {
		//MainFXApplication.loadScene(Scenes.waterPurityReport);
	}

	@FXML public void handleHistoricalReportPressed () {
		//MainFXApplication.loadScene(Scenes.historicalReport);
	}

	@FXML public void handleAllReportsPressed () {
		//MainFXApplication.loadScene(Scenes.allReports);
	}

	@FXML public void handleProfilePressed () {
		MainFXApplication.loadScene(Scenes.profile);
	}

	@FXML public void handleLogoutPressed () {
		MainFXApplication.loadScene(Scenes.welcome);
	}
}
