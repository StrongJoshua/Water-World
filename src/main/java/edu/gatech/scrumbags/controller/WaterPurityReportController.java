package edu.gatech.scrumbags.controller;

import com.lynden.gmapsfx.javascript.object.LatLong;
import edu.gatech.scrumbags.fxapp.MainFXApplication;
import edu.gatech.scrumbags.model.*;
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
		locationLabel.setText(sourceReport.getLocation().getLatitudeString() + ", " + sourceReport.getLocation().getLongitudeString());
		waterConditionCombo.setItems(FXCollections.observableArrayList(Arrays.asList(WaterPurityCondition.values())));
		waterConditionCombo.setValue(WaterPurityCondition.Safe);
		setErrorMessage(null);
	}

	/**
	 * Handles the input verification when the user presses the submit button
	 * Checks that latitude and longitude values are acceptable and pulls the rest
	 * of the needed information from the user or local time.
	 */
	@FXML public void handleSubmitPressed () {
		if (virusPPMText.getText() == null || contaminantPPMText.getText() == null) {
			setErrorMessage("Must enter a value for Virus PPM and Contaminant PPM");
		} else if (waterConditionCombo.getValue() == null) {
			setErrorMessage("Must choose a water condition");
		} else {
			MainFXApplication.waterReports.add(new WaterPurityReport(MainFXApplication.getLastUsedSourceReport(), waterConditionCombo.getValue(), Double.parseDouble(virusPPMText.getText()), Double.parseDouble(virusPPMText.getText()), MainFXApplication.userInfo.getFullName()));
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
