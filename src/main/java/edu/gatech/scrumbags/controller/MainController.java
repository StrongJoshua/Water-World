
package edu.gatech.scrumbags.controller;

import java.util.List;

import com.lynden.gmapsfx.GoogleMapView;
import com.lynden.gmapsfx.MapComponentInitializedListener;
import com.lynden.gmapsfx.MapNotInitializedException;
import com.lynden.gmapsfx.javascript.event.UIEventType;
import com.lynden.gmapsfx.javascript.object.GoogleMap;
import com.lynden.gmapsfx.javascript.object.InfoWindow;
import com.lynden.gmapsfx.javascript.object.LatLong;
import com.lynden.gmapsfx.javascript.object.MapOptions;
import com.lynden.gmapsfx.javascript.object.MapTypeIdEnum;
import com.lynden.gmapsfx.javascript.object.Marker;
import com.lynden.gmapsfx.javascript.object.MarkerOptions;

import edu.gatech.scrumbags.fxapp.MainFXApplication;
import edu.gatech.scrumbags.fxapp.MainFXApplication.Scenes;
import edu.gatech.scrumbags.model.Authorization;
import edu.gatech.scrumbags.model.User;
import edu.gatech.scrumbags.model.WaterLocation;
import edu.gatech.scrumbags.model.WaterReport;
import edu.gatech.scrumbags.model.WaterSourceReport;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import netscape.javascript.JSException;
import netscape.javascript.JSObject;

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
    private boolean hasClickedPin = false;

    /** Initialize the Main screen. */
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
        waterPurityReportButton.setDisable(true);
		historicalReportButton.setDisable(true);
        mapView.addMapInializedListener(this);
    }

    /** Initialize the map and load all pins from saved water reports. */
    @Override
    public void mapInitialized () {
        try {
            mapView.setPrefSize(500, 300);
            mapView.autosize();
            MapOptions options = new MapOptions();
            options.center(new LatLong(33.748, -84.388)).zoom(13).overviewMapControl(false).panControl(false).rotateControl(false)
                .scaleControl(false).streetViewControl(false).zoomControl(false).mapType(MapTypeIdEnum.ROADMAP);
            map = mapView.createMap(options);

            List<WaterReport> reports = MainFXApplication.waterReports;
            for (WaterReport report : reports) {
                if (report instanceof WaterSourceReport) {
                    WaterLocation location = report.getLocation();
                    MarkerOptions markerOptions = new MarkerOptions();
                    LatLong loc = new LatLong(location.getLatitude(), location.getLongitude());

                    markerOptions.position(loc).visible(Boolean.TRUE).title(location.toString());

                    Marker marker = new Marker(markerOptions);
                    InfoWindow window = new InfoWindow();
                    // opens detailed window on click
                    map.addUIEventHandler(marker, UIEventType.click, (JSObject obj) -> {
                        window.setContent(report.toString());
                        window.open(map, marker);
                        MainFXApplication.setLastUsedSourceReport((WaterSourceReport)report);
                        hasClickedPin = true;
						waterPurityReportButton.setDisable(false);
						historicalReportButton.setDisable(false);
                    });
                    // opens basic info window on mouse over
                    map.addUIEventHandler(marker, UIEventType.mouseover, (JSObject obj) -> {
                        window.setContent(((WaterSourceReport)report).getSourceConditionDescription());
                        window.open(map, marker);
                    });
                    // closes window on mouse out
                    map.addUIEventHandler(marker, UIEventType.mouseout, (JSObject obj) -> {
                        if (!window.getContent().equals(report.toString())) {
                            window.close();
                        }
                    });
                    map.addMarker(marker);
                }
            }
        } catch (JSException e) {
            e.printStackTrace();
            System.err.println("Javascript exception while initializing map.");
        } catch (MapNotInitializedException e) {
            e.printStackTrace();
            System.err.println("GMapsFX threw exception: Map not initialized successfully.");
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
        if (hasClickedPin) {
            MainFXApplication.loadScene(Scenes.waterPurityReport);
        }
    }

    /** Brings the user to the historical report screen. */
    @FXML
    public void handleHistoricalReportPressed () {
        if (hasClickedPin) {
            MainFXApplication.loadScene(Scenes.historicalReport);
        }
    }

    /** Brings the user to the all reports screen. */
    @FXML
    public void handleAllReportsPressed () {
        MainFXApplication.loadScene(Scenes.allReports);
    }

    /** Brings the user to the profile screen. */
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

    /** Sets new map center
     *
     * @param latitude latitude of new center
     * @param longitude longitude of new center */
    public void setMapCoordinates (double latitude, double longitude) {
        try {
            map.setCenter(new LatLong(latitude, longitude));
        } catch (Exception e) {
            System.err.println("Failed to center map @ (" + latitude + ", " + longitude + ")");
        }
    }
}
