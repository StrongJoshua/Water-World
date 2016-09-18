package edu.gatech.scrumbags.controller;
import edu.gatech.scrumbags.fxapp.MainFXApplication;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class WelcomeViewController {
    private Stage mainStage;

    public void setMainStage(Stage mainStage) {
        this.mainStage = mainStage;
    }

    @FXML
    public void handleLoginPressed() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainFXApplication.class.getResource("../view/LoginView.fxml"));
            Scene loginScene = new Scene(loader.load());
            LoginViewController loginController = loader.getController();
            loginController.setMainStage(mainStage);
            loginController.setWelcomeScene(mainStage.getScene());
            mainStage.setScene(loginScene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void handleRegisterPressed() {
    }
}