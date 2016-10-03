
package edu.gatech.scrumbags.controller;

import java.util.Arrays;

import edu.gatech.scrumbags.fxapp.MainFXApplication;
import edu.gatech.scrumbags.model.AccountType;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

/** Te controller for the registration view.
 * @author Rishi Raj */
public class RegistrationController {

	@FXML private TextField firstNameField;
	@FXML private TextField lastNameField;
	@FXML private TextField usernameField;

	@FXML private PasswordField passwordField;

	@FXML private PasswordField confirmPasswordField;

	@FXML private ComboBox<AccountType> accountTypeCombo;

	@FXML
	public void initialize () {
		accountTypeCombo.setItems(FXCollections.observableArrayList(Arrays.asList(AccountType.values())));
	}

	@FXML
	/** Ensures the password and confirm password match then creates a new user in the backend. */
	public void handleRegisterPressed () {
		if (passwordField.getText().equals(confirmPasswordField.getText())) {
			MainFXApplication.userInfo = MainFXApplication.createAccount(firstNameField.getText(), lastNameField.getText(),
				usernameField.getText(), passwordField.getText(), accountTypeCombo.getValue());
			MainFXApplication.loadScene(MainFXApplication.Scenes.profile);
		} else {

		}
	}

	@FXML
	/** Loads application back to the welcome scene. */
	public void handleCancelPressed () {
		MainFXApplication.loadScene(MainFXApplication.Scenes.welcome);
	}
}
