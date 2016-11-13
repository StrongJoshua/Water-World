
package edu.gatech.scrumbags.controller;

import java.util.Arrays;

import edu.gatech.scrumbags.fxapp.MainFXApplication;
import edu.gatech.scrumbags.model.Authorization;
import edu.gatech.scrumbags.model.User;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

/** The controller for the registration view.
 *
 * @author Rishi Raj */
public class RegistrationController {

    @FXML private TextField firstNameField;
    @FXML private TextField lastNameField;
    @FXML private TextField usernameField;
    @FXML private PasswordField passwordField;
    @FXML private PasswordField confirmPasswordField;
    @FXML private ComboBox<Authorization> accountTypeCombo;
    @FXML private Label errorLabel;
    @FXML private Button confirmRegistrationButton;

    /** Initializes the RegistrationView with account types */
    @FXML
    public void initialize () {
        accountTypeCombo.setItems(FXCollections.observableArrayList(Arrays.asList(Authorization.values())));
        accountTypeCombo.setValue(Authorization.user);
        setErrorMessage(null);
    }

    /** Ensures the password and confirm password match then creates a new user in the backend. */
    @FXML
    public void handleRegisterPressed () {
        confirmRegistrationButton.disableProperty().set(true);
        if (passwordField.getText().length() < 6 || passwordField.getText().length() > 40) {
            setErrorMessage("Password must be between 6 and 40 characters.");
        } else {
            if (passwordField.getText().equals(confirmPasswordField.getText())) {
                User userInfo = MainFXApplication.createAccount(firstNameField.getText(), lastNameField.getText(),
                    usernameField.getText(), passwordField.getText(), accountTypeCombo.getValue());
                if (userInfo != null) {
                    MainFXApplication.userInfo = userInfo;
                    MainFXApplication.client.requestAllReports();
                    MainFXApplication.loadScene(MainFXApplication.Scenes.main);
                } else {
                    setErrorMessage("Username already exists.");
                }
            } else {
                setErrorMessage("Passwords did not match.");
            }
        }
        confirmRegistrationButton.disableProperty().set(false);
    }

    /** Shows the error label and sets the message.
     *
     * @param msg The message to have the label display. */
    private void setErrorMessage (String msg) {
        if (msg != null) {
            errorLabel.setText(msg);
            errorLabel.setVisible(true);
        } else
            errorLabel.setVisible(false);
    }

    /** Loads application back to the welcome scene. */
    @FXML
    public void handleCancelPressed () {
        MainFXApplication.loadScene(MainFXApplication.Scenes.welcome);
    }
}
