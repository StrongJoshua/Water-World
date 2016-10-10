package edu.gatech.scrumbags.controller;

import edu.gatech.scrumbags.fxapp.MainFXApplication;
import edu.gatech.scrumbags.model.WaterType;
import edu.gatech.scrumbags.model.ConditionType;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.util.Arrays;

/*
Controls the flow of information when someone creates a new water source report.
 */
public class WaterSourceReportController {
	@FXML private TextField firstNameField;
	@FXML private ComboBox<WaterType> waterTypeComboBox;
	@FXML private ComboBox<ConditionType> conditionTypeComboBox;

	@FXML
	public void initialize () {
		waterTypeComboBox.setItems(FXCollections.observableArrayList(
				Arrays.asList(WaterType.values())));
		conditionTypeComboBox.setItems(FXCollections.observableArrayList(
				Arrays.asList(ConditionType.values())));
		waterTypeComboBox.setValue(WaterType.Well);
		conditionTypeComboBox.setValue(ConditionType.Potable);
//		setErrorMessage(null);
	}

//	@FXML
//	public void handleRegisterPressed () {
//		if (passwordField.getText().equals(confirmPasswordField.getText())) {
//			Authorized userInfo = MainFXApplication.createAccount(firstNameField.getText(), lastNameField.getText(),
//				usernameField.getText(), passwordField.getText(), accountTypeCombo.getValue());
//			if (userInfo != null) {
//				MainFXApplication.userInfo = userInfo;
//				MainFXApplication.loadScene(MainFXApplication.Scenes.main);
//			} else {
//				setErrorMessage("Error: Username already exists.");
//			}
//		} else {
//			setErrorMessage("Error: Passwords did not match.");
//		}
//	}

	/** Shows the error label and sets the message.
	 * @param msg The message to have the label display. */
//	private void setErrorMessage (String msg) {
//		if (msg != null) {
//			errorLabel.setText(msg);
//			errorLabel.setVisible(true);
//		} else
//			errorLabel.setVisible(false);
//	}

//	@FXML
//	/** Loads application back to the welcome scene. */
//	public void handleCancelPressed () {
//		MainFXApplication.loadScene(MainFXApplication.Scenes.welcome);
//	}
}
