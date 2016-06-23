package contract.operation;

import contract.assets.Const;
import contract.json.Locator;

/**
 * Create a new Write operation.
 */
public class OP_Write extends OP_ReadWrite {

    /**
     * Version number for this class.
     */
    private static final long serialVersionUID = Const.VERSION_NUMBER;
    private static final OperationType OPERATION = OperationType.write;

    /**
     * Create a new Write operation. Note that you must set the target, source and value.
     */
    public OP_Write () {
        super(OPERATION);
    }

    public OP_Write (String source, int[] sourcerOWS) {
        super(OPERATION, source, sourcerOWS);
    }

    /**
     * Set the target variable for this Write operation. The identifier of the variable
     * should be previously declared in the header.
     *
     * @param target The target variable for this Write operation.
     */
    @Override
    public void setTarget (Locator target) {
        if (target == null) {
            System.err.println("Target null in Write operation!");
        }
        body.put(Key.target, target);
    }

    /**
     * Set the source variable for this Write operation. The identifier of the variable
     * should be previously declared in the header.
     *
     * @param source The source variable for this Write operation.
     */
    @Override
    public void setSource (Locator source) {
        body.put(Key.source, source);
    }

    /**
     * Set the value(s) which were written to {@code target} (from {@code source}, if
     * applicable).
     *
     * @param value Set the value(s) written to {@code target}.
     */
    @Override
    public void setValue (double[] value) {
        body.put(Key.value, value);
    }

    @Override
    public Locator getTarget () {
        return (Locator) body.get(Key.target);
    }

    @Override
    public Locator getSource () {
        return (Locator) body.get(Key.source);
    }

    @Override
    public double[] getValue () {
        return (double[]) body.get(Key.value);
    }
}
