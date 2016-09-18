package edu.gatech.scrumbags.controller;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class LoginViewController {
    private Stage mainStage;
    private Scene welcomeScene;

    public void setMainStage(Stage mainStage) {
        this.mainStage = mainStage;
    }


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
            badLoginLabel.setText("Bad login! Try again.");
            badLoginLabel.setTextFill(Color.DARKRED);
        }
    }

    public boolean validateCredentials() {
        // TODO
        return usernameField.getText().equals("scrumbags") && passwordField.getText().equals("2340");
    }

    @FXML
    public void handleCancelPressed() {
        mainStage.setScene(welcomeScene);
    }
}