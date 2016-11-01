package edu.gatech.scrumbags.model;

/**
 * Created by klun on 11/1/2016.
 */
public class HistoricalReport {
	private WaterSourceReport waterSource;
	private int year;

	public HistoricalReport(WaterSourceReport waterSource, int year) {
		this.waterSource = waterSource;
		this.year = year;
	}


}
