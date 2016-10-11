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
    private WaterType waterSourceType;
    private WaterCondition sourceCondition;
    private Date sourceReportDate;
    private String submitterName;

    public WaterSourceReport(WaterLocation location, WaterType sourceDescription,
                             WaterCondition sourceCondition, String submitterName) {
        this(location, sourceDescription, sourceCondition, submitterName,
                new Date());
    }

    public WaterSourceReport(WaterLocation location, WaterType waterSourceType,
                             WaterCondition sourceCondition, String submitterName,
                             Date sourceReportDate) {
        this.sourceReportDate = sourceReportDate;
        this.waterSourceType = waterSourceType;
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

    public String getSourceTypeDescription() {
        return waterSourceType.toString();
    }

    public String getSourceConditionDescription() {
        return "Condition: " + sourceCondition.toString();
    }

    public String getSubmitterName() {
        return submitterName;
    }

    public WaterType getSourceType() {
        return waterSourceType;
    }

    public WaterCondition getCondition() {
        return sourceCondition;
    }

    public String getDateString() {
        DateFormat format = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss aa");
        return "Date: " + format.format(sourceReportDate);
    }

    public String toString() {
        return getSourceTypeDescription() + ", "
                + getSourceConditionDescription();
    }
}
