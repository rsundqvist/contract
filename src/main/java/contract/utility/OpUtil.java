package contract.utility;

import contract.json.Locator;
import contract.json.Operation;
import contract.operation.HighLevelOperation;
import contract.operation.Key;
import contract.operation.OP_ReadWrite;
import contract.operation.OperationType;

import java.util.List;

/**
 * Utility class for operations.
 *
 * @author Richard Sundqvist
 */
public abstract class OpUtil {

    public static Locator getLocator (Operation op, Key locatorKey) {
        Locator ans = null;
        switch (locatorKey) {
            case source:
            case target:
            case var1:
            case var2:
                ans = (Locator) op.body.get(locatorKey);
            default:
                break;
        }

        return ans;
    }

    public static String getIdentifier (Operation op) {
        return (String) op.body.get(Key.identifier);
    }

    public static double[] getValue (Operation op) {
        return (double[]) op.body.get(Key.value);
    }

    public static int[] getIndex (Operation op) {
        return (int[]) op.body.get(Key.index);
    }

    public static int[] getSize (Operation op) {
        return (int[]) op.body.get(Key.size);
    }

    public static OperationType getOperationTyope (Operation op) {
        return (OperationType) op.body.get(Key.operation);
    }

    /**
     * Returns {@code true} if the operation is of type read, write, or message.
     *
     * @return {@code true} if the operation is atomic, {@code false} otherwise.
     */
    public static boolean isAtomic (Operation op) {
        return op.operation.numAtomicOperations < 2;
    }

    /**
     * Returns true if the operation is a read or write operation, thus being capable of
     * inheriting OP_ReadWrite.
     *
     * @param op The operation to test.
     * @return True if the operation is a read/write operation. False otherwise.
     */
    public static boolean isReadOrWrite (Operation op) {
        return op.operation == OperationType.read || op.operation == OperationType.write;
    }

    /**
     * Attempt to estimate the origin of a HighLevelOperation.
     */
    public static void guessSource (HighLevelOperation hlo) {
        List<OP_ReadWrite> rwList = hlo.atomicOperations;

        if (rwList == null || rwList.isEmpty()) {
            System.err.println("Error in OpUtil.guessSource(): Bad rwList: " + rwList);
            return;
        }

        String source = rwList.get(0).source;
        if (source == null) {
            System.err.println("Error in OpUtil.guessSource(): Bad source: null");
            return;
        }

        int[] firstOpLines = rwList.get(0).sourceRows;
        if (firstOpLines == null || firstOpLines.length == 0) {
            System.err.println("Error in OpUtil.guessSource(): Bad sourceRows: " + firstOpLines);
            return;
        }

        int[] lastOpLines = rwList.get(rwList.size() - 1).sourceRows;
        if (lastOpLines == null || lastOpLines.length == 0) {
            System.err.println("Error in OpUtil.guessSource(): Bad sourceRows: " + lastOpLines);
            return;
        }

        int start = firstOpLines[0];
        int end = lastOpLines[lastOpLines.length - 1];

        int[] sourceRows = new int[]{start, end};

        hlo.source = source;
        hlo.sourceRows = sourceRows;
    }
}
