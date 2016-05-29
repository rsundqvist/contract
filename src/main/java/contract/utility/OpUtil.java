package contract.utility;

import contract.json.Locator;
import contract.json.Operation;
import contract.operation.Key;
import contract.operation.OperationType;

/**
 * Utility class for operations.
 *
 * @author Richard Sundqvist
 *
 */
public abstract class OpUtil {

    public static Locator getLocator (Operation op, Key locatorKey) {
        Locator ans = null;
        switch (locatorKey) {
        case source:
        case target:
        case var1:
        case var2:
            ans = (Locator) op.operationBody.get(locatorKey);
        default:
            break;
        }

        return ans;
    }

    public static String getIdentifier (Operation op) {
        return (String) op.operationBody.get(Key.identifier);
    }

    public static double[] getValue (Operation op) {
        return (double[]) op.operationBody.get(Key.value);
    }

    public static int[] getIndex (Operation op) {
        return (int[]) op.operationBody.get(Key.index);
    }

    public static int[] getSize (Operation op) {
        return (int[]) op.operationBody.get(Key.size);
    }

    public static OperationType getOperationTyope (Operation op) {
        return (OperationType) op.operationBody.get(Key.operation);
    }

    /**
     * Returns {@code true} if the operation is of type read, write, or message.
     *
     * @param op
     * @return {@code true} if the operation is atomic, {@code false} otherwise.
     */
    public static boolean isAtomic (Operation op) {
        return op.operation.numAtomicOperations < 2;
    }

    /**
     * Returns true if the operation is a read or write operation, thus being capable of
     * inheriting OP_ReadWrite.
     *
     * @param op
     *            The operation to test.
     * @return True if the operation is a read/write operation. False otherwise.
     */
    public static boolean isReadOrWrite (Operation op) {
        return op.operation == OperationType.read || op.operation == OperationType.write;
    }

}
