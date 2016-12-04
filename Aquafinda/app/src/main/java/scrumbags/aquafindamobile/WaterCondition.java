
package scrumbags.aquafindamobile;

/** List of possible water conditions.
 *
 * @author gnoziere */
public enum WaterCondition {
    Waste("Waste Water"), Treatable_Clear("Treatable (Clear)"), Treatable_Muddy("Treatable (Muddy)"), Potable("Potable Water");

    private final String representation;

    WaterCondition (String representation) {
        this.representation = representation;
    }

    @Override
    public String toString () {
        return representation;
    }
}
