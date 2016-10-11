
package edu.gatech.scrumbags.controller;

import edu.gatech.scrumbags.fxapp.MainFXApplication;
import edu.gatech.scrumbags.fxapp.MainFXApplication.Scenes;
import edu.gatech.scrumbags.model.Authorized;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/** The controller for the profile view. Displays all user information and allows editing of the user's email and address.
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

	@FXML
	public void initialize () {
		Authorized userInfo = MainFXApplication.userInfo;
		if (MainFXApplication.userInfo == null) {
			MainFXApplication.loadScene(Scenes.welcome);
		}

		userFirstLast.setText(userInfo.getFullName());
		String account = userInfo.getAccountType().toString();
		userAccountType.setText(account.substring(0, 1).toUpperCase() + account.substring(1));

		resetEditableLabels();

		editMode = false;
	}

	/** Resets email and address labels to the session's user's info. */
	private void resetEditableLabels () {
		Authorized userInfo = MainFXApplication.userInfo;
		userEmail.setVisible(true);
		userEmail.setManaged(true);
		userAddress.setVisible(true);
		userAddress.setManaged(true);
		userEmail.setText(userInfo.getEmail());
		userAddress.setText(userInfo.getAddress());
	}

	/** Switches in between edit and view mode for the email and address fields. */
	@FXML
	public void handleEditPressed () {
		Authorized userInfo = MainFXApplication.userInfo;
		if (editMode) {
			userInfo.setEmail(emailTextField.getText());
			userInfo.setAddress(addressTextField.getText());
			resetEditableLabels();

			emailTextField.setVisible(false);
			addressTextField.setVisible(false);
			editButton.setText("Edit");
		} else {
			userEmail.setVisible(false);
			userEmail.setManaged(false);
			userAddress.setVisible(false);
			userAddress.setManaged(false);

			emailTextField.setVisible(true);
			addressTextField.setVisible(true);

			emailTextField.setText(userInfo.getEmail());
			addressTextField.setText(userInfo.getAddress());

			editButton.setText("Save");
		}
		editMode = !editMode;
	}

	/** Brings the user back to the main screen. */
	@FXML
	public void handleBackPressed () {
		MainFXApplication.loadScene(Scenes.main);
		// MainFXApplication.userInfo = null;
	}
}
