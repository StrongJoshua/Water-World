package edu.gatech.scrumbags.controller;

import edu.gatech.scrumbags.fxapp.MainFXApplication;
import edu.gatech.scrumbags.fxapp.MainFXApplication.Scenes;
import edu.gatech.scrumbags.model.AccountType;
import edu.gatech.scrumbags.model.Authorized;
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

	@FXML public void initialize () {
		Authorized userInfo = MainFXApplication.userInfo;
		if (userInfo.getAccountType() == AccountType.user) {
			waterPurityReportButton.setVisible(false);
			waterPurityReportButton.setManaged(false);
			historicalReportButton.setVisible(false);
			historicalReportButton.setManaged(false);
		} else if (userInfo.getAccountType() == AccountType.worker) {
			historicalReportButton.setVisible(false);
			historicalReportButton.setManaged(false);
		}
	}

	/**
	 * Brings the user to the water source report screen.
	 */
	@FXML public void handleWaterSourceReportPressed () {
		MainFXApplication.loadScene(Scenes.waterSourceReport);
	}

	/**
	 * Brings the user to the water purity report screen.
	 */
	@FXML public void handleWaterPurityReportPressed () {
		//MainFXApplication.loadScene(Scenes.waterPurityReport);
	}

	/**
	 * Brings the user to the historical report screen.
	 */
	@FXML public void handleHistoricalReportPressed () {
		//MainFXApplication.loadScene(Scenes.historicalReport);
	}

	/**
	 * Brings the user to the all reports screen.
	 */
	@FXML public void handleAllReportsPressed () {
		MainFXApplication.loadScene(Scenes.allReports);
	}

	/**
	 * Brings the user to the profle screen.
	 */
	@FXML public void handleProfilePressed () {
		MainFXApplication.loadScene(Scenes.profile);
	}

	/**
	 * Brings the user to the logout screen.
	 */
	@FXML public void handleLogoutPressed () {
		MainFXApplication.loadScene(Scenes.welcome);
	}
}
