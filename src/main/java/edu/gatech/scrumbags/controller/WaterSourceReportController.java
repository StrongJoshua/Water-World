package edu.gatech.scrumbags.controller;

import edu.gatech.scrumbags.fxapp.MainFXApplication;
import edu.gatech.scrumbags.model.WaterType;
import edu.gatech.scrumbags.model.ConditionType;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;

import java.util.Arrays;

/*
Controls the flow of information when someone creates a new water source report.
 */
public class WaterSourceReportController {

	@FXML private Label errorMessage;

	@FXML private TextField latitudeText;
	@FXML private TextField longitudeText;

	@FXML private ComboBox<WaterType> waterTypeCombo;
	@FXML private ComboBox<ConditionType> waterConditionCombo;
	@FXML private TextField waterConditionOtherText;

	@FXML
	public void initialize () {
		waterTypeCombo.setItems(FXCollections.observableArrayList(
				Arrays.asList(WaterType.values())));
		waterConditionCombo.setItems(FXCollections.observableArrayList(
				Arrays.asList(ConditionType.values())));
		waterTypeCombo.setValue(WaterType.Well);
		waterConditionCombo.setValue(ConditionType.Potable);
		setErrorMessage(null);
	}

	@FXML
	public void handleSubmitPressed () {
		// trying to get correct GPS coordinates
		boolean successfulParse = false;
		double latitude;
		double longitude;
		try {
			latitude = Double.parseDouble(latitudeText.getText());
			longitude = Double.parseDouble(longitudeText.getText());
			if (latitude < -90.0 || latitude > 90.0) {
				setErrorMessage("Incorrect format! Latitude value must be " +
						"larger than -90 and less than 90.");
			} else if (longitude < -180.0 || longitude > 180.0) {
				setErrorMessage("Incorrect format! Longitude value must be " +
						"larger than -180 and less than 180.");
			} else {
				successfulParse = true;
			}
		} catch (NullPointerException e) {
			setErrorMessage("Incorrect format! Please enter GPS coordinates " +
					"in the form of doubles.");
		} catch (NumberFormatException e) {
			setErrorMessage("Incorrect format! Please enter valid doubles for " +
					"GPS coordinates.");
		}

		// submitting all information needed for a full water source report
		if (successfulParse) {

		}
	}

	/** Shows the error label and sets the message.
	 * @param msg The message to have the label display. */
	private void setErrorMessage (String msg) {
		if (msg != null) {
			errorMessage.setText(msg);
			errorMessage.setVisible(true);
		} else
			errorMessage.setVisible(false);
	}

	@FXML
	public void handleCancelPressed () {
		MainFXApplication.loadScene(MainFXApplication.Scenes.main);
	}
}
