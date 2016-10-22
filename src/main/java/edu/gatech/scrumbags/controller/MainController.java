
package edu.gatech.scrumbags.controller;

import com.lynden.gmapsfx.GoogleMapView;
import com.lynden.gmapsfx.MapComponentInitializedListener;
import com.lynden.gmapsfx.javascript.object.LatLong;
import com.lynden.gmapsfx.javascript.object.MapOptions;
import com.lynden.gmapsfx.javascript.object.MapTypeIdEnum;

import edu.gatech.scrumbags.fxapp.MainFXApplication;
import edu.gatech.scrumbags.fxapp.MainFXApplication.Scenes;
import edu.gatech.scrumbags.model.Authorization;
import edu.gatech.scrumbags.model.User;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;

/** The controller for the main view.
 *
 * @author Kevin Lun */
public class MainController implements MapComponentInitializedListener {
	@FXML private Button waterSourceReportButton;
	@FXML private Button waterPurityReportButton;
	@FXML private Button historicalReportButton;
	@FXML private Button allReportsButton;
	@FXML private Button profileButton;
	@FXML private Button logoutButton;
	@FXML private FlowPane mapPane;

	private GoogleMapView mapView;

	@FXML
	public void initialize () {
		User userInfo = MainFXApplication.userInfo;
		if (userInfo.getAuthorization() == Authorization.user) {
			waterPurityReportButton.setVisible(false);
			waterPurityReportButton.setManaged(false);
			historicalReportButton.setVisible(false);
			historicalReportButton.setManaged(false);
		} else if (userInfo.getAuthorization() == Authorization.worker) {
			historicalReportButton.setVisible(false);
			historicalReportButton.setManaged(false);
		}

		mapView = new GoogleMapView();
		mapView.addMapInializedListener(this);

		mapPane.getChildren().add(mapView);
	}

	@Override
	public void mapInitialized () {
		mapView.setPrefSize(500, 300);
		mapView.autosize();
		MapOptions options = new MapOptions();
		options.center(new LatLong(33.748, -84.388)).zoom(13).overviewMapControl(false).panControl(false)
			.rotateControl(false).scaleControl(false).streetViewControl(false).zoomControl(false)
			.mapType(MapTypeIdEnum.ROADMAP);
		mapView.createMap(options);
	}

	/** Brings the user to the water source report screen. */
	@FXML
	public void handleWaterSourceReportPressed () {
		MainFXApplication.loadScene(Scenes.waterSourceReport);
	}

	/** Brings the user to the water purity report screen. */
	@FXML
	public void handleWaterPurityReportPressed () {
		// MainFXApplication.loadScene(Scenes.waterPurityReport);
	}

	/** Brings the user to the historical report screen. */
	@FXML
	public void handleHistoricalReportPressed () {
		// MainFXApplication.loadScene(Scenes.historicalReport);
	}

	/** Brings the user to the all reports screen. */
	@FXML
	public void handleAllReportsPressed () {
		MainFXApplication.loadScene(Scenes.allReports);
	}

	/** Brings the user to the profle screen. */
	@FXML
	public void handleProfilePressed () {
		MainFXApplication.loadScene(Scenes.profile);
	}

	/** Brings the user to the welcome screen. */
	@FXML
	public void handleLogoutPressed () {
		MainFXApplication.loadScene(Scenes.welcome);
		MainFXApplication.logout();
	}
}
