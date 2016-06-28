package contract.utility;

import contract.json.Locator;
import contract.json.Operation;
import contract.operation.HighLevelOperation;
import contract.operation.Key;
import contract.operation.OP_ReadWrite;
import contract.operation.OperationType;

import java.util.ArrayList;
import java.util.List;

/**
 * Utility class for operations.
 *
 * @author Richard Sundqvist
 */
public abstract class OpUtil {

    /**
     * Attempts to locate and return the locator identified by {@code key} from {@link Operation#body}.
     *
     * @param op The operation to retrieve a locator from.
     * @param key The key for the locator.
     * @return A Locator if it could be found, {@code null} otherwise.
     * @throws IllegalArgumentException If the type found in the body was not a Locator.
     */
    public static Locator getLocator (Operation op, Key key) {
        Object ans;

        ans = op.body.get(key);

        if (ans != null && !(ans instanceof Locator)) {
            throw new IllegalArgumentException("Bad key or value:" +
                    "The key \"" + key + "\" returned an object of type " + ans.getClass());
        }

        return (Locator) ans;
    }

    /**
     * Returns the value, if any, carried by the operation.
     * @param op The operation to retrieve the value from.
     * @return An array of value(s).
     */
    public static double[] getValue (Operation op) {
        return (double[]) op.body.get(Key.value);
    }

    /**
     * Returns {@code true} if the operation is atomic ({@link OperationType#numAtomicOperations} < 2).
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

    /**
     * Converts any {@link HighLevelOperation} found into a group of low level
     * operations.
     *
     * @param mixedList A list of atomic and high level operations.
     * @return A list a atomic operations.
     */
    public static List<Operation> asAtomicList (List<Operation> mixedList) {
        List<Operation> answer = new ArrayList<>(mixedList.size() * 2);
        Math.abs(-1);
        for (Operation op : mixedList) {
            if (op.operation.numAtomicOperations > 1) {
                HighLevelOperation hlo = (HighLevelOperation) op;

                if (hlo.atomicOperations == null || hlo.atomicOperations.size() != hlo.operation.numAtomicOperations) {
                    System.err.println("WARNING: Bad atomic operations list: "
                            + hlo.atomicOperations + " in operation: " + hlo + ", at index: " + mixedList.indexOf(hlo));
                    answer.add(hlo);
                } else {
                    answer.addAll(hlo.atomicOperations);
                }

            } else {
                answer.add(op);
            }
        }

        return answer;
    }
}
