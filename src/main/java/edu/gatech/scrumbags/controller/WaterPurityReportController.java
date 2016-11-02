package edu.gatech.scrumbags.controller;

import edu.gatech.scrumbags.fxapp.MainFXApplication;
import edu.gatech.scrumbags.model.WaterPurityCondition;
import edu.gatech.scrumbags.model.WaterPurityReport;
import edu.gatech.scrumbags.model.WaterSourceReport;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.util.Arrays;

/**
 * Controls the flow of information when someone creates a new water purity report.
 *
 * @author gnoziere
 */
public class WaterPurityReportController {

	@FXML private Label errorMessage;
	@FXML private Label locationLabel;
	@FXML private ComboBox<WaterPurityCondition> waterConditionCombo;
	@FXML private TextField virusPPMText;
	@FXML private TextField contaminantPPMText;

	/**
	 * Initializes the WaterPurityReportView as to minimize errors by populating
	 * combo boxes, setting default choices and making the error message invisible.
	 */
	@FXML public void initialize () {
		WaterSourceReport sourceReport = MainFXApplication.getLastUsedSourceReport();
		locationLabel.setText(
			sourceReport.getLocation().getLatitudeString() + ", " + sourceReport.getLocation()
				.getLongitudeString());
		waterConditionCombo
			.setItems(FXCollections.observableArrayList(Arrays.asList(WaterPurityCondition.values())));
		waterConditionCombo.setValue(WaterPurityCondition.Safe);
		setErrorMessage(null);
	}

	/**
	 * Handles the input verification when the user presses the submit button.  Checks that virusPPM and contaminantPPM are filled out correctly.
	 */
	@FXML public void handleSubmitPressed () {
		// trying to get correct GPS coordinates
		boolean successfulParse = false;
		int virusPPM = 0;
		int contaminantPPM = 0;
		try {
			virusPPM = Integer.parseInt(virusPPMText.getText());
			contaminantPPM = Integer.parseInt(contaminantPPMText.getText());
			if (virusPPM < 0) {
				setErrorMessage("Incorrect format! Virus PPM cannot be negative.");
			} else if (contaminantPPM < 0) {
				setErrorMessage("Incorrect format! Contaminant PPM cannot be negative.");
			} else {
				successfulParse = true;
			}
		} catch (NullPointerException e) {
			setErrorMessage("Incorrect format! Please enter values in the form of integers.");
		} catch (NumberFormatException e) {
			setErrorMessage("Incorrect format! Please enter valid integers for PPM values.");
		}
		// submitting report if all data is valid
		if (successfulParse) {
			WaterPurityReport purityReport = new WaterPurityReport(
				MainFXApplication.getLastUsedSourceReport(), waterConditionCombo.getValue(),
				Double.parseDouble(virusPPMText.getText()), Double.parseDouble(virusPPMText.getText()),
				MainFXApplication.userInfo.getFullName());
			//if(MainFXApplication.client.sendPurityReport(purityReport);)
			MainFXApplication.waterReports.add(purityReport);
			MainFXApplication.getLastUsedSourceReport().addPurityReport(purityReport);
			MainFXApplication.loadScene(MainFXApplication.Scenes.main);
		}
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
	 * Sends the user back to the main screen when cancelling.
	 */
	@FXML public void handleCancelPressed () {
		MainFXApplication.loadScene(MainFXApplication.Scenes.main);
	}

}
