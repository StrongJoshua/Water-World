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

	@FXML private Label latitudeText;
	@FXML private Label longitudeText;

	@FXML private ComboBox<WaterPurityCondition> waterConditionCombo;
	@FXML private TextField virusPPMText;
	@FXML private TextField contaminantPPMText;

	/**
	 * Initializes the WaterPurityReportView as to minimize errors by populating
	 * combo boxes, setting default choices and making the error message invisible.
	 */
	@FXML public void initialize () {
		WaterLocation location = MainFXApplication.getLastUsedLocation();
		latitudeText.setText(location.getLatitudeString());
		longitudeText.setText(location.getLongitudeString());
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
		// trying to get correct GPS coordinates
		boolean successfulParse = false;
		double latitude = 0.0;
		double longitude = 0.0;
		try {
			latitude = Double.parseDouble(latitudeText.getText());
			longitude = Double.parseDouble(longitudeText.getText());
			if (latitude < -90.0 || latitude > 90.0) {
				setErrorMessage("Incorrect format! Latitude value must be " + "larger than -90 and less than 90.");
			} else if (longitude < -180.0 || longitude > 180.0) {
				setErrorMessage("Incorrect format! Longitude value must be " + "larger than -180 and less than 180.");
			} else {
				successfulParse = true;
			}
		} catch (NullPointerException e) {
			setErrorMessage("Incorrect format! Please enter GPS coordinates " + "in the form of doubles.");
		} catch (NumberFormatException e) {
			setErrorMessage("Incorrect format! Please enter valid doubles for " + "GPS coordinates.");
		}

		// submitting all information needed for a full water source report
		if (successfulParse) {
//			MainFXApplication.waterReports.add(
//				new WaterSourceReport(new WaterLocation(latitude, longitude), waterTypeCombo.getValue(),
//					waterConditionCombo.getValue(), MainFXApplication.userInfo.getFullName()));
//			MainFXApplication.loadScene(MainFXApplication.Scenes.main);
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
