package edu.gatech.scrumbags.controller;

import edu.gatech.scrumbags.fxapp.MainFXApplication;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

public class LoginViewController {
    private Scene welcomeScene;

    public void setWelcomeScene(Scene welcomeScene) {
        this.welcomeScene = welcomeScene;
    }

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
            badLoginLabel.setVisible(true);
            badLoginLabel.setText("Login successful.");
            badLoginLabel.setTextFill(Color.DARKGREEN);
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
        MainFXApplication.mainStage.setScene(welcomeScene);
    }
}