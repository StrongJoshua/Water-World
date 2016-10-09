
package edu.gatech.scrumbags.controller;

import edu.gatech.scrumbags.fxapp.MainFXApplication;
import edu.gatech.scrumbags.fxapp.MainFXApplication.Scenes;
import edu.gatech.scrumbags.model.WaterSourceReport;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.fxml.FXML;

/** The controller for the all reports view, the view containing a list of all water source reports
 * @author Beau Mitchell */
public class AllReportsController {
	@FXML private Label descriptionLabel;
	@FXML private Label locationLabel;
	@FXML private Label dateLabel;
	@FXML private Label noReportsLabel;
	@FXML private ListView<WaterSourceReport> reportList;

	/**
	 * Called when the view is initialized.
	 * Sets up the list view and binds the labels to the selection model.
	 */
	@FXML
	public void initialize() {
		reportList.getItems().addAll(MainFXApplication.waterReports);
		if (reportList == null || reportList.getItems().size() == 0) {
			noReportsLabel.setVisible(true);
			descriptionLabel.setVisible(false);
			locationLabel.setVisible(false);
			dateLabel.setVisible(false);
		} else {
			noReportsLabel.setVisible(false);
			reportList.getSelectionModel().select(0);
			WaterSourceReport selected = reportList.getItems().get(0);
			descriptionLabel.setText(selected.getSourceDescription());
			locationLabel.setText(selected.getLocation().toString());
			dateLabel.setText(selected.getDateString());
			descriptionLabel.setVisible(true);
			locationLabel.setVisible(true);
			dateLabel.setVisible(true);
			reportList.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
                descriptionLabel.setText(newValue.getSourceDescription());
                locationLabel.setText(newValue.getLocation().toString());
                dateLabel.setText(newValue.getDateString());
            });
		}
		if (MainFXApplication.userInfo != null) {
			MainFXApplication.loadScene(Scenes.main);
		}
	}

	/** Returns to the main view when back button is pressed. */
	@FXML
	public void handleBackPressed () {
		MainFXApplication.loadScene(Scenes.main);
	}

	/**
	 * Centers the map on this water source and returns to the map view
	 * TODO not yet implemented
	 */
	@FXML
	public void handleViewPressed () {
		MainFXApplication.loadScene(Scenes.main);
	}
}
