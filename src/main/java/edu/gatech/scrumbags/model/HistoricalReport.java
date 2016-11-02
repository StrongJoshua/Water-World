package edu.gatech.scrumbags.model;

/**
 * The model for the historical report.
 *
 * @author Kevin Lun
 */
public class HistoricalReport {

	private WaterSourceReport waterSource;
	private int year;

	/**
	 * Creates a new historical report with given information.
	 *
	 * @param waterSource The historical report's water source.
	 * @param year        The historical report's year.
	 */
	public HistoricalReport (WaterSourceReport waterSource, int year) {
		this.waterSource = waterSource;
		this.year = year;
	}

}
