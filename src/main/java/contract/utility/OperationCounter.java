package contract.utility;

import java.util.ArrayList;
import java.util.List;

import contract.operation.OperationType;
import javafx.beans.property.SimpleIntegerProperty;

/**
 * A class which counts performed operations.
 *
 * @author Richard Sundqvist
 *
 */
public class OperationCounter {

    private final SimpleIntegerProperty read    = new SimpleIntegerProperty(0);
    private final SimpleIntegerProperty write   = new SimpleIntegerProperty(0);
    private final SimpleIntegerProperty swap    = new SimpleIntegerProperty(0);
    private final SimpleIntegerProperty remove  = new SimpleIntegerProperty(0);
    private final SimpleIntegerProperty message = new SimpleIntegerProperty(0);

    /**
     * @return The reads property.
     */
    public SimpleIntegerProperty readsProperty () {
        return read;
    }

    /**
     * Returns the the Writes property.
     *
     * @return the Writes property.
     */
    public SimpleIntegerProperty writesProperty () {
        return write;
    }

    /**
     * Returns the the Swaps property.
     *
     * @return the Swaps property.
     */
    public SimpleIntegerProperty swapsProperty () {
        return swap;
    }

    /**
     * Returns the the Removes property.
     *
     * @return the Removes property.
     */
    public SimpleIntegerProperty removesProperty () {
        return remove;
    }

    /**
     * Returns the Messages property.
     *
     * @return the Messages property.
     */
    public SimpleIntegerProperty messagesProperty () {
        return message;
    }

    /**
     * Count an operation. Calls {@code countOperation(OperationType type)}.
     *
     * @param op
     *            The operation to count.
     */
    public void count (OperationType op) {
        countOperation(op);
    }

    /**
     * Count an operation type.
     *
     * @param type
     *            The count to count.
     */
    public void countOperation (OperationType type) {
        switch (type) {
        case message:
            message.setValue(message.getValue() + 1);
            break;
        case read:
            read.setValue(read.getValue() + 1);
            break;
        case remove:
            remove.setValue(remove.getValue() + 1);
            break;
        case swap:
            swap.setValue(swap.getValue() + 1);
            break;
        case write:
            write.setValue(write.getValue() + 1);
            break;
        default:
            break;

        }
    }

    /**
     * Returns the number of Read operations.
     *
     * @return The number of Read operations.
     */
    public int getReads () {
        return read.get();
    }

    /**
     * Returns the number of Writes operations.
     *
     * @return The number of Writes operations.
     */
    public int getWrites () {
        return write.get();
    }

    /**
     * Returns the number of Swap operations.
     *
     * @return The number of Swap operations.
     */
    public int getSwap () {
        return swap.get();
    }

    /**
     * Returns the number of Remove operations.
     *
     * @return The number of Remove operations.
     */
    public int getRemove () {
        return remove.get();
    }

    /**
     * Returns the number of Message operations.
     *
     * @return The number of Message operations.
     */
    public int getMessage () {
        return message.get();
    }

    /**
     * Reset the counter.
     */
    public void reset () {
        read.set(0);
        write.set(0);
        swap.set(0);
        remove.set(0);
        message.set(0);
    }

    /**
     * Returns a list of string on the format "OperationType: {@code count}".
     */
    public List<String> getStatistics () {
        ArrayList<String> stats = new ArrayList<String>();
        stats.add("Read: " + read.intValue());
        stats.add("Write: " + write.intValue());
        stats.add("Swap: " + swap.intValue());
        return stats;
    }

    /**
     * Interface for classes which count the {@link #Operation}'s performed on it.
     *
     * @author Richard Sundqvist
     *
     */
    public interface OperationCounterHaver {
        /**
         * Returns the OperationCounter for this OperationCounterHaver.
         *
         * @return An OperationCounter
         */
        public OperationCounter getCounter ();

        /**
         * Print stats for a OperationCounterHaver using the {@link #getStatistics()}
         * method.
         *
         * @param och
         *            The OperationCounterHaver whose counter should be used.
         */
        public static List<String> printStatistics (OperationCounterHaver och) {
            return och.getCounter().getStatistics();
        }
    }
}
