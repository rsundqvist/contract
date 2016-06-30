package contract.utility;

import contract.datastructure.Array;
import contract.datastructure.DataStructure;
import contract.datastructure.IndependentElement;
import contract.wrapper.AnnotatedVariable;
import contract.operation.Key;

/**
 * Contains methods to parse data structures. Cannot be instantiated.
 *
 * @author Richard Sundqvist
 */
public abstract class StructParser {

    private StructParser () {
    }

    ;

    public static DataStructure unpackAnnotatedVariable (AnnotatedVariable av) {
        switch (av.rawType) {
            case array:
                return unpackArray(av);
            case independentElement:
                return unpackIndependentElement(av);
            case tree:
                return null; // TODO: Add parsing of trees.
            default:
                System.err.println("Unknown Data Structure raw type: \"" + av.rawType + "\"");
                break;
        }
        return null;
    }

    /**
     * Unpack an IndependentElement data structure variable.
     *
     * @param av The variable to unpack.
     * @return An unpacked IndependentElement.
     */
    private static IndependentElement unpackIndependentElement (AnnotatedVariable av) {
        return new IndependentElement(av.identifier, av.abstractType, av.visual, av.attributes);
    }

    /**
     * Unpack an Array data structure variable.
     *
     * @param av The variable to unpack.
     * @return An unpacked Array.
     */
    public static Array unpackArray (AnnotatedVariable av) {
        return new Array(av.identifier, av.abstractType, av.visual, av.attributes);
    }

    public static int[] parseSize (AnnotatedVariable av) {
        return OpParser.ensureIntArray(av.attributes.get(Key.size.name()));
    }
}
