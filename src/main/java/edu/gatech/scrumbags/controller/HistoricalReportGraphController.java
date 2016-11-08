package edu.gatech.scrumbags.controller;

import edu.gatech.scrumbags.fxapp.MainFXApplication;
import edu.gatech.scrumbags.model.HistoricalType;
import edu.gatech.scrumbags.model.HistoricalReport;
import edu.gatech.scrumbags.model.WaterPurityReport;
import edu.gatech.scrumbags.model.WaterSourceReport;
import javafx.fxml.FXML;

import java.text.DateFormatSymbols;
import java.util.Calendar;
import java.util.List;
import java.util.LinkedList;
import java.util.Stack;

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
    private Calendar calendar = Calendar.getInstance();

	/**
	 * Initializes the HistoricalVirusGraphView with ppm for a chosen year for each month.
	 */
	@FXML public void initialize () {
        HistoricalReport report = MainFXApplication.getLastUsedHistoricalReport();
        // modifying chart appearance options
        lineChart.setLegendVisible(false);
        if (report.getType().equals(HistoricalType.Virus)) {
            lineChart.setTitle("Virus PPM");
        } else {
            lineChart.setTitle("Contaminant PPM");
        }

        XYChart.Series<String, Number> graphSeries = new XYChart.Series<>();
        List<WaterPurityReport> dataReportList = MainFXApplication.purityMap.get(report.getWaterSource());
        Stack<Double>[] monthDataArray = (Stack<Double>[]) new Stack[12];
        // getting the info into usable format
        for (WaterPurityReport item : dataReportList) {
            calendar.setTime(item.getReportDate());
            if (calendar.get(Calendar.YEAR) == report.getYear()) {
                double ppmValue;
                if (report.getType().equals(HistoricalType.Virus)) {
                    ppmValue = item.getVirusPPM();
                } else {
                    ppmValue = item.getContaminantPPM();
                }
                // adding to linked list array
                if (monthDataArray[calendar.get(Calendar.MONTH)] == null) {
                    Stack<Double> monthDataList = new Stack<>();
                    monthDataList.push(ppmValue);
                    monthDataArray[calendar.get(Calendar.MONTH)] = monthDataList;
                } else {
                    Stack<Double> monthDataList = monthDataArray[calendar.get(Calendar.MONTH)];
                    monthDataList.push(ppmValue);
                    monthDataArray[calendar.get(Calendar.MONTH)] = monthDataList;
                }
            }
        }
        // adding the data to the chart
        double avg;
        DateFormatSymbols dfs = new DateFormatSymbols();
        String[] months = dfs.getMonths();
        for (int i = 0; i < 12; i++) {
            Stack<Double> stack = monthDataArray[i];
            double sum = 0.0;
            int count = 0;
            while (stack != null && !stack.empty()) {
                sum += stack.pop();
                count++;
            }
            avg = sum / count;
            if (count > 0) {
                graphSeries.getData().add(new XYChart.Data<>(months[i].substring(0, 3), avg));
            }
        }
		lineChart.getData().add(graphSeries);
	}

	/**
	 * Returns to the main view when back button is pressed.
	 */
	@FXML public void handleBackPressed () {
		MainFXApplication.loadScene(MainFXApplication.Scenes.main);
	}
}
