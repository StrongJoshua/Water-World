package edu.gatech.scrumbags.controller;

import edu.gatech.scrumbags.fxapp.MainFXApplication;
import edu.gatech.scrumbags.fxapp.MainFXApplication.Scenes;
import edu.gatech.scrumbags.model.WaterReport;
import edu.gatech.scrumbags.model.WaterSourceReport;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

/**
 * The controller for the all reports view, the view containing a list of all water source reports
 *
 * @author Beau Mitchell
 */
public class AllReportsController {
/*	@FXML private Label descriptionLabel;
	@FXML private Label locationLabel;
	@FXML private Label dateLabel;
	@FXML private Label noReportsLabel;
	@FXML private Label userLabel;
	@FXML private Label conditionLabel;*/
	@FXML private ListView<WaterReport> reportList;

	/**
	 * Called when the view is initialized.
	 * Sets up the list view and binds the labels to the selection model.
	 * note: reports from MainFXApplication::waterReports are loaded
	 * here when the view is loaded.
	 */
	@FXML public void initialize () {
		reportList.getItems().addAll(MainFXApplication.waterReports);
		/*if (reportList == null || reportList.getItems().size() == 0) {
			noReportsLabel.setVisible(true);
			descriptionLabel.setVisible(false);
			locationLabel.setVisible(false);
			dateLabel.setVisible(false);
			userLabel.setVisible(false);
			conditionLabel.setVisible(false);
		} else {
			noReportsLabel.setVisible(false);
			reportList.getSelectionModel().select(0);
			//WaterSourceReport selected = reportList.getItems().get(0);
			WaterReport selected = reportList.getItems().get(0);
			descriptionLabel.setText(selected.toString());
			//descriptionLabel.setText(selected.getSourceTypeDescription());
			//locationLabel.setText(selected.getLocationString());
			dateLabel.setText(selected.getDateString());
			//conditionLabel.setText(selected.getSourceConditionDescription());
			conditionLabel.setText(selected.toString());
			userLabel.setText("Submitted by " + selected.getSubmitterName());
			descriptionLabel.setVisible(true);
			locationLabel.setVisible(true);
			dateLabel.setVisible(true);
			userLabel.setVisible(true);
			conditionLabel.setVisible(true);
			reportList.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
				//descriptionLabel.setText(newValue.getSourceTypeDescription());
				descriptionLabel.setText(newValue.toString());
				locationLabel.setText(newValue.getLocationString());
				dateLabel.setText(newValue.getDateString());
				//conditionLabel.setText(newValue.getSourceConditionDescription());
				conditionLabel.setText(newValue.toString());
				userLabel.setText("Submitted by " + newValue.getSubmitterName());
			});
		}*/
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
	 * TODO in M7 (map not yet implemented)
	 */
	@FXML public void handleViewPressed () {
		MainFXApplication.loadScene(Scenes.main);
		MainFXApplication.setMapLocation(reportList.getSelectionModel().getSelectedItem().getLocation());
	}
}
