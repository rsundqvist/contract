package contract.assets;

/**
 * Utility class to reduce clutter.
 *
 * @author Richard Sundqvist
 */
public abstract class Tools {

    private Tools () {
    } // Not to be instantiated.

    /**
     * Tries to simplify the variable name. For example, {@code
     * "package.subpackage.class:var <space> name"} becomes {@code "name"}.
     *
     * @param orig A string to simplify.
     * @return A simplified variable name (hopefully).
     */
    public static String stripQualifiers (String orig) {
        String a[] = orig.split("\\p{Punct}");
        a = a[a.length - 1].split(" ");
        return a[a.length - 1];
    }
}
