package edu.gatech.scrumbags.controller;

import edu.gatech.scrumbags.fxapp.MainFXApplication;
import javafx.fxml.FXML;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

/**
 * Created by klun on 11/1/2016.
 */
public class HistoricalReportGraphController {
	@FXML private LineChart<String, Number> lineChart;
	@FXML private CategoryAxis xAxis;
	@FXML private NumberAxis yAxis;

	@FXML public void initialize () {
		XYChart.Series virus = new XYChart.Series();
		virus.setName("My portfolio");

		virus.getData().add(new XYChart.Data("Jan", 23));
		virus.getData().add(new XYChart.Data("Feb", 14));
		virus.getData().add(new XYChart.Data("Mar", 15));
		virus.getData().add(new XYChart.Data("Apr", 24));
		virus.getData().add(new XYChart.Data("May", 34));
		virus.getData().add(new XYChart.Data("Jun", 36));
		virus.getData().add(new XYChart.Data("Jul", 22));
		virus.getData().add(new XYChart.Data("Aug", 45));
		virus.getData().add(new XYChart.Data("Sep", 43));
		virus.getData().add(new XYChart.Data("Oct", 17));
		virus.getData().add(new XYChart.Data("Nov", 29));
		virus.getData().add(new XYChart.Data("Dec", 25));

		lineChart.getData().add(virus);
	}

	/**
	 * Returns to the main view when back button is pressed.
	 */
	@FXML public void handleBackPressed () {
		MainFXApplication.loadScene(MainFXApplication.Scenes.main);
	}
}
