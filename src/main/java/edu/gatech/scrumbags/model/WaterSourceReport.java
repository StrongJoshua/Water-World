package edu.gatech.scrumbags.model;

import java.text.SimpleDateFormat;
import java.text.DateFormat;
import java.util.Date;

/**
 * Water source report class containing a location, description, report date, and relevant methods.
 * Created by Beau on 10/9/2016.
 */
public class WaterSourceReport {
    private static int counter = 0;
    private int reportNumber;
    private WaterLocation location;
    private String sourceDescription;
    private String sourceCondition;
    private Date sourceReportDate;
    private String submitterName;

    public WaterSourceReport(WaterLocation location, String sourceDescription,
                             String sourceCondition, String submitterName) {
        this(location, sourceDescription, sourceCondition, submitterName,
                new Date());
    }

    public WaterSourceReport(WaterLocation location, String sourceDescription,
                             String sourceCondition, String submitterName,
                             Date sourceReportDate) {
        this.sourceReportDate = sourceReportDate;
        this.sourceDescription = sourceDescription;
        this.location = location;
        this.sourceCondition = sourceCondition;
        this.submitterName = submitterName;
        reportNumber = counter++;
    }

    public WaterLocation getLocation() {
        return location;
    }

    public String getLocationString() {
        return "Location: " + location;
    }

    public String getSourceDescription() {
        return sourceDescription;
    }

    public String getSourceCondition() {
        return sourceCondition;
    }

    public String getSubmitterName() {
        return submitterName;
    }

    public String getDateString() {
        DateFormat format = new SimpleDateFormat("yyyy/MM/dd");
        return format.format(sourceReportDate);
    }

    public String toString() {
        return getDateString() + ": " + getSourceDescription();
    }
}
