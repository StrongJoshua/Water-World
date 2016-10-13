package edu.gatech.scrumbags.model;

/**
 * List of possible water conditions.
 *
 * @author gnoziere
 */
public enum WaterCondition {
	Waste("Waste water"), Treatable_Clear("Treatable water (clear)"), Treatable_Muddy("Treatable water (muddy)"), Potable(
		"Potable water");

	private final String repr;

	WaterCondition (String repr) {
		this.repr = repr;
	}

	@Override public String toString () {
		return repr;
	}
}
