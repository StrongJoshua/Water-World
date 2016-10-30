package edu.gatech.scrumbags.model;

/**
 * Created by Beau on 10/30/2016.
 */
abstract class WaterReport {
    static int reportCount = 0;
    private int reportId;

    WaterReport() {
        this.reportId = reportCount++;
    }

    public int getId() {
        return reportId;
    }
}
