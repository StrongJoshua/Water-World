
package edu.gatech.scrumbags.controller;

import com.lynden.gmapsfx.GoogleMapView;
import com.lynden.gmapsfx.MapComponentInitializedListener;
import com.lynden.gmapsfx.MapNotInitializedException;
import com.lynden.gmapsfx.javascript.event.UIEventType;
import com.lynden.gmapsfx.javascript.object.*;

import edu.gatech.scrumbags.fxapp.MainFXApplication;
import edu.gatech.scrumbags.fxapp.MainFXApplication.Scenes;
import edu.gatech.scrumbags.model.Authorization;
import edu.gatech.scrumbags.model.User;
import edu.gatech.scrumbags.model.WaterLocation;
import edu.gatech.scrumbags.model.WaterSourceReport;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import netscape.javascript.JSException;
import netscape.javascript.JSObject;

import java.util.List;

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
	@FXML private Pane mapPane;

	@FXML private GoogleMapView mapView;
	private GoogleMap map;

	/**
	 * Initialize the Main screen.
	 */
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

	/**
	 * Initialize the map and load all pins from saved water reports.
	 */
	@Override
	public void mapInitialized () {
		try {
			mapView.setPrefSize(500, 300);
			mapView.autosize();
			MapOptions options = new MapOptions();
			options.center(new LatLong(33.748, -84.388)).zoom(13).overviewMapControl(false).panControl(false)
					.rotateControl(false).scaleControl(false).streetViewControl(false).zoomControl(false)
					.mapType(MapTypeIdEnum.ROADMAP);
			map = mapView.createMap(options);

			List<WaterSourceReport> reports = MainFXApplication.waterReports;
			for (WaterSourceReport report : reports) {
				WaterLocation location = report.getLocation();
				MarkerOptions markerOptions = new MarkerOptions();
				LatLong loc = new LatLong(location.getLatitude(), location.getLongitude());

				markerOptions.position(loc)
						.visible(Boolean.TRUE)
						.title(location.toString());

				Marker marker = new Marker(markerOptions);
				InfoWindow window = new InfoWindow();
				// opens detailed window on click
				map.addUIEventHandler(marker,
						UIEventType.click,
						(JSObject obj) -> {
							window.setContent(report.toString());
							window.open(map, marker);
						});
				// opens basic info window on mouse over
				map.addUIEventHandler(marker,
						UIEventType.mouseover,
						(JSObject obj) -> {
							window.setContent(report.getSourceConditionDescription());
							window.open(map, marker);
						});
				// closes window on mouse out
				map.addUIEventHandler(marker,
						UIEventType.mouseout,
						(JSObject obj) -> {
							if (!window.getContent().equals(report.toString())) {
								window.close();
							}
						});
				map.addMarker(marker);
			}
		} catch (JSException e) {
			System.err.println("Javascript exception while initializing map.");
		} catch (MapNotInitializedException e) {
			System.err.println("Map not initialized successfully.");
		}


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

	public void setMapCoords(double latitude, double longitude) {
		try {
			map.setCenter(new LatLong(latitude, longitude));
		} catch (Exception e) {
			System.err.println("Failed to center map @ (" + latitude + ", " + longitude + ")");
		}
	}
}
