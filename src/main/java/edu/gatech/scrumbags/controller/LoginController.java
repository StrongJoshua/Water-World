
package edu.gatech.scrumbags.controller;

import edu.gatech.scrumbags.fxapp.MainFXApplication;
import edu.gatech.scrumbags.fxapp.MainFXApplication.Scenes;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

/** The controller for the login view.
 *
 * @author Beau Mitchell */
public class LoginController {
    @FXML private Label badLoginLabel;

    @FXML private TextField usernameField;

    @FXML private PasswordField passwordField;

    @FXML
    public void initialize () {
        badLoginLabel.setVisible(false);
    }

    /** Authenticates user and brings the user to the main screen. */
    @FXML
    public void handleLoginPressed () {
        MainFXApplication.userInfo = MainFXApplication.authorizeUser(usernameField.getText(), passwordField.getText());
        if (MainFXApplication.userInfo != null) {
            MainFXApplication.loadScene(Scenes.main);
            MainFXApplication.client.requestAllReports();
        } else {
            badLoginLabel.setVisible(true);
            badLoginLabel.setText("Bad login attempt! Try again.");
            badLoginLabel.setTextFill(Color.DARKRED);
        }
    }

    /** Brings the user to the welcome screen. */
    @FXML
    public void handleCancelPressed () {
        MainFXApplication.loadScene(Scenes.welcome);
    }
}
