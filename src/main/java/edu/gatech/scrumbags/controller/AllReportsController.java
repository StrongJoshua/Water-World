
package edu.gatech.scrumbags.controller;

import java.util.List;

import edu.gatech.scrumbags.fxapp.MainFXApplication;
import edu.gatech.scrumbags.fxapp.MainFXApplication.Scenes;
import edu.gatech.scrumbags.model.WaterCondition;
import edu.gatech.scrumbags.model.WaterPurityCondition;
import edu.gatech.scrumbags.model.WaterPurityReport;
import edu.gatech.scrumbags.model.WaterReport;
import edu.gatech.scrumbags.model.WaterSourceReport;
import edu.gatech.scrumbags.model.WaterType;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.util.Callback;

/** The controller for the all reports view, the view containing a list of all water reports
 *
 * @author Beau Mitchell */
public class AllReportsController implements UpdateableController {
    @FXML TableView<WaterReport> reportsTable;
    @FXML TableColumn<WaterReport, Integer> idCol;
    @FXML TableColumn<WaterReport, String> reportTypeCol;
    @FXML TableColumn<WaterReport, String> dateCol;
    @FXML TableColumn<WaterReport, String> submitterCol;
    @FXML TableColumn<WaterReport, String> locationCol;
    @FXML TableColumn<WaterReport, WaterType> typeCol;
    @FXML TableColumn<WaterReport, WaterCondition> conditionCol;
    @FXML TableColumn<WaterReport, WaterPurityCondition> purityCol;
    @FXML TableColumn<WaterReport, Double> virusCol;
    @FXML TableColumn<WaterReport, Double> contaminantCol;

    /** Called when the view is initialized. Sets up the list view and binds the labels to the selection model note: reports from
     * MainFXApplication::waterReports are loaded here when the view is loaded. */
    @FXML
    public void initialize () {
        idCol.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<WaterReport, Integer>, ObservableValue<Integer>>() {
            @Override
            public ObservableValue<Integer> call (CellDataFeatures<WaterReport, Integer> param) {
                return new ReadOnlyObjectWrapper<>(param.getValue().getId());
            }
        });
        reportTypeCol
            .setCellValueFactory(new Callback<TableColumn.CellDataFeatures<WaterReport, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call (CellDataFeatures<WaterReport, String> param) {
                    String s = "";
                    if (param.getValue() instanceof WaterSourceReport)
                        s = "Source";
                    else
                        s = "Purity";
                    return new ReadOnlyObjectWrapper<>(s);
                }
            });
        dateCol.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<WaterReport, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call (CellDataFeatures<WaterReport, String> param) {
                return new ReadOnlyObjectWrapper<>(param.getValue().getDateString());
            }
        });
        submitterCol
            .setCellValueFactory(new Callback<TableColumn.CellDataFeatures<WaterReport, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call (CellDataFeatures<WaterReport, String> param) {
                    return new ReadOnlyObjectWrapper<>(param.getValue().getSubmitterName());
                }
            });
        locationCol
            .setCellValueFactory(new Callback<TableColumn.CellDataFeatures<WaterReport, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call (CellDataFeatures<WaterReport, String> param) {
                    return new ReadOnlyObjectWrapper<>(param.getValue().getLocationString());
                }
            });
        typeCol.setCellValueFactory(
            new Callback<TableColumn.CellDataFeatures<WaterReport, WaterType>, ObservableValue<WaterType>>() {
                @Override
                public ObservableValue<WaterType> call (CellDataFeatures<WaterReport, WaterType> param) {
                    if (param.getValue() instanceof WaterSourceReport)
                        return new ReadOnlyObjectWrapper<>(((WaterSourceReport)param.getValue()).getSourceType());
                    return null;
                }
            });
        conditionCol.setCellValueFactory(
            new Callback<TableColumn.CellDataFeatures<WaterReport, WaterCondition>, ObservableValue<WaterCondition>>() {
                @Override
                public ObservableValue<WaterCondition> call (CellDataFeatures<WaterReport, WaterCondition> param) {
                    if (param.getValue() instanceof WaterSourceReport)
                        return new ReadOnlyObjectWrapper<>(((WaterSourceReport)param.getValue()).getCondition());
                    return null;
                }
            });
        purityCol.setCellValueFactory(
            new Callback<TableColumn.CellDataFeatures<WaterReport, WaterPurityCondition>, ObservableValue<WaterPurityCondition>>() {
                @Override
                public ObservableValue<WaterPurityCondition> call (CellDataFeatures<WaterReport, WaterPurityCondition> param) {
                    if (param.getValue() instanceof WaterPurityReport)
                        return new ReadOnlyObjectWrapper<>(((WaterPurityReport)param.getValue()).getPurityCondition());
                    return null;
                }
            });
        virusCol.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<WaterReport, Double>, ObservableValue<Double>>() {
            @Override
            public ObservableValue<Double> call (CellDataFeatures<WaterReport, Double> param) {
                if (param.getValue() instanceof WaterPurityReport)
                    return new ReadOnlyObjectWrapper<>(((WaterPurityReport)param.getValue()).getVirusPPM());
                return null;
            }
        });
        contaminantCol
            .setCellValueFactory(new Callback<TableColumn.CellDataFeatures<WaterReport, Double>, ObservableValue<Double>>() {
                @Override
                public ObservableValue<Double> call (CellDataFeatures<WaterReport, Double> param) {
                    if (param.getValue() instanceof WaterPurityReport)
                        return new ReadOnlyObjectWrapper<>(((WaterPurityReport)param.getValue()).getContaminantPPM());
                    return null;
                }
            });
        reportsTable.getItems().addAll(MainFXApplication.waterReports);
    }

    /** Returns to the main view when back button is pressed. */
    @FXML
    public void handleBackPressed () {
        MainFXApplication.loadScene(Scenes.main);
    }

    /** Centers the map on this water source and returns to the map view */
    @FXML
    public void handleViewPressed () {
        MainFXApplication.loadScene(Scenes.main);
        MainFXApplication.setMapLocation(reportsTable.getSelectionModel().getSelectedItem().getLocation());
    }

    @Override
    public void updateReports (List<WaterReport> newReports) {
        reportsTable.getItems().addAll(newReports);
    }
}
