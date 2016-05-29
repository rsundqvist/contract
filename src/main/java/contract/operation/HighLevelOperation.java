package contract.operation;

import java.util.HashMap;
import java.util.List;

import contract.assets.Const;
import contract.json.Operation;

/**
 * Used by the Interpreter to merge groups of r/w operations into high-level operations.
 *
 * @author Richard Sundqvist
 *
 */
public abstract class HighLevelOperation extends Operation {

    private static final long serialVersionUID = Const.VERSION_NUMBER;

    /**
     * /** Create a new Operation.
     *
     * @param operation
     *            The literal name of the operation, such as "read".
     * @param operationBody
     *            A map containing the identifier of the field (such as "destination" or
     *            "value") and the data they contained.
     * @param source
     *            The source file this operation originates from.
     * @param beginLine
     *            The first line this operation originates from.
     * @param endLine
     *            The last line this operation originates from.
     * @param beginColumn
     *            The first column this operation originates from.
     * @param endColumn
     *            The last line column operation originates from.
     */
    public HighLevelOperation (OperationType operation, HashMap<Key, Object> operationBody, String source, int beginLine,
            int endLine, int beginColumn, int endColumn) {
        super(operation, new HashMap<Key, Object>(), source, beginLine, endLine, beginColumn, endColumn);
    }

    /**
     * Attempt to consolidate a list of read/write operations. The method should behave as
     * it were static, that is the state of the calling initialisation should not matter.
     *
     * @param rwList
     *            The list to attempt consolidation on.
     * @return A high level operation if the supplied list could be consolidated, null
     *         otherwise.
     */
    public abstract Operation consolidate (List<OP_ReadWrite> rwList);
}
