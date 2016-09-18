package edu.gatech.scrumbags.controller;
import java.io.IOException;

import edu.gatech.scrumbags.fxapp.MainFXApplication;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class WelcomeViewController {
    @FXML
    public void handleLoginPressed() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/edu/gatech/scrumbags/view/LoginView.fxml"));
            Stage main = MainFXApplication.mainStage;
            Scene loginScene = new Scene(loader.load(), main.getScene().getWidth(), main.getScene().getHeight());
            LoginViewController loginController = loader.getController();
            loginController.setWelcomeScene(main.getScene());
            main.setScene(loginScene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void handleRegisterPressed() {
    }
}