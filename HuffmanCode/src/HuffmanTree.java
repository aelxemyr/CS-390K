/**
 * A HuffmanTree is a specialized BinaryTree used for developing and storing a
 * Huffman Code. Note that there is no empty tree constructor.
 *
 * @author Bennett Alex Myers
 * @version 15 October 2016
 */
public class HuffmanTree extends BinaryTree<HuffmanTreeNodeValues>
        implements Comparable<HuffmanTree> {

    /**
     * Maximum difference to accept to double values as equal.
     */
    public static final double EPSILON = 0.00001;

    /**
     * Serialization version indicator used to determine
     * if a file is compatible with this class.
     */
    private static final long serialVersionUID = 2016090422L;

    /**
     * Constructor for leaf node.
     * @param data an object containing the symbol, frequency, and code for
     *             this node
     */
    public HuffmanTree(final HuffmanTreeNodeValues data) {
        this(data, null, null);
    }

    /**
     * Constructor for internal node.
     * @param data an object containing the symbol, code, and frequency for
     *             this node
     * @param leftChild the left child for this node
     * @param rightChild the right child for this node
     */
    public HuffmanTree(final HuffmanTreeNodeValues data,
                       final HuffmanTree leftChild,
                       final HuffmanTree rightChild) {
        super(data, leftChild, rightChild);
    }

    /**
     * Parameterized constructor for leaf node.
     * @param symbol the symbol stored in this node
     * @param frequency the frequency stored in this node
     * @param code the code stored in this node
     */
    public HuffmanTree(final Character symbol,
                       final Double frequency,
                       final StringOfBits code) {
        this(symbol, frequency, code, null, null);
    }

    /**
     * Fully parameterized constructor.
     * @param symbol the symbol stored in this node
     * @param frequency the frequency stored in this node
     * @param code the node stored in this node
     * @param leftChild the left child for this node
     * @param rightChild the right child for this node
     */
    public HuffmanTree(final Character symbol,
                       final Double frequency,
                       final StringOfBits code,
                       final HuffmanTree leftChild,
                       final HuffmanTree rightChild) {
        super(new HuffmanTreeNodeValues(symbol, frequency, code),
                leftChild,
                rightChild);
    }

    /**
     * Frequency and children constructor; useful for internal nodes. Sets
     * symbol and code to null.
     * @param frequency the frequency stored in this node
     * @param leftChild the left child of this node
     * @param rightChild the right child of this node
     */
    public HuffmanTree(final Double frequency,
                       final HuffmanTree leftChild,
                       final HuffmanTree rightChild) {
        this(null, frequency, null, leftChild, rightChild);
    }

    /**
     * Returns the left child of this tree.
     * @return the left child; null if no such child
     */
    @Override
    public HuffmanTree getLeftChild() {
        return (HuffmanTree) super.getLeftChild();
    }

    /**
     * Returns the right child of this tree.
     * @return the right child; null if no such child
     */
    @Override
    public HuffmanTree getRightChild() {
        return (HuffmanTree) super.getRightChild();
    }

    /**
     * Retrieve the symbol stored in this root.
     * @return the symbol stored in this root
     */
    public Character getSymbol() {
        return getValue().getSymbol();
    }

    /**
     * Retrieve the code stored in this root.
     * @return the code stored in this root
     */
    public StringOfBits getCode() {
        return getValue().getCode();
    }

    /**
     * Retrieve the frequency stored in this root.
     * @return the frequency stored in this root
     */
    public Double getFrequency() {
        return getValue().getFrequency();
    }

    /**
     * Store the given parameter as the symbol of this tree root.
     * @param symbol the new symbol for this root
     */
    public void setSymbol(final Character symbol) {
        getValue().setSymbol(symbol);
    }

    /**
     * Store the given parameter as the code of this tree root.
     * @param code the new code for this root
     */
    public void setCode(final StringOfBits code) {
        getValue().setCode(code);
    }

    /**
     * Store the given parameter as the frequency of this tree root.
     * @param frequency the new frequency for this root
     */
    public void setFrequency(final Double frequency) {
        getValue().setFrequency(frequency);
    }

    /**
     * Equals predicate considers the symbol and frequency only.
     * @param o the object to check for equality
     * @return true if both the symbol and the frequency agree; false otherwise
     * @see #hashCode()
     */
    @Override
    public boolean equals(final Object o) {
        if ((o == null) || (o.getClass() != this.getClass())) {
            return false;
        }
        HuffmanTree ht = (HuffmanTree) o;
        return compareTrees(this, ht);
    }

    /** Constants used in computation of hash code. */
    private static final int HASH_CONST = 7;

    /**
     * Returns a hash code value for the object. Supported for the benefit of
     * hashtables.
     * @return a hash code value for this object.
     * @see #equals(Object o)
     */
    @Override
    public int hashCode() {
        int mycode = 1;
        if (getSymbol() == null) {
            mycode += HASH_CONST;
        } else {
            mycode += getSymbol().hashCode();
        }
        return mycode + getFrequency().hashCode();
    }

    /**
     * <p>Compares this HuffmanTree with the parameter for order. Returns a
     * negative integer, zero, or a positive integer as this object is less
     * than, equal to, or greater than the specified object. Comparison
     * considers frequency only; <code>null</code> is considered to be lower
     * than any other frequency value.</p>
     *
     * <p>Ensures that <code>sgn(x.compareTo(y) == -sgn(y.compareTo(x))</code>
     * for all <code>x</code> and <code>y</code>. (This implies that
     * <code>x.compareTo(y)</code> must throw an exception if and only if
     * <code>y.compareTo(x)</code> throws an exception.)</p>
     *
     * <p>The relation in transitive:
     * <code>(x.compareTo(y)>0 && y.compareTo(z)>0)</code> implies
     * <code>x.compareTo(z)>0</code>.</p>
     *
     * <p>Ensures that <code>x.compareTo(y)==0</code> implies that
     * <code>sgn(x.compareTo(z)) == sgn(y.compareTo(z))</code>, for all
     * <code>z</code>.</p>
     *
     * <p>The natural ordering is consistent with
     * {@link #equals equals}, that is,
     * <code>(x.compareTo(y)==0) == (x.equals(y))</code>.</p>
     *
     * <p>In the foregoing description, the notation
     * <code>sgn</code>(<i>expression</i>) designated the mathematical
     * <i>signum</i> function, which is defined to return
     * one of <code>1</code>, <code>0</code>, or <code>-1</code> according to
     * whether the value of expression is negative, zero, or positive.
     * @param ht the object to be compared
     * @return a negative integer, zero, or a positive integer as this object
     * is less than, equal to, or greater than the specified object.</p>
     */
    public int compareTo(final HuffmanTree ht) {
        if (Math.abs(this.getFrequency() - (ht.getFrequency())) < EPSILON) {
            return 0;
        }
        if (this.getFrequency() > ht.getFrequency()) {
            return 1;
        } else {
            return -1;
        }
    }

    /**
     * Utility method that compares symbol and frequency stored in two roots.
     * @param ht1 first root for comparison
     * @param ht2 second root for comparison
     * @return true if symbol and frequency agree.
     */
    private static boolean compareTrees(final HuffmanTree ht1,
                                        final HuffmanTree ht2) {
        if (ht1.getSymbol() == null && ht2.getSymbol() == null) {
            return ht1.getFrequency().equals(ht2.getFrequency());
        }
        if (ht1.getSymbol() != null && ht2.getSymbol() != null) {
            return (ht1.getSymbol().equals(ht2.getSymbol()))
                    && (ht1.getFrequency().equals(ht2.getFrequency()));
        }
        return false;
    }
}
