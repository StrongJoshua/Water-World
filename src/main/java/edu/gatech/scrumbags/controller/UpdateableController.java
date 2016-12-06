
package edu.gatech.scrumbags.controller;

import java.util.List;

import edu.gatech.scrumbags.model.WaterReport;

/** Interface to allow a dynamic multi-user environment.
 * @author Jan Risse */
public interface UpdateableController {
    /** Dynamically displays the new reports.
     * @param newReports The new reports to display. */
    public void updateReports (List<WaterReport> newReports);
}
