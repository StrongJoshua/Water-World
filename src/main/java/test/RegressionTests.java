
package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import edu.gatech.scrumbags.model.WaterCondition;
import edu.gatech.scrumbags.model.WaterLocation;
import edu.gatech.scrumbags.model.WaterPurityCondition;
import edu.gatech.scrumbags.model.WaterPurityReport;
import edu.gatech.scrumbags.model.WaterSourceReport;
import edu.gatech.scrumbags.model.WaterType;

/** @author Beau */
public class RegressionTests {
    WaterSourceReport testReport;

    @Before
    public void initialize () {
        testReport = new WaterSourceReport(new WaterLocation(10, 15), WaterType.Bottled, WaterCondition.Potable, "Beaux");
        testReport.addPurityReport(
            new WaterPurityReport(testReport, new Date(2012 - 1900, 5, 20), WaterPurityCondition.Safe, 100, 50, "Alvin"));
        testReport.addPurityReport(
            new WaterPurityReport(testReport, new Date(2014 - 1900, 5, 1), WaterPurityCondition.Unsafe, 77, 77, "Kevin"));
        testReport.addPurityReport(
            new WaterPurityReport(testReport, new Date(2011 - 1900, 5, 4), WaterPurityCondition.Treatable, 88, 84, "David"));
        testReport.addPurityReport(
            new WaterPurityReport(testReport, new Date(2012 - 1900, 6, 20), WaterPurityCondition.Treatable, 85, 32, "Bob"));
        testReport.addPurityReport(
            new WaterPurityReport(testReport, new Date(2012 - 1900, 4, 20), WaterPurityCondition.Safe, 14, 16, "Yolo"));
        testReport.addPurityReport(
            new WaterPurityReport(testReport, new Date(2012 - 1900, 5, 15), WaterPurityCondition.Safe, 20, 14, "Aubrey"));
    }

    @Test
    public void testGetPurityReportsByMonth () {
        try {
            testReport.getPurityReportsByMonth(2012, 12);
            assertFalse(true);
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        }
        assertEquals(2, testReport.getPurityReportsByMonth(2012, 5).size());
        assertEquals(1, testReport.getPurityReportsByMonth(2014, 5).size());
        assertEquals(1, testReport.getPurityReportsByMonth(2011, 5).size());
        assertEquals(WaterPurityCondition.Treatable, testReport.getPurityReportsByMonth(2012, 6).get(0).getPurityCondition());
        assertEquals("David", testReport.getPurityReportsByMonth(2011, 5).get(0).getSubmitterName());
    }
}
