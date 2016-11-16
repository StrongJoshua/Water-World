package edu.gatech.scrumbags.controller;

import edu.gatech.scrumbags.fxapp.MainFXApplication;
import edu.gatech.scrumbags.fxapp.MainFXApplication.Scenes;
import edu.gatech.scrumbags.model.WaterReport;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;

/**
 * The controller for the all reports view, the view containing a list of all water reports
 *
 * @author Beau Mitchell
 */
public class AllReportsController {
	/*
	 * @FXML private Label descriptionLabel;
	 *
	 * @FXML private Label locationLabel;
	 *
	 * @FXML private Label dateLabel;
	 *
	 * @FXML private Label noReportsLabel;
	 *
	 * @FXML private Label userLabel;
	 *
	 * @FXML private Label conditionLabel;
	 */
	@FXML private ListView<WaterReport> reportList;

	/**
	 * Called when the view is initialized. Sets up the list view and binds the labels to the selection model
	 * note: reports from MainFXApplication::waterReports are loaded here when the view is loaded.
	 */
	@FXML public void initialize () {
		reportList.getItems().addAll(MainFXApplication.waterReports);
		if (MainFXApplication.userInfo != null) {
			MainFXApplication.loadScene(Scenes.main);
		}
	}

	/**
	 * Returns to the main view when back button is pressed.
	 */
	@FXML public void handleBackPressed () {
		MainFXApplication.loadScene(Scenes.main);
	}

	/**
	 * Centers the map on this water source and returns to the map view
	 */
	@FXML public void handleViewPressed () {
		MainFXApplication.loadScene(Scenes.main);
		MainFXApplication.setMapLocation(reportList.getSelectionModel().getSelectedItem().getLocation());
	}
}
