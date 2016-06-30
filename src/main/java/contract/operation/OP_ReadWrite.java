package contract.operation;

import contract.assets.Const;
import contract.wrapper.Locator;
import contract.wrapper.Operation;

import java.util.Arrays;
import java.util.HashMap;

/**
 * A primitive operation from which all other operations on data structures may be
 * constructed.
 *
 * @author Richard Sundqvist
 */
public abstract class OP_ReadWrite extends Operation {

    /**
     * Version number for this class.
     */
    private static final long serialVersionUID = Const.VERSION_NUMBER;

    /**
     * Create a new ReadWrite operation. Note that you must set the target, source and
     * value.
     *
     * @param operation The name of the operation. Should be "read" or "write".
     */
    public OP_ReadWrite (OperationType operation) {
        super(operation, new HashMap<Key, Object>(), null, null);
    }

    public OP_ReadWrite (OperationType operation, String source, int[] sourceRows) {
        super(operation, new HashMap<Key, Object>(), source, sourceRows);
    }

    /**
     * Set the target variable for this ReadWrite operation. The identifier of the
     * variable should be previously declared in the header.
     *
     * @param target The target variable for this ReadWrite operation.
     */
    public void setTarget (Locator target) {
        body.put(Key.target, target);
    }

    /**
     * Set the source variable for this ReadWrite operation. The identifier of the
     * variable should be previously declared in the header.
     *
     * @param source The source variable for this ReadWrite operation.
     */
    public void setSource (Locator source) {
        body.put(Key.source, source);
    }

    /**
     * Set the value(s) which were ReadWrite from {@code source}. This should be the value
     * of {@code target} and the specified index after operation execution, if applicable.
     *
     * @param value Set the value(s) which were ReadWrite from {@code source}.
     */
    public void setValue (double[] value) {
        body.put(Key.value, value);
    }

    @Override
    public String toString () {
        Locator source = getSource();
        Locator target = getTarget();
        String sourceStr;
        String targetStr;
        // Source and target known
        if (source != null && target != null) {
            sourceStr = source.toString();
            targetStr = target.toString();
            // Assume source or target known.
        } else {
            double[] value = getValue();
            String valueStr = value == null ? "?" : Arrays.toString(value);
            // Source unknown
            if (source == null) {
                sourceStr = valueStr;
                targetStr = target.toString();
                // target <-- values
                return super.operation.toString().toUpperCase() + ": " + targetStr + " <-- " + sourceStr;
            }
            // Target unknown
            else {
                sourceStr = source.toString();
                targetStr = valueStr;
            }
        }
        // source <-- target || values
        return super.operation.toString().toUpperCase() + ": " + sourceStr + " --> " + targetStr;
    }

    public Locator getTarget () {
        return (Locator) body.get(Key.target);
    }

    public Locator getSource () {
        return (Locator) body.get(Key.source);
    }

    public double[] getValue () {
        return (double[]) body.get(Key.value);
    }
}
