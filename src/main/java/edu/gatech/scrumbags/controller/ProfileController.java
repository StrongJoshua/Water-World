
package edu.gatech.scrumbags.controller;

import java.util.Optional;

import edu.gatech.scrumbags.fxapp.MainFXApplication;
import edu.gatech.scrumbags.fxapp.MainFXApplication.Scenes;
import edu.gatech.scrumbags.model.User;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/** The controller for the profile view. Displays all user information and allows editing of the user's email and address.
 *
 * @author Jan Risse */
public class ProfileController {
    @FXML private Label userFirstLast;
    @FXML private Label userAccountType;
    @FXML private Label userEmail;
    @FXML private Label userAddress;
    @FXML private TextField emailTextField;
    @FXML private TextField addressTextField;
    @FXML private Button editButton;
    @FXML private Button backButton;

    private boolean editMode;

    /** Initializes the ProfileView with name and account type. */
    @FXML
    public void initialize () {
        User userInfo = MainFXApplication.userInfo;
        if (MainFXApplication.userInfo == null) {
            MainFXApplication.loadScene(Scenes.welcome);
        }

        userFirstLast.setText(userInfo.getFullName());
        String account = userInfo.getAuthorization().toString();
        userAccountType.setText(account.substring(0, 1).toUpperCase() + account.substring(1));

        resetEditableLabels();

        editMode = false;
        setManageds(editMode);
    }

    /** Resets email and address labels to the session's user's info. */
    private void resetEditableLabels () {
        User userInfo = MainFXApplication.userInfo;
        userEmail.setText(userInfo.getEmail());
        userAddress.setText(userInfo.getAddress());
    }

    /** Handles managing and visibility when swapping between edit and view modes.
     * @param editMode The current edit mode. */
    private void setManageds (boolean editMode) {
        userEmail.setVisible(!editMode);
        userEmail.setManaged(!editMode);
        userAddress.setVisible(!editMode);
        userAddress.setManaged(!editMode);
        emailTextField.setVisible(editMode);
        emailTextField.setManaged(editMode);
        addressTextField.setVisible(editMode);
        addressTextField.setManaged(editMode);
    }

    /** Switches in between edit and view mode for the email and address fields. */
    @FXML
    public void handleEditPressed () {
        User userInfo = MainFXApplication.userInfo;
        if (editMode) {
            // userInfo.setEmail(emailTextField.getText());
            // userInfo.setAddress(addressTextField.getText());
            MainFXApplication.client.updateUserInfo(emailTextField.getText(), addressTextField.getText());
            resetEditableLabels();
            editButton.setText("Edit");
        } else {
            emailTextField.setText(userInfo.getEmail());
            addressTextField.setText(userInfo.getAddress());

            editButton.setText("Save");
        }
        editMode = !editMode;
        setManageds(editMode);
    }

    /** Brings the user back to the main screen. */
    @FXML
    public void handleBackPressed () {
        MainFXApplication.loadScene(Scenes.main);
        // MainFXApplication.userInfo = null;
    }

    /** Brings the user back to the main screen. */
    @FXML
    public void handleDeleteAccountPressed () {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText("Delete Account");
        alert.setContentText("Are you sure you want to DELETE your account?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            // ... user chose OK
            MainFXApplication.client.deleteAccount();
            MainFXApplication.loadScene(Scenes.welcome);
        } else {
            // ... user chose CANCEL or closed the dialog
        }

        // MainFXApplication.userInfo = null;
    }

}
