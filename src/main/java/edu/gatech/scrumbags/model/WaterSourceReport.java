package edu.gatech.scrumbags.model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Water source report class containing a location, description, report date, and relevant methods.
 * Created by Beau on 10/9/2016.
 */
public class WaterSourceReport extends WaterReport {
    private WaterType sourceType;
    private WaterCondition sourceCondition;
    private List<WaterPurityReport> purityReports;

    /**
     * Constructs a new WaterSourceReport that was submitted at the moment this object was constructed
     * @param location the WaterLocation of this WaterSourceReport
     * @param sourceType the WaterType of the water at this water source
     * @param sourceCondition the WaterCondition of the water at this water source
     * @param submitterName the full name of the submitter of this WaterSourceReport
     */
    public WaterSourceReport(WaterLocation location, WaterType sourceType,
                             WaterCondition sourceCondition, String submitterName) {
        super(location, submitterName);
        this.sourceType = sourceType;
        this.sourceCondition = sourceCondition;
        purityReports = new ArrayList<>();
    }

    /**
     * Constructs a new WaterSourceReport
     * @param location the WaterLocation of this WaterSourceReport
     * @param sourceType the WaterType of the water at this water source
     * @param sourceCondition the WaterCondition of the water at this water source
     * @param submitterName the full name of the submitter of this WaterSourceReport
     * @param sourceReportDate the date and time this report was submitted as a java.util.Date object
     */
    public WaterSourceReport(WaterLocation location, Date sourceReportDate, WaterType sourceType, WaterCondition sourceCondition, String submitterName) {
        super(location, sourceReportDate, submitterName);
        this.sourceType = sourceType;
        this.sourceCondition = sourceCondition;
        purityReports = new ArrayList<>();
    }

    public List<WaterPurityReport> getPurityReports () {
        return purityReports;
    }

    public void addPurityReport (WaterPurityReport purityReport) {
        this.purityReports.add(purityReport);
    }

    /**
     * Returns a description of the source type of this water source
     * @return String representation of the Water Source Type of this source
     */
    public String getSourceTypeDescription() {
        return sourceType.toString();
    }

    /**
     * Return a String description of the condition of this water source
     * @return labeled String description of the general condition of this water source
     */
    public String getSourceConditionDescription() {
        return sourceCondition.toString();
    }

    /**
     * Returns the WaterType of this WaterSourceReport
     * @return returns the type of water available at the water source described by this report
     */
    public WaterType getSourceType() {
        return sourceType;
    }

    /**
     * Returns the condition of this WaterSourceReport
     * @return returns the condition of water available at the water source described by this report
     */
    public WaterCondition getCondition() {
        return sourceCondition;
    }

    /**
     * Returns a String representation of the readable information contained in this WaterSourceReport
     * @return Returns a String representation of the readable information contained in this WaterSourceReport
     */
    public String toString() {
        return super.toString() + ", Type: " + getSourceTypeDescription() + ", Condition: " + getSourceConditionDescription();
    }
}
