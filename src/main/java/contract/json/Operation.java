package contract.json;

import contract.assets.Const;
import contract.operation.Key;
import contract.operation.OperationType;

import java.io.Serializable;
import java.util.HashMap;

/**
 * Abstract wrapper class containing the necessary data to recreate a given operation.
 * Should be inherited to create specific operations.
 */
public class Operation implements Serializable {

    /**
     * Version number for this class.
     */
    private static final long serialVersionUID = Const.VERSION_NUMBER;
    /**
     * The literal name of the operation.
     */
    public final OperationType operation;
    /**
     * A map containing the identifier of the field (such as "target" or "value") and the
     * data they contained.
     */
    public final HashMap<Key, Object> body;
    /**
     * The name of the source file this Operation originates from.
     */
    public String source;
    /**
     * The row(s) the operation originates from.
     */
    public int[] sourceRows;

    /**
     * /** Create a new Operation.
     *
     * @param operation The literal name of the operation, such as "init" (initialize) or
     * "read".
     * @param body A map containing the identifier of the field (such as "destination" or
     * "value") and the data they contained.
     * @param source The source file this operation originates from.
     */
    public Operation (OperationType operation, HashMap<Key, Object> body, String source, int[] sourceRows) {
        this.operation = operation;
        this.body = body;
        this.source = source;
        this.sourceRows = sourceRows;
    }

    public String toSimpleString () {
        return operation.toString().toUpperCase();
    }

    @Override
    public String toString () {
        return body == null ? "null" : body.toString();
    }
}
