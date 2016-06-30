package contract.wrapper;

import contract.assets.Const;
import contract.operation.Key;
import contract.operation.OperationType;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Abstract wrapper class containing the necessary data to recreate an operation.
 */
public abstract class Operation implements Serializable {

    /**
     * Version number for this class.
     */
    private static final long serialVersionUID = Const.VERSION_NUMBER;

    // ============================================================= //
    /*
     *
     * Field variables
     *
     */
    // ============================================================= //
    /**
     * The literal name of the operation.
     */
    public OperationType operation;
    /**
     * A map containing the identifier of the field (such as "target" or "value") and the
     * data they contained.
     */
    public Map<Key, Object> body;
    /**
     * The name of the source file this Operation originates from.
     */
    public String source;
    /**
     * The row(s) the operation originates from.
     */
    public int[] sourceRows;
    /**
     * The logical execution order for the operation. {@code Negative} values are generally ignored.
     * Duplicates are permitted. All operations with a
     * given value may be executed and visualized in one batch.
     */
    public int group;

    // ============================================================= //
    /*
     *
     * Constructors
     *
     */
    // ============================================================= //

    /**
     * Create a new Operation.
     *
     * @param operation The literal name of the operation, such as {@link OperationType#read}.
     * @param body A map containing the operands of the operation.
     * @param source The source file this operation originates from.
     * @param sourceRows The row(s) the operation originates from.
     */
    public Operation (OperationType operation, HashMap<Key, Object> body, String source, int[] sourceRows) {
        this.operation = operation;
        this.body = body;
        this.source = source;
        this.sourceRows = sourceRows;
        this.group = -1;
    }

    // ============================================================= //
    /*
     *
     * Miscellaneous
     *
     */
    // ============================================================= //

    public String toSimpleString () {
        return operation.toString().toUpperCase();
    }

    @Override
    public String toString () {
        return body == null ? "null" : body.toString();
    }
}
