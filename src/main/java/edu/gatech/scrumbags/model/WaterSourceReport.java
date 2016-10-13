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

    /**
     * Constructs a new WaterSourceReport that was submitted at the moment this object was constructed
     * @param location the WaterLocation of this WaterSourceReport
     * @param sourceDescription the WaterType of the water at this water source
     * @param sourceCondition the WaterCondition of the water at this water source
     * @param submitterName the full name of the submitter of this WaterSourceReport
     */
    public WaterSourceReport(WaterLocation location, WaterType sourceDescription,
                             WaterCondition sourceCondition, String submitterName) {
        this(location, sourceDescription, sourceCondition, submitterName,
                new Date());
    }

    /**
     * Constructs a new WaterSourceReport
     * @param location the WaterLocation of this WaterSourceReport
     * @param waterSourceType the WaterType of the water at this water source
     * @param sourceCondition the WaterCondition of the water at this water source
     * @param submitterName the full name of the submitter of this WaterSourceReport
     * @param sourceReportDate the date and time this report was submitted as a java.util.Date object
     */
    public WaterSourceReport(WaterLocation location, WaterType waterSourceType,
                             WaterCondition sourceCondition, String submitterName,
                             Date sourceReportDate) {
        this.sourceReportDate = sourceReportDate;
        this.waterSourceType = waterSourceType;
        this.location = location;
        this.sourceCondition = sourceCondition;
        this.submitterName = submitterName;
        this.reportNumber = counter++;
    }

    /**
     * Returns the WaterLocation of this WaterSourceReport
     * @return WaterLocation of this Water Source
     */
    public WaterLocation getLocation() {
        return location;
    }


    /**
     * Returns a String representation of the location of this water source report
     * @return Labeled string representation of the location of this water source report
     */
    public String getLocationString() {
        return "Location: " + location;
    }

    /**
     * Returns a description of the source type of this water source
     * @return String representation of the Water Source Type of this source
     */
    public String getSourceTypeDescription() {
        return waterSourceType.toString();
    }

    /**
     * Return a String description of the condition of this water source
     * @return labeled String description of the general condition of this water source
     */
    public String getSourceConditionDescription() {
        return "Condition: " + sourceCondition.toString();
    }

    /**
     * Returns the full name of the submitter of this water source report
     * @return full name of the submitted of this water source report as a String
     */
    public String getSubmitterName() {
        return submitterName;
    }

    /**
     * Returns the WaterType of this WaterSourceReport
     * @return returns the type of water available at the water source described by this report
     */
    public WaterType getSourceType() {
        return waterSourceType;
    }

    /**
     * Returns the condition of this WaterSourceReport
     * @return returns the condition of water available at the water source described by this report
     */
    public WaterCondition getCondition() {
        return sourceCondition;
    }

    /**
     * Returns the date of submission of this water source report as a String
     * @return Returns a
     */
    public String getDateString() {
        DateFormat format = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss aa");
        return "Date: " + format.format(sourceReportDate);
    }

    /**
     * Returns the unique integer id of this WaterSourceReport
     * @return Returns the unique integer id of this WaterSourceReport
     */
    public int getId() {
        return this.reportNumber;
    }

    /**
     * Returns a String representation of the readable information contained in this WaterSourceReport
     * @return Returns a String representation of the readable information contained in this WaterSourceReport
     */
    public String toString() {
        return "id: " + reportNumber + ", " + getSourceTypeDescription() + ", "
                + getSourceConditionDescription();
    }
}
