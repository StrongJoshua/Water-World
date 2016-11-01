package edu.gatech.scrumbags.controller;

import edu.gatech.scrumbags.fxapp.MainFXApplication;
import edu.gatech.scrumbags.model.WaterSourceReport;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

/**
 * Created by klun on 10/31/2016.
 */
public class HistoricalReportController {

	@FXML private Label errorMessage;
	@FXML private Label locationLabel;
	@FXML private ComboBox<Integer> yearComboBox;

	/**
	 * Initializes the WaterSourceReportView as to minimize errors by populating
	 * combo boxes, setting default choices and making the error message invisible.
	 */
	@FXML public void initialize () {
		WaterSourceReport sourceReport = MainFXApplication.getLastUsedSourceReport();
		locationLabel.setText(
			sourceReport.getLocation().getLatitudeString() + ", " + sourceReport.getLocation()
				.getLongitudeString());
		yearComboBox.setItems(FXCollections.observableArrayList(MainFXApplication.years));
		setErrorMessage(null);
	}

	/**
	 * Shows the error label and sets the message to whatever is inputted or null.
	 *
	 * @param msg The message to have the label display.
	 */
	private void setErrorMessage (String msg) {
		if (msg != null) {
			errorMessage.setText(msg);
			errorMessage.setVisible(true);
		} else
			errorMessage.setVisible(false);
	}

	/**
	 * Handles the input verification when the user presses the submit button
	 * Checks that latitude and longitude values are acceptable and pulls the rest
	 * of the needed information from the user or local time.
	 */
	@FXML public void handleSubmitPressed () {
		if (yearComboBox.getValue() == null) {
			setErrorMessage("Must choose a year.");
		} else {
			MainFXApplication.loadScene(MainFXApplication.Scenes.historicalReportGraph);
		}
	}

	/**
	 * Sends the user back to the main screen when cancelling.
	 */
	@FXML public void handleCancelPressed () {
		MainFXApplication.loadScene(MainFXApplication.Scenes.main);
	}
}
