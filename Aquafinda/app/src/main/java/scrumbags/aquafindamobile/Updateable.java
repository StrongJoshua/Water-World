package scrumbags.aquafindamobile;

import java.util.List;

/**
 * Created by rajmi on 12/6/2016.
 */

public interface Updateable {
	/** Dynamically displays the new reports.
	 * @param newReports The new reports to display. */
	public void update(List<WaterReport> newReports);
}