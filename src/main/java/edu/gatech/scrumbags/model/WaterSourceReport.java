package edu.gatech.scrumbags.model;

import java.text.SimpleDateFormat;
import java.text.DateFormat;
import java.util.Date;

/**
 * Water source report class containing a location, description, report date, and relevant methods.
 * Created by Beau on 10/9/2016.
 */
public class WaterSourceReport {
    private WaterLocation location;
    private String sourceDescription;
    private Date sourceReportDate;

    public WaterSourceReport(WaterLocation location, String sourceDescription) {
        this(location, sourceDescription, new Date());
    }

    public WaterSourceReport(WaterLocation location, String sourceDescription, Date sourceReportDate) {
        this.sourceReportDate = sourceReportDate;
        this.sourceDescription = sourceDescription;
        this.location = location;
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

    public String getDateString() {
        DateFormat format = new SimpleDateFormat("yyyy/MM/dd");
        return format.format(sourceReportDate);
    }

    public String toString() {
        return getDateString() + ": " + getSourceDescription();
    }
}
