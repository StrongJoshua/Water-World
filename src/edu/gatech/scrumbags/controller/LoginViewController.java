package edu.gatech.scrumbags.controller;

import edu.gatech.scrumbags.fxapp.MainFXApplication;
import edu.gatech.scrumbags.fxapp.MainFXApplication.Scenes;
import edu.gatech.scrumbags.model.User;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

public class LoginViewController {
    @FXML
    private Label badLoginLabel;

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    public void initialize() {
        badLoginLabel.setVisible(false);
    }

    @FXML
    public void handleLoginPressed() {
        if (validateCredentials()) {
            MainFXApplication.userInfo = new User(usernameField.getText(), passwordField.getText());
            MainFXApplication.loadScene(Scenes.loggedIn);
        } else {
            badLoginLabel.setVisible(true);
            badLoginLabel.setText("Bad login attempt! Try again.");
            badLoginLabel.setTextFill(Color.DARKRED);
        }
    }

    public boolean validateCredentials() {
        // TODO
        return usernameField.getText().equals("scrumbags") && passwordField.getText().equals("2340");
    }

    @FXML
    public void handleCancelPressed() {
        MainFXApplication.loadScene(Scenes.welcome);
    }
}