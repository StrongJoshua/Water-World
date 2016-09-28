
package edu.gatech.scrumbags.controller;

import edu.gatech.scrumbags.fxapp.MainFXApplication;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

/** Created by rajmi on 9/27/2016. */
public class RegistrationController {

	@FXML private TextField firstNameField;
	@FXML private TextField lastNameField;
	@FXML private TextField usernameField;

	@FXML private PasswordField passwordField;

	@FXML private PasswordField confirmPasswordField;

	@FXML private ComboBox<String> accountTypeCombo;

	@FXML
	public void initialize () {

	}

	@FXML
	public void handleRegisterPressed () {
		if (passwordField.getText().equals(confirmPasswordField.getText())) {
			MainFXApplication.userInfo = MainFXApplication.createAccount(firstNameField.getText(), lastNameField.getText(),
				usernameField.getText(), passwordField.getText(), accountTypeCombo.getValue());
			MainFXApplication.loadScene(MainFXApplication.Scenes.loggedIn);
		} else {

		}
	}

	@FXML
	public void handleCancelPressed () {
		MainFXApplication.loadScene(MainFXApplication.Scenes.welcome);
	}
}
