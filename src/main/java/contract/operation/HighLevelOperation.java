package contract.operation;

import contract.assets.Const;
import contract.wrapper.Operation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Used by the Interpreter to merge groups of r/w operations into high-level operations.
 *
 * @author Richard Sundqvist
 */
public abstract class HighLevelOperation extends Operation {

    private static final long serialVersionUID = Const.VERSION_NUMBER;

    /**
     * The atomic operations which created this HighLevelOperations.
     */
    public final List<OP_ReadWrite> atomicOperations;

    /**
     * /** Create a new Operation.
     *
     * @param operation The literal name of the operation, such as "read".
     * @param source The source file this operation originates from.
     */
    public HighLevelOperation (OperationType operation, String source, int[] sourceRows) {
        super(operation, new HashMap<Key, Object>(), source, sourceRows);

        atomicOperations = new ArrayList<OP_ReadWrite>(operation.numAtomicOperations);
    }

    /**
     * Attempt to consolidate a list of read/write operations. The method should behave as
     * it were static, that is the state of the calling initialisation should not matter.
     * If successful, the operations contain in {@code rwList} will be placed in the
     * {@link #atomicOperations} list of the newly created operation.
     *
     * @param rwList The list to attempt consolidation on.
     * @return A high level operation if the supplied list could be consolidated, null
     * otherwise.
     */
    public abstract HighLevelOperation consolidate (List<OP_ReadWrite> rwList);
}
