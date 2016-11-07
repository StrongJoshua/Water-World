package edu.gatech.scrumbags.controller;

import edu.gatech.scrumbags.fxapp.MainFXApplication;
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
public class HistoricalContaminantGraphController {
	@FXML private LineChart<String, Number> lineChart;
	@FXML private CategoryAxis xAxis;
	@FXML private NumberAxis yAxis;

	/**
	 * Initializes the HistoricalContaminantGraphView with ppm for a chosen year for each month.
	 */
	@FXML public void initialize () {
		XYChart.Series contaminant = new XYChart.Series();
		lineChart.setLegendVisible(false);

		contaminant.getData().add(new XYChart.Data("Jan", 23));
		contaminant.getData().add(new XYChart.Data("Feb", 14));
		contaminant.getData().add(new XYChart.Data("Mar", 15));
		contaminant.getData().add(new XYChart.Data("Apr", 24));
		contaminant.getData().add(new XYChart.Data("May", 34));
		contaminant.getData().add(new XYChart.Data("Jun", 36));
		contaminant.getData().add(new XYChart.Data("Jul", 22));
		contaminant.getData().add(new XYChart.Data("Aug", 45));
		contaminant.getData().add(new XYChart.Data("Sep", 43));
		contaminant.getData().add(new XYChart.Data("Oct", 17));
		contaminant.getData().add(new XYChart.Data("Nov", 29));
		contaminant.getData().add(new XYChart.Data("Dec", 25));

		lineChart.getData().add(contaminant);
	}

	/**
	 * Returns to the main view when back button is pressed.
	 */
	@FXML public void handleBackPressed () {
		MainFXApplication.loadScene(MainFXApplication.Scenes.main);
	}
}
