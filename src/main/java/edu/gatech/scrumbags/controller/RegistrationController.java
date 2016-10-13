package edu.gatech.scrumbags.controller;

import edu.gatech.scrumbags.fxapp.MainFXApplication;
import edu.gatech.scrumbags.model.AccountType;
import edu.gatech.scrumbags.model.Authorized;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.util.Arrays;

/**
 * The controller for the registration view.
 *
 * @author Rishi Raj
 */
public class RegistrationController {

	@FXML private TextField firstNameField;
	@FXML private TextField lastNameField;
	@FXML private TextField usernameField;

	@FXML private PasswordField passwordField;

	@FXML private PasswordField confirmPasswordField;

	@FXML private ComboBox<AccountType> accountTypeCombo;

	@FXML private Label errorLabel;

	@FXML public void initialize () {
		accountTypeCombo.setItems(FXCollections.observableArrayList(Arrays.asList(AccountType.values())));
		accountTypeCombo.setValue(AccountType.user);
		setErrorMessage(null);
	}

	/**
	 * Ensures the password and confirm password match then creates a new user in the backend.
	 */
	@FXML public void handleRegisterPressed () {
		if (passwordField.getText().equals(confirmPasswordField.getText())) {
			Authorized userInfo = MainFXApplication
				.createAccount(firstNameField.getText(), lastNameField.getText(), usernameField.getText(),
					passwordField.getText(), accountTypeCombo.getValue());
			if (userInfo != null) {
				MainFXApplication.userInfo = userInfo;
				MainFXApplication.loadScene(MainFXApplication.Scenes.main);
			} else {
				setErrorMessage("Error: Username already exists.");
			}
		} else {
			setErrorMessage("Error: Passwords did not match.");
		}
	}

	/**
	 * Shows the error label and sets the message.
	 *
	 * @param msg The message to have the label display.
	 */
	private void setErrorMessage (String msg) {
		if (msg != null) {
			errorLabel.setText(msg);
			errorLabel.setVisible(true);
		} else
			errorLabel.setVisible(false);
	}

	/**
	 * Loads application back to the welcome scene.
	 */
	@FXML public void handleCancelPressed () {
		MainFXApplication.loadScene(MainFXApplication.Scenes.welcome);
	}
}
