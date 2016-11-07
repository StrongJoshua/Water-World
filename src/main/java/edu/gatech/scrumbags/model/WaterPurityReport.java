package edu.gatech.scrumbags.model;

import edu.gatech.scrumbags.fxapp.MainFXApplication;

import java.util.Date;

/**
 * Water purity report class to M9 specification.
 * Note: In this implementation a reference to an associated Source
 * is held in each WaterPurityReport, so location and Water type are
 * accessible indirectly via getSource().
 * <p>
 * Specification from wiki:
 * <p>
 * Water Purity Report
 * A real water report would need to address multiple bacteria/virus and chemical contaminants. For our basic application, we assume that parts-per-million (PPM) is our measure and we will lump everything into two categories: Viruses and Contaminants.
 * <p>
 * 1. Date and time of the report (can be autogenerated by application)
 * 2. Report Number (must be autogenerated by application)
 * 3. Name of Worker (can be autogenerated from worker information)
 * 4. Location of water (manually entered. using any kind of location services or gps is extra credit).
 * 5. Overall Condition (Safe / Treatable / Unsafe)
 * 6. Virus PPM
 * 7. Contaminant PPM
 * <p>
 * Created by Beau on 10/9/2016.
 */
public class WaterPurityReport extends WaterReport {
	//TODO To remove
	//private WaterSourceReport waterSource;
	private WaterPurityCondition purityCondition;
	private double virusPPM;
	private double contaminantPPM;

	/**
	 * Constructs a new WaterSourceReport, auto-generates the current java.util.Date
	 *
	 * @param source          the Water source associated with this purity report
	 * @param purityCondition the overall condition of the water at this source
	 * @param virusPPM        the virus concentration at this water source
	 * @param contaminantPPM  the contaminant concentration at this water source
	 * @param submitterName   the full name of the submitter of this WaterSourceReport
	 */
	public WaterPurityReport (WaterSourceReport source, WaterPurityCondition purityCondition, double virusPPM,
		double contaminantPPM, String submitterName) {
		super(source.getLocation(), submitterName);
		this.purityCondition = purityCondition;
		this.virusPPM = virusPPM;
		this.contaminantPPM = contaminantPPM;
	}

	/**
	 * Constructs a new WaterSourceReport
	 *
	 * @param source           the Water source associated with this purity report
	 * @param purityCondition  the overall condition of the water at this source
	 * @param virusPPM         the virus concentration at this water source
	 * @param contaminantPPM   the contaminant concentration at this water source
	 * @param submitterName    the full name of the submitter of this WaterSourceReport
	 * @param purityReportDate the date and time this report was submitted as a java.util.Date object
	 */
	public WaterPurityReport (WaterSourceReport source, Date purityReportDate,
		WaterPurityCondition purityCondition, double virusPPM, double contaminantPPM, String submitterName) {
		super(source.getLocation(), purityReportDate, submitterName);
		//this.waterSource = source;
		//this.waterSource = MainFXApplication.purityMap.get(this);
		this.purityCondition = purityCondition;
		this.virusPPM = virusPPM;
		this.contaminantPPM = contaminantPPM;
	}

	/**
	 * Returns the condition of this WaterPurityReport
	 *
	 * @return returns the condition of water available at the water source described by this report
	 */
	public WaterPurityCondition getPurityCondition () {
		return purityCondition;
	}

	/**
	 * Return a String description of the condition of this water source
	 *
	 * @return labeled String description of the general condition of this water source
	 */
	public String getPurityConditionDescription () {
		return purityCondition.toString();
	}

	/**
	 * @return the virus concentration from this purity report
	 */
	public double getVirusPPM () {
		return virusPPM;
	}

	/**
	 * @return the contaminant concentration from this purity report
	 */
	public double getContaminantPPM () {
		return contaminantPPM;
	}

	/**
	 * Returns a String representation of the readable information contained in this WaterPurityReport
	 *
	 * @return Returns a String representation of the readable information contained in this WaterPurityReport
	 */
	public String toString () {
		return super.toString() + ", Condition: " + getPurityConditionDescription() + ", Virus PPM: "
			+ getVirusPPM() + ", Contaminant PPM: " + getContaminantPPM();
	}
}
