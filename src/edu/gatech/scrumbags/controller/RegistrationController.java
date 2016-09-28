package edu.gatech.scrumbags.controller;

import edu.gatech.scrumbags.fxapp.MainFXApplication;
import edu.gatech.scrumbags.model.User;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

/**
 * Created by rajmi on 9/27/2016.
 */
public class RegistrationController {

    @FXML
    private TextField firstNameField;
    @FXML
    private TextField lastNameField;
    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private PasswordField confirmPasswordField;

    @FXML
    private ComboBox<String> accountTypeCombo;

    @FXML
    public void initialize() {

    }

    @FXML
    public void handleRegisterPressed() {
        if (validateCredentials()) {
            MainFXApplication.userInfo = new User(usernameField.getText(), passwordField.getText());
            MainFXApplication.loadScene(MainFXApplication.Scenes.loggedIn);
        } else {

        }
    }

    public boolean validateCredentials() {
        // TODO
        return usernameField.getText().equals("scrumbags") && passwordField.getText().equals("2340");
    }

    @FXML
    public void handleCancelPressed() {
        MainFXApplication.loadScene(MainFXApplication.Scenes.welcome);
    }
}
