package contract.operation;

import javafx.scene.paint.Color;

/**
 * The name of the operation.
 */
public enum OperationType {

    // ============================================================= //
    /*
     * Atomic operations
     */
    // ============================================================= //
    read(Color.GREEN, 1), write(Color.RED, 1), message(null, 0),

    // ============================================================= //
    /*
     * Non-atomic operations
     */
    // ============================================================= //
    remove(null, 1), swap(Color.BLUEVIOLET, 3);

    // ============================================================= //
    /*
     *
     * Field variables
     *
     */
    // ============================================================= //

    /**
     * The color associated with the type.
     */
    public final Color color;

    /**
     * The number of atomic operations this OperationType consists of. 1 signifies that
     * the operation is atomic, 0 indicates that its execution will not affect any data
     * structures.
     */
    public final int numAtomicOperations;

    OperationType (Color color, int numOperations) {
        numAtomicOperations = numOperations;
        this.color = color;
    }
}
