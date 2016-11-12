package test;

import edu.gatech.scrumbags.model.*;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Tests getPurityReportsByYear() in WaterSourceReport
 *
 * @author Kevin Lun
 */
public class PurityReportsByYearTests {

	WaterSourceReport testReport;

	@Before
	public void initialize () {
		testReport = new WaterSourceReport(new WaterLocation(73, 62), WaterType.Lake, WaterCondition.Treatable_Clear, "Kevin");

		WaterPurityReport purityReport1 = new WaterPurityReport(testReport, new Date(2000 - 1900, 3, 11), WaterPurityCondition.Treatable, 43, 62, "Beau");
		WaterPurityReport purityReport2 = new WaterPurityReport(testReport, new Date(2001 - 1900, 4, 1), WaterPurityCondition.Safe, 27, 58, "Guillaume");
		WaterPurityReport purityReport3 = new WaterPurityReport(testReport, new Date(2001 - 1900, 4, 15), WaterPurityCondition.Unsafe, 90, 72, "Jan");
		WaterPurityReport purityReport4 = new WaterPurityReport(testReport, new Date(2002 - 1900, 6, 7), WaterPurityCondition.Treatable, 81, 63, "Rishi");
		WaterPurityReport purityReport5 = new WaterPurityReport(testReport, new Date(2001 - 1900, 8, 22), WaterPurityCondition.Treatable, 53, 76, "David");
		WaterPurityReport purityReport6 = new WaterPurityReport(testReport, new Date(20017 - 1900, 4, 8), WaterPurityCondition.Safe, 33, 92, "Connor");

		testReport.addPurityReport(purityReport1);
		testReport.addPurityReport(purityReport2);
		testReport.addPurityReport(purityReport3);
		testReport.addPurityReport(purityReport4);
		testReport.addPurityReport(purityReport5);
		testReport.addPurityReport(purityReport6);
	}

	@Test
	public void testGetPurityReportsFromFuture () {
		try {
			testReport.getPurityReportsByYear(2017);
			assertFalse(true);
		} catch (IllegalArgumentException e) {
			assertTrue(true);
		}
	}

	@Test
	public void testGetPurityReportFromYearSingle () {
		assertEquals(1, testReport.getPurityReportsByYear(2000).size());
	}

	@Test
	public void testGetPurityReportFromYearMultiple () {
		assertEquals(3, testReport.getPurityReportsByYear(2001).size());
	}

	@Test
	public void testGetPurityReportFromYearContents () {

		List<WaterPurityReport> actual = testReport.getPurityReportsByYear(2001);

		List<WaterPurityReport> expected = new ArrayList<>();
		expected.add(new WaterPurityReport(testReport, new Date(2001 - 1900, 4, 1), WaterPurityCondition.Safe, 27, 58, "Guillaume"));
		expected.add(new WaterPurityReport(testReport, new Date(2001 - 1900, 4, 15), WaterPurityCondition.Unsafe, 90, 72, "Jan"));
		expected.add(new WaterPurityReport(testReport, new Date(2001 - 1900, 8, 22), WaterPurityCondition.Treatable, 53, 76, "David"));

		assertEquals(actual, expected);
	}
}
