
package edu.gatech.scrumbags.controller;

import java.util.Arrays;

import edu.gatech.scrumbags.fxapp.MainFXApplication;
import edu.gatech.scrumbags.model.WaterCondition;
import edu.gatech.scrumbags.model.WaterLocation;
import edu.gatech.scrumbags.model.WaterSourceReport;
import edu.gatech.scrumbags.model.WaterType;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/** Controls the flow of information when someone creates a new water source report.
 *
 * @author gnoziere */
public class WaterSourceReportController {

    @FXML private Label errorMessage;

    @FXML private TextField latitudeText;
    @FXML private TextField longitudeText;

    @FXML private ComboBox<WaterType> waterTypeCombo;
    @FXML private ComboBox<WaterCondition> waterConditionCombo;

    /** Initializes the WaterSourceReportView as to minimize errors by populating combo boxes, setting default choices and making
     * the error message invisible. */
    @FXML
    public void initialize () {
        waterTypeCombo.setItems(FXCollections.observableArrayList(Arrays.asList(WaterType.values())));
        waterConditionCombo.setItems(FXCollections.observableArrayList(Arrays.asList(WaterCondition.values())));
        waterTypeCombo.setValue(WaterType.Bottled);
        waterConditionCombo.setValue(WaterCondition.Waste);
        setErrorMessage(null);
    }

    /** Handles the input verification when the user presses the submit button Checks that latitude and longitude values are
     * acceptable and pulls the rest of the needed information from the user or local time. */
    @FXML
    public void handleSubmitPressed () {
        // trying to get correct GPS coordinates
        boolean successfulParse = false;
        double latitude = 0.0;
        double longitude = 0.0;
        try {
            latitude = Double.parseDouble(latitudeText.getText());
            longitude = Double.parseDouble(longitudeText.getText());
            if ((latitude < -90.0) || (latitude > 90.0)) {
                setErrorMessage("Latitude must be between -90 and 90.");
            } else if ((longitude < -180.0) || (longitude > 180.0)) {
                setErrorMessage("Longitude must be between -180 and 180.");
            } else {
                successfulParse = true;
            }
        } catch (NullPointerException e) {
            setErrorMessage("GPS coordinates must be numbers.");
        } catch (NumberFormatException e) {
            setErrorMessage("GPS coordinates must be valid numbers.");
        }

        // submitting all information needed for a full water source report
        if (successfulParse) {
            WaterSourceReport sourceReport = new WaterSourceReport(new WaterLocation(latitude, longitude),
                waterTypeCombo.getValue(), waterConditionCombo.getValue(), MainFXApplication.userInfo.getFullName());
			System.out.println(sourceReport.getId());
            MainFXApplication.client.sendSourceReport(sourceReport);

            MainFXApplication.loadScene(MainFXApplication.Scenes.main);
        }
    }

    /** Shows the error label and sets the message to whatever is inputted or null.
     *
     * @param msg The message to have the label display. */
    private void setErrorMessage (String msg) {
        if (msg != null) {
            errorMessage.setText(msg);
            errorMessage.setVisible(true);
        } else
            errorMessage.setVisible(false);
    }

    /** Sends the user back to the main screen when cancelling. */
    @FXML
    public void handleCancelPressed () {
        MainFXApplication.loadScene(MainFXApplication.Scenes.main);
    }

}
