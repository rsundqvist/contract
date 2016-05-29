package contract.datastructure;

import java.util.Arrays;

/**
 * An element with an n-dimensional index.
 *
 * @author Richard Sundqvist
 *
 */
public class IndexedElement extends Element {

    int[] index;

    /**
     * Construct a new ArrayElement with the given value and index.
     *
     * @param value
     *            The value for this ArrayElement.
     * @param index
     *            The index for this ArrayElement.
     */
    public IndexedElement (double value, int[] index) {
        this.setValue(value);
        setIndex(index);
    }

    private final int primes[] = { 607, 613, 617, 619, 631, 641, 643, 647, 653, 659, 661, 673, 677, 683, 691, 701, 709,
            719, 727, 733 };

    @Override
    public int hashCode () {
        if (index == null) {
            return -1;
        }

        int indexHash = 0;
        for (int i = 0; i < index.length; i++) {
            indexHash = index [i] * primes [i];
        }
        return indexHash;
    }

    /**
     * Get the index of this ArrayElement.
     *
     * @return The index of this ArrayElement.
     */
    public int[] getIndex () {
        return index;
    }

    /**
     * Set the index of this ArrayElement.
     *
     * @param newIndex
     *            The new index of this ArrayElement.
     */
    public void setIndex (int[] newIndex) {
        index = newIndex;
    }

    /**
     * Returns true if value and index are equal, false otherwise.
     *
     * @param obj
     *            The object to compare this ArrayVariable to.
     */
    @Override
    public boolean equals (Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof IndexedElement == false) {
            return false;
        }
        IndexedElement rhs = (IndexedElement) obj;
        return getNumValue() == rhs.getNumValue() && Arrays.equals(index, rhs.index);
    }

    @Override
    public String toString () {
        return Arrays.toString(index) + " = " + getNumValue();
    }
}
