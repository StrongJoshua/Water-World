package edu.gatech.scrumbags.controller;

import edu.gatech.scrumbags.fxapp.MainFXApplication;
import edu.gatech.scrumbags.model.HistoricalReport;
import edu.gatech.scrumbags.model.HistoricalType;
import edu.gatech.scrumbags.model.WaterPurityCondition;
import edu.gatech.scrumbags.model.WaterSourceReport;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

import java.util.Arrays;

/**
 * The controller for the historical report view.
 *
 * @author Kevin Lun
 */
public class HistoricalReportController {

	@FXML private Label errorMessage;
	@FXML private Label locationLabel;
	@FXML private ComboBox<HistoricalType> typeComboBox;
	@FXML private ComboBox<Integer> yearComboBox;

	/**
	 * Initializes the HistoricalReportView with location of source report and years from 1990 to present.
	 */
	@FXML public void initialize () {
		WaterSourceReport sourceReport = MainFXApplication.getLastUsedSourceReport();
		locationLabel.setText(
			sourceReport.getLocation().getLatitudeString() + ", " + sourceReport.getLocation()
				.getLongitudeString());
		typeComboBox.setItems(FXCollections.observableArrayList(Arrays.asList(HistoricalType.values())));
		typeComboBox.setValue(HistoricalType.Virus);
		yearComboBox.setItems(FXCollections.observableArrayList(MainFXApplication.years));
        yearComboBox.setValue(MainFXApplication.years.get(0));
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
	 * Handles the input verification when the user presses the submit button.  Checks that something is chosen for yearComboBox.
	 */
	@FXML public void handleSubmitPressed () {
		if (yearComboBox.getValue() == null) {
			setErrorMessage("Must choose a year.");
		} else if (typeComboBox.getValue().equals(HistoricalType.Virus)){
            MainFXApplication.setLastUsedHistoricalReport(new HistoricalReport(
                    MainFXApplication.getLastUsedSourceReport(), HistoricalType.Virus,
                    yearComboBox.getValue()));
			MainFXApplication.loadScene(MainFXApplication.Scenes.historicalReportGraph);
		} else if (typeComboBox.getValue().equals(HistoricalType.Contaminant)) {
            MainFXApplication.setLastUsedHistoricalReport(new HistoricalReport(
                    MainFXApplication.getLastUsedSourceReport(), HistoricalType.Contaminant,
                    yearComboBox.getValue()));
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
