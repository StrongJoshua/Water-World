
package edu.gatech.scrumbags.model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/** Water source report class containing a location, description, report date, and relevant methods. Created by Beau on
 * 10/9/2016. */
public class WaterSourceReport extends WaterReport {
    private WaterType sourceType;
    private WaterCondition sourceCondition;
    private List<WaterPurityReport> purityReports;

    /** Constructs a new WaterSourceReport that was submitted at the moment this object was constructed
     *
     * @param location the WaterLocation of this WaterSourceReport
     * @param sourceType the WaterType of the water at this water source
     * @param sourceCondition the WaterCondition of the water at this water source
     * @param submitterName the full name of the submitter of this WaterSourceReport */
    public WaterSourceReport (WaterLocation location, WaterType sourceType, WaterCondition sourceCondition,
        String submitterName) {
        super(location, submitterName);
        this.sourceType = sourceType;
        this.sourceCondition = sourceCondition;
        purityReports = new ArrayList<>();
    }

    /** Constructs a new WaterSourceReport
     *
     * @param location the WaterLocation of this WaterSourceReport
     * @param sourceType the WaterType of the water at this water source
     * @param sourceCondition the WaterCondition of the water at this water source
     * @param submitterName the full name of the submitter of this WaterSourceReport
     * @param sourceReportDate the date and time this report was submitted as a java.util.Date object */
    public WaterSourceReport (WaterLocation location, Date sourceReportDate, WaterType sourceType, WaterCondition sourceCondition,
        String submitterName) {
        super(location, sourceReportDate, submitterName);
        this.sourceType = sourceType;
        this.sourceCondition = sourceCondition;
        purityReports = new ArrayList<>();
    }

    public List<WaterPurityReport> getPurityReports () {
        return purityReports;
    }

    public void addPurityReport (WaterPurityReport purityReport) {
        if (purityReports == null) purityReports = new ArrayList<>();
        this.purityReports.add(purityReport);
    }

    /** Returns purity reports created in a given year (0 - present)
     * @param year year of purity reports
     * @return list of purity reports from given month, or empty list if none found */
    public List<WaterPurityReport> getPurityReportsByYear (int year) {
        List<WaterPurityReport> reports = new ArrayList<>();
        Calendar cal = Calendar.getInstance();
        if (year > cal.get(Calendar.YEAR)) {
            throw new IllegalArgumentException("Year cannot be in the future");
        }
        for (WaterPurityReport report : purityReports) {
            cal.setTime(report.getReportDate());
            if (cal.get(Calendar.YEAR) == year) {
                reports.add(report);
            }
        }
        return reports;
    }

    /** Returns purity reports created in a given year (0 - present), month (0 - 11)
     * @param year year of purity reports
     * @param month month of purity reports
     * @return list of purity reports from given month, or empty list if none found */
    public List<WaterPurityReport> getPurityReportsByMonth (int year, int month) {
        if (month < 0 || month > 11) {
            throw new IllegalArgumentException("Month must be between 0 (January) and 11 (December)");
        }
        List<WaterPurityReport> reportsInYear = getPurityReportsByYear(year);
        List<WaterPurityReport> reports = new ArrayList<>();
        Calendar cal = Calendar.getInstance();
// if (year == cal.get(Calendar.YEAR) && month > cal.get(Calendar.MONTH)) {
// throw new IllegalArgumentException("Month cannot be in the future");
// }
        for (WaterPurityReport report : reportsInYear) {
            cal.setTime(report.getReportDate());
            if (cal.get(Calendar.YEAR) == year && cal.get(Calendar.MONTH) == month) {
                reports.add(report);
            }
        }
        return reports;
    }

    /** Returns a description of the source type of this water source
     *
     * @return String representation of the Water Source Type of this source */
    public String getSourceTypeDescription () {
        return sourceType.toString();
    }

    /** Return a String description of the condition of this water source
     *
     * @return labeled String description of the general condition of this water source */
    public String getSourceConditionDescription () {
        return sourceCondition.toString();
    }

    /** Returns the WaterType of this WaterSourceReport
     *
     * @return returns the type of water available at the water source described by this report */
    public WaterType getSourceType () {
        return sourceType;
    }

    /** Returns the condition of this WaterSourceReport
     *
     * @return returns the condition of water available at the water source described by this report */
    public WaterCondition getCondition () {
        return sourceCondition;
    }

    /** Returns a String representation of the readable information contained in this WaterSourceReport
     *
     * @return Returns a String representation of the readable information contained in this WaterSourceReport */
    public String toString () {
        return super.toString() + ", Type: " + getSourceTypeDescription() + ", Condition: " + getSourceConditionDescription();
    }
}
