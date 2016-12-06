
package edu.gatech.scrumbags.model;

import java.util.List;

/** Interface to allow a dynamic multi-user environment.
 * @author Jan Risse */
public interface UpdateableController {
    /** Dynamically displays the new reports.
     * @param newReports The new reports to display. */
    public void updateReports (List<WaterReport> newReports);
}
