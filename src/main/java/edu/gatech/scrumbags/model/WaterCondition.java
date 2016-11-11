
package edu.gatech.scrumbags.model;

/** List of possible water conditions.
 *
 * @author gnoziere */
public enum WaterCondition {
    Waste("Waste Water"), Treatable_Clear("Treatable (Clear)"), Treatable_Muddy("Treatable (Muddy)"), Potable("Potable Water");

    private final String repr;

    WaterCondition (String repr) {
        this.repr = repr;
    }

    @Override
    public String toString () {
        return repr;
    }
}
