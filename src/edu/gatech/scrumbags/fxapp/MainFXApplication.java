package edu.gatech.scrumbags.fxapp;

import edu.gatech.scrumbags.controller.LoginViewController;
import edu.gatech.scrumbags.controller.WelcomeViewController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainFXApplication extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(MainFXApplication.class.getResource("../view/WelcomeView.fxml"));
        Parent root = loader.load();
        primaryStage.setTitle("Clean Water by SCRUMbags for CS 2340");
        primaryStage.setScene(new Scene(root, 500, 500));
        ((WelcomeViewController) loader.getController()).setMainStage(primaryStage);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
