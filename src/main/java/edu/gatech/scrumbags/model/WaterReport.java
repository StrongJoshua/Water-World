package edu.gatech.scrumbags.model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Beau on 10/30/2016.
 */
public abstract class WaterReport {
	public static int reportCount = 0;
	private int reportId;
	private WaterLocation location;
	private Date reportDate;
	private String submitterName;

	WaterReport (WaterLocation location, String submitterName) {
		this.reportId = reportCount++;
		this.location = location;
		this.reportDate = new Date();
		this.submitterName = submitterName;
	}

	WaterReport (WaterLocation location, Date reportDate, String submitterName) {
		this.reportId = reportCount++;
		this.location = location;
		this.reportDate = reportDate;
		this.submitterName = submitterName;
	}

	public int getId () {
		return reportId;
	}

	/**
	 * Returns the WaterLocation of this WaterSourceReport
	 *
	 * @return WaterLocation of this Water Source
	 */
	public WaterLocation getLocation () {
		return location;
	}

	/**
	 * Returns a String representation of the location of this water source report
	 *
	 * @return Labeled string representation of the location of this water source report
	 */
	public String getLocationString () {
		return location.toString();
	}

	/**
	 * Returns the date of submission of this water source report as a String
	 *
	 * @return Returns a
	 */
	public String getDateString () {
		DateFormat format = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss aa");
		return format.format(reportDate);
	}

	/**
	 * Returns the full name of the submitter of this water source report
	 *
	 * @return full name of the submitted of this water source report as a String
	 */
	public String getSubmitterName () {
		return submitterName;
	}

	public String toString () {
		return "ID: " + reportId + ", Submitter: " + getSubmitterName() + ", Location: " + getLocationString()
			+ ", Date: " + getDateString();
	}
}
