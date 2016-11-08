package edu.gatech.scrumbags.controller;

import edu.gatech.scrumbags.fxapp.MainFXApplication;
import edu.gatech.scrumbags.model.HistoricalType;
import javafx.fxml.FXML;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

/**
 * The controller for the historical report graph view.
 *
 * @author Guillaume Noziere
 */
public class HistoricalReportGraphController {
	@FXML private LineChart<String, Number> lineChart;
	@FXML private CategoryAxis xAxis;
	@FXML private NumberAxis yAxis;

	/**
	 * Initializes the HistoricalVirusGraphView with ppm for a chosen year for each month.
	 */
	@FXML public void initialize () {
        // modifying chart appearance options
        lineChart.setLegendVisible(false);
        if (MainFXApplication.getLastUsedHistoricalReportReport().getType().equals(HistoricalType.Virus)) {
            lineChart.setTitle("Virus PPM");
        } else {
            lineChart.setTitle("Contaminant PPM");
        }

        XYChart.Series<String, Number> virus = new XYChart.Series<>();

		virus.getData().add(new XYChart.Data<>("Jan", 23));

		lineChart.getData().add(virus);
	}

	/**
	 * Returns to the main view when back button is pressed.
	 */
	@FXML public void handleBackPressed () {
		MainFXApplication.loadScene(MainFXApplication.Scenes.main);
	}
}
