package contract.datastructure;

/**
 * The abstract type of the data structure, if applicable.
 *
 * @author Richard Sundqvist
 *
 */
public enum AbstractType {
    tree("Tree", "tree"); // A tree with n children and one parent.

    public transient final String pretty;
    public transient final String json;

    private AbstractType (String pretty, String json) {
        this.pretty = pretty;
        this.json = json;
    }

    @Override
    public String toString () {
        return pretty;
    }

    /**
     * Parse a json string.
     *
     * @param json
     *            The string to parse
     * @return The corresponding RawType, if applicable. Null otherwise.
     */
    public static AbstractType fromString (String json) {
        for (AbstractType at : AbstractType.values()) {
            if (at.json.equals(json)) {
                return at;
            }
        }
        return null;
    }
}