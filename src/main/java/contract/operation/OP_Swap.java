package contract.operation;

import java.util.List;

import contract.assets.Const;
import contract.json.Locator;

/**
 * Create a new Swap operation, shifting the values of {@code var1} and {@code var2}.
 */
public class OP_Swap extends HighLevelOperation {

    /**
     * Version number for this class.
     */
    private static final long serialVersionUID = Const.VERSION_NUMBER;

    /**
     * Create a new Swap operation. Note that you must set var1, var2, and value.
     */
    public OP_Swap () {
        super(OperationType.swap, null, null);
    }

    /**
     * Set var1 for this Swap operation. The identifier of the variable should be
     * previously declared in the header.
     *
     * @param var1
     *            Variable 1 for this Swap operation.
     */
    public void setVar1 (Locator var1) {
        body.put(Key.var1, var1);
    }

    /**
     * Set var2 for this Swap operation. The identifier of the variable should be
     * previously declared in the header.
     *
     * @param var2
     *            Variable 2 for this Swap operation.
     */
    public void setVar2 (Locator var2) {
        body.put(Key.var2, var2);
    }

    /**
     * The values contained at var1 and var2 respectively, AFTER this Swap operation has
     * been executed.
     *
     * @param values
     *            The values in var1 and var2 after execution.
     */
    public void setValues (double[] values) {
        body.put(Key.value, values);
    }

    public Locator getVar1 () {
        return (Locator) body.get(Key.var1);
    }

    public Locator getVar2 () {
        return (Locator) body.get(Key.var2);
    }

    public double[] getValue () {
        return (double[]) body.get(Key.value);
    }

    @Override
    public String toString () {
        return "SWAP: " + getVar1().toString() + " <-> " + getVar2().toString();
    }

    /**
     * Attempt to create a Swap operation from 3 read/write operations.
     *
     * @param rwList
     *            The list of 3 read/write operations to test.
     * @return A new Swap operation if the given testSet is a valid decomposition of a
     *         Swap operation, null otherwise.
     */
    @Override
    public OP_Swap consolidate (List<OP_ReadWrite> rwList) {
        if (rwList.size() != OperationType.swap.numAtomicOperations) {
            throw new IllegalArgumentException("Swap operations are composed of 3 read/write operations.");
        }

        OP_ReadWrite rw0 = rwList.get(0);
        OP_ReadWrite rw1 = rwList.get(1);
        OP_ReadWrite rw2 = rwList.get(2);

        if (rw0.getSource() == null || rw0.getTarget() == null || rw1.getSource() == null || rw1.getTarget() == null
                || rw2.getSource() == null || rw2.getTarget() == null) {
            return null; // All sources/targets must be known.
        }
        Locator var1, tmp, var2;
        // Operation 1: Set var1 -> tmp
        var1 = rw0.getSource();
        tmp = rw0.getTarget();
        if (tmp.index != null) {
            return null; // tmp should not be another array.
        }
        // Operation 2: x -> var1?
        if (rw1.getTarget().equals(var1)) {
            var2 = rw1.getSource(); // Set x = var2
        } else {
            return null;
        }
        // Operation 3: tmp -> var2?
        if (!(rw2.getSource().equals(tmp) && rw2.getTarget().equals(var2))) {
            return null;
        }
        // Construct and return swap operation.
        OP_Swap op_swap = new OP_Swap();
        op_swap.setVar1(var1);
        op_swap.setVar2(var2);
        op_swap.setValues(new double[] { rw1.getValue() [0], rw0.getValue() [0] });
        op_swap.atomicOperations.addAll(rwList);
        // TODO: Source origin.
        return op_swap;
    }

    @Override
    public String toSimpleString () {
        return toString();
    }
}
