
package edu.gatech.scrumbags.model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/** Abstract water report class to allow for inheritance.
 * @author Beau Mitchell */
public abstract class WaterReport {
    public static int reportCount = 0;
    private int reportID;
    private WaterLocation location;
    private Date reportDate;
    private String submitterName;

    /** Creates a new WaterReport with current Date and given information
     *
     * @param location The location of this WaterReport.
     * @param submitterName The name of the submitter of this WaterReport. */
    WaterReport (WaterLocation location, String submitterName) {
        this.reportID = reportCount;
		reportCount++;
		this.location = location;
        this.reportDate = new Date();
        this.submitterName = submitterName;
    }

    /** Creates a new WaterReport with given Date and given information
     *
     * @param location The location of this WaterReport.
     * @param reportDate The date of this WaterReport.
     * @param submitterName The name of the submitter of this WaterReport. */
    WaterReport (WaterLocation location, Date reportDate, String submitterName) {
        this.reportID = reportCount;
		reportCount++;
		this.location = location;
        this.reportDate = reportDate;
        this.submitterName = submitterName;
    }

    /** Returns the ID of this WaterReport
     *
     * @return ID of this WaterReport */
    public int getId () {
        return reportID;
    }

    /** Returns the WaterLocation of this WaterReport
     *
     * @return WaterLocation of this WaterReport */
    public WaterLocation getLocation () {
        return location;
    }

    /** Returns a String representation of the location of this WaterReport
     *
     * @return Labeled string representation of the location of this WaterReport */
    public String getLocationString () {
        return location.toString();
    }

    /** Returns the date of submission of this WaterReport as a String
     *
     * @return Returns a Date string */
    public String getDateString () {
        DateFormat format = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss aa");
        return format.format(reportDate);
    }

    /** Returns the date of submission of this WaterReport
     *
     * @return a Date object */
    public Date getReportDate () {
        return reportDate;
    }

    /** Returns the full name of the submitter of this WaterReport
     *
     * @return full name of the submitted of this WaterReport as a String */
    public String getSubmitterName () {
        return submitterName;
    }

    public String toString () {
        return "ID: " + reportID + ", Submitter: " + getSubmitterName() + ", Location: " + getLocationString() + ", Date: "
            + getDateString();
    }
}
