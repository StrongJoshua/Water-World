
package edu.gatech.scrumbags.controller;

import java.util.Arrays;

import edu.gatech.scrumbags.fxapp.MainFXApplication;
import edu.gatech.scrumbags.model.AccountType;
import javafx.collections.FXCollections;
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

	@FXML private ComboBox<AccountType> accountTypeCombo;

	@FXML
	public void initialize () {
		accountTypeCombo.setItems(FXCollections.observableArrayList(Arrays.asList(AccountType.values())));
	}

	@FXML
	public void handleRegisterPressed () {
		if (passwordField.getText().equals(confirmPasswordField.getText())) {
			MainFXApplication.userInfo = MainFXApplication.createAccount(firstNameField.getText(), lastNameField.getText(),
				usernameField.getText(), passwordField.getText(), accountTypeCombo.getValue());
			MainFXApplication.loadScene(MainFXApplication.Scenes.profile);
		} else {

		}
	}

	@FXML
	public void handleCancelPressed () {
		MainFXApplication.loadScene(MainFXApplication.Scenes.welcome);
	}
}
