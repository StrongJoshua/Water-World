package edu.gatech.scrumbags.controller;
import edu.gatech.scrumbags.fxapp.MainFXApplication;
import edu.gatech.scrumbags.fxapp.MainFXApplication.Scenes;
import javafx.fxml.FXML;

public class WelcomeViewController {
	@FXML
	public void initialize() {
		if(MainFXApplication.userInfo != null) {
			MainFXApplication.loadScene(Scenes.loggedIn);
		}
	}
	
    @FXML
    public void handleLoginPressed() {
        MainFXApplication.loadScene(Scenes.login);
    }

    @FXML
    public void handleRegisterPressed() {
    }
}