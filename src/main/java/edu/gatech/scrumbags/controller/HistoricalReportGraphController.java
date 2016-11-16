
package edu.gatech.scrumbags.controller;

import java.text.DateFormatSymbols;
import java.util.List;

import edu.gatech.scrumbags.fxapp.MainFXApplication;
import edu.gatech.scrumbags.model.HistoricalReport;
import edu.gatech.scrumbags.model.HistoricalType;
import edu.gatech.scrumbags.model.WaterPurityReport;
import javafx.fxml.FXML;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

/** The controller for the historical report graph view.
 *
 * @author Guillaume Noziere */
public class HistoricalReportGraphController {
    @FXML private LineChart<String, Number> lineChart;
    @FXML private CategoryAxis xAxis;
    @FXML private NumberAxis yAxis;

    /** Initializes the HistoricalVirusGraphView with ppm for a chosen year for each month. */
    @FXML
    public void initialize () {
        HistoricalReport report = MainFXApplication.getLastUsedHistoricalReport();
        // modifying chart appearance options
        lineChart.setLegendVisible(false);
        if (report.getType().equals(HistoricalType.Virus)) {
            lineChart.setTitle("Virus PPM");
        } else {
            lineChart.setTitle("Contaminant PPM");
        }
        DateFormatSymbols dfs = new DateFormatSymbols();
        String[] months = dfs.getMonths();
        XYChart.Series<String, Number> graphSeries = new XYChart.Series<>();
        for (int i = 0; i < 12; i++) {
            double avg = 0;
            List<WaterPurityReport> purityReports = report.getWaterSource().getPurityReportsByMonth(report.getYear(), i);
            for (WaterPurityReport purityReport : purityReports) {
                if (report.getType() == HistoricalType.Contaminant) {
                    avg += purityReport.getContaminantPPM();
                } else if (report.getType() == HistoricalType.Virus) {
                    avg += purityReport.getVirusPPM();
                }
            }
            if (!purityReports.isEmpty()) {
                avg /= purityReports.size();
                graphSeries.getData().add(new XYChart.Data<>(months[i].substring(0, 3), avg));
            }
        }
        lineChart.getData().add(graphSeries);
    }

    /** Returns to the main view when back button is pressed. */
    @FXML
    public void handleBackPressed () {
        MainFXApplication.loadScene(MainFXApplication.Scenes.main);
    }
}
