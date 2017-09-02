import org.junit.Test;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertNotNull;

/**
 * Tests for HuffmanTree
 *
 * @author Bennett Alex Myers
 * @version 14 October 2016
 */
public class HuffmanTreeTest {

    // Leaf node data for Huffman coding of "roadrunner"
    /** 'r' leaf node. */
    private static final HuffmanTreeNodeValues R =
            new HuffmanTreeNodeValues('r', 0.3, new StringOfBits("11"));
    /** 'o' leaf node. */
    private static final HuffmanTreeNodeValues O =
            new HuffmanTreeNodeValues('o', 0.1, new StringOfBits("100"));
    /** 'a' leaf node. */
    private static final HuffmanTreeNodeValues A =
            new HuffmanTreeNodeValues('a', 0.1, new StringOfBits("001"));
    /** 'd' leaf node. */
    private static final HuffmanTreeNodeValues D =
            new HuffmanTreeNodeValues('d', 0.1, new StringOfBits("1011"));
    /** 'u' leaf node. */
    private static final HuffmanTreeNodeValues U =
            new HuffmanTreeNodeValues('u', 0.1, new StringOfBits("1010"));
    /** 'n' leaf node. */
    private static final HuffmanTreeNodeValues N =
            new HuffmanTreeNodeValues('n', 0.2, new StringOfBits("01"));
    /** 'e' leaf node. */
    private static final HuffmanTreeNodeValues E =
            new HuffmanTreeNodeValues('e', 0.1, new StringOfBits("000"));

    /**
     * Generates a standard tree for testing.
     */
    private HuffmanTree generateStandardTestTree() {
        return new HuffmanTree(
                1.0,
                new HuffmanTree(
                        0.4,
                        new HuffmanTree(
                                0.2,
                                new HuffmanTree(E),
                                new HuffmanTree(A)),
                        new HuffmanTree(N)),
                new HuffmanTree(
                        0.6,
                        new HuffmanTree(
                                0.3,
                                new HuffmanTree(O),
                                new HuffmanTree(
                                        0.2,
                                        new HuffmanTree(U),
                                        new HuffmanTree(D))),
                        new HuffmanTree(R)));
    }

    /** New symbol value for testing. */
    private static final Character NEW_SYMBOL = 'A';
    /** New frequency value for testing. */
    private static final Double NEW_FREQ = 0.5;
    /** New code value for testing. */
    private static final StringOfBits NEW_CODE = new StringOfBits("10");
    /** New HuffmanTreeNodeValues for testing. */
    private static final HuffmanTreeNodeValues NEW_NODE_VALUES =
            new HuffmanTreeNodeValues(NEW_SYMBOL, NEW_FREQ, NEW_CODE);
    /** Default left child tree for testing. */
    private static final HuffmanTree LEFT_CHILD =
            new HuffmanTree('L', 0.2, new StringOfBits("100"));
    /** Default right child tree for testing. */
    private static final HuffmanTree RIGHT_CHILD =
            new HuffmanTree('R', 0.3, new StringOfBits("101"));

    /** Generate standard leaf node for testing. */
    private HuffmanTree generateLeaf() {
        return new HuffmanTree(NEW_NODE_VALUES);
    }

    /** Generate standard internal node for testing. */
    private HuffmanTree generateInternalNode() {
        return new HuffmanTree(NEW_FREQ, LEFT_CHILD, RIGHT_CHILD);
    }

    /**
     * Leaf node tests for HuffmanTreeNodeValues constructor.
     */
    @Test
    public void leafNodeTest() {
        HuffmanTree ht = new HuffmanTree(NEW_NODE_VALUES);
        assertTrue(ht.compareTo(new HuffmanTree(R)) > 0);
        assertNotNull(ht.getCode());
        assertEquals(ht.getCode(), NEW_CODE);
        assertNotNull(ht.getFrequency());
        assertEquals(ht.getFrequency(), NEW_FREQ);
        assertNotNull(ht.getSymbol());
        assertEquals(ht.getSymbol(), NEW_SYMBOL);
        assertNull(ht.getLeftChild());
        assertNull(ht.getRightChild());
    }

    /**
     * Leaf node tests for parameterized constructor.
     */
    @Test
    public void leafNodeWithParamTest() {
        HuffmanTree ht = new HuffmanTree(NEW_SYMBOL, NEW_FREQ, NEW_CODE);
        assertTrue(ht.compareTo(new HuffmanTree(R)) > 0);
        assertNotNull(ht.getCode());
        assertEquals(ht.getCode(), NEW_CODE);
        assertNotNull(ht.getFrequency());
        assertEquals(ht.getFrequency(), NEW_FREQ);
        assertNotNull(ht.getSymbol());
        assertEquals(ht.getSymbol(), NEW_SYMBOL);
        assertNull(ht.getLeftChild());
        assertNull(ht.getRightChild());
    }

    /**
     * Internal node tests for frequency constructor.
     */
    @Test
    public void internalNodeWithFrequencyTest() {
        HuffmanTree ht = new HuffmanTree(NEW_FREQ, LEFT_CHILD, RIGHT_CHILD);
        assertTrue(ht.compareTo(new HuffmanTree(R)) > 0);
        assertNull(ht.getCode());
        assertNotNull(ht.getFrequency());
        assertEquals(ht.getFrequency(), NEW_FREQ);
        assertNull(ht.getSymbol());
        assertNotNull(ht.getLeftChild());
        assertEquals(ht.getLeftChild(), LEFT_CHILD);
        assertNotNull(ht.getRightChild());
        assertEquals(ht.getRightChild(), RIGHT_CHILD);
    }

    /**
     * Internal node tests for HuffmanTreeNodeValues constructor.
     */
    @Test
    public void internalNodeWithDataTest() {
        HuffmanTree ht = new HuffmanTree(NEW_NODE_VALUES,
                LEFT_CHILD, RIGHT_CHILD);
        assertTrue(ht.compareTo(new HuffmanTree(R)) > 0);
        assertNotNull(ht.getCode());
        assertEquals(ht.getCode(), NEW_CODE);
        assertNotNull(ht.getFrequency());
        assertEquals(ht.getFrequency(), NEW_FREQ);
        assertNotNull(ht.getSymbol());
        assertEquals(ht.getSymbol(), NEW_SYMBOL);
        assertNotNull(ht.getLeftChild());
        assertEquals(ht.getLeftChild(), LEFT_CHILD);
        assertNotNull(ht.getRightChild());
        assertEquals(ht.getRightChild(), RIGHT_CHILD);
    }

    /**
     * Internal node tests for fully parameterized constructor.
     */
    @Test
    public void internalNodeTest() {
        HuffmanTree ht = new HuffmanTree(NEW_SYMBOL,
                NEW_FREQ, NEW_CODE, LEFT_CHILD, RIGHT_CHILD);
        assertTrue(ht.compareTo(new HuffmanTree(R)) > 0);
        assertNotNull(ht.getCode());
        assertEquals(ht.getCode(), NEW_CODE);
        assertNotNull(ht.getFrequency());
        assertEquals(ht.getFrequency(), NEW_FREQ);
        assertNotNull(ht.getSymbol());
        assertEquals(ht.getSymbol(), NEW_SYMBOL);
        assertNotNull(ht.getLeftChild());
        assertEquals(ht.getLeftChild(), LEFT_CHILD);
        assertNotNull(ht.getRightChild());
        assertEquals(ht.getRightChild(), RIGHT_CHILD);
    }

    /**
     * Tests for symbol accessor.
     */
    @Test
    public void getSymbolTest() {
        HuffmanTree ht = generateLeaf();
        assertNotNull(ht.getSymbol());
        assertEquals(ht.getSymbol(), NEW_SYMBOL);
        ht = generateInternalNode();
        assertNull(ht.getSymbol());
    }

    /**
     * Tests for frequency accessor.
     */
    @Test
    public void getFrequencyTest() {
        HuffmanTree ht = generateLeaf();
        assertNotNull(ht.getFrequency());
        assertEquals(ht.getFrequency(), NEW_FREQ);
        ht = generateInternalNode();
        assertNotNull(ht.getFrequency());
        assertEquals(ht.getFrequency(), NEW_FREQ);
    }

    /**
     * Tests for code accessor.
     */
    @Test
    public void getCodeTest() {
        HuffmanTree ht = generateLeaf();
        assertNotNull(ht.getCode());
        assertEquals(ht.getCode(), NEW_CODE);
        ht = generateInternalNode();
        assertNull(ht.getCode());
    }

    /**
     * Tests for symbol mutator.
     */
    @Test
    public void setSymbolTest() {
        HuffmanTree ht = generateLeaf();
        ht.setSymbol(null);
        assertNull(ht.getSymbol());
        ht.setSymbol(NEW_SYMBOL);
        assertEquals(ht.getSymbol(), NEW_SYMBOL);
    }

    /**
     * Tests for frequency mutator.
     */
    @Test
    public void setFrequencyTest() {
        HuffmanTree ht = generateLeaf();
        ht.setFrequency(null);
        assertNull(ht.getFrequency());
        ht.setFrequency(NEW_FREQ);
        assertEquals(ht.getFrequency(), NEW_FREQ);
    }

    /**
     * Tests for code mutator.
     */
    @Test
    public void setCodeTest() {
        HuffmanTree ht = generateLeaf();
        ht.setCode(null);
        assertNull(ht.getCode());
        ht.setCode(NEW_CODE);
        assertEquals(ht.getCode(), NEW_CODE);
    }

    /**
     * Verifies equality of leaf nodes.
     */
    @Test
    public void equalsLeafTest() {
        HuffmanTree ht1 = generateLeaf();
        assertTrue(ht1.equals(ht1));
        assertFalse(ht1.equals(null));
        HuffmanTree ht2 = generateLeaf();
        assertTrue(ht1.equals(ht2));
        assertTrue(ht2.equals(ht1));
        HuffmanTree ht3 = generateStandardTestTree();
        assertFalse(ht1.equals(ht3));
        assertFalse(ht3.equals(ht1));
    }

    /**
     * Verifies equality of internal nodes.
     */
    @Test
    public void equalsInternalNodeTest() {
        HuffmanTree ht1 = generateInternalNode();
        assertTrue(ht1.equals(ht1));
        assertFalse(ht1.equals(null));
        HuffmanTree ht2 = generateInternalNode();
        assertTrue(ht1.equals(ht2));
        assertTrue(ht2.equals(ht1));
        HuffmanTree ht3 = generateStandardTestTree();
        assertFalse(ht1.equals(ht3));
        assertFalse(ht3.equals(ht1));
    }

    /**
     * Verifies equality of arbitrary trees.
     */
    @Test
    public void equalsTest() {
        HuffmanTree ht1 = generateStandardTestTree();
        assertTrue(ht1.equals(ht1));
        assertFalse(ht1.equals(null));
        HuffmanTree ht2 = generateStandardTestTree();
        assertTrue(ht1.equals(ht2));
        assertTrue(ht2.equals(ht1));
    }

    /**
     * Verifies hashCode meets equals contract.
     */
    @Test
    public void hashCodeTest() {
        HuffmanTree leaf1 = generateLeaf();
        HuffmanTree leaf2 = generateLeaf();
        assertTrue(leaf1.equals(leaf2));
        assertEquals(leaf1.hashCode(), leaf2.hashCode());
        HuffmanTree fork1 = generateInternalNode();
        HuffmanTree fork2 = generateInternalNode();
        assertTrue(fork1.equals(fork2));
        assertEquals(fork1.hashCode(), fork2.hashCode());
        HuffmanTree ht1 = generateStandardTestTree();
        HuffmanTree ht2 = generateStandardTestTree();
        assertTrue(ht1.equals(ht2));
        assertEquals(ht1.hashCode(), ht2.hashCode());
    }

    /**
     * Verifies result of compareTo.
     */
    @Test
    public void compareToTest() {
        HuffmanTree leaf1 = generateLeaf();
        HuffmanTree leaf2 = generateLeaf();
        assertTrue(leaf1.equals(leaf2));
        assertEquals(leaf1.compareTo(leaf2), 0);
        assertTrue(RIGHT_CHILD.compareTo(leaf1) < 0);
        assertTrue(leaf1.compareTo(RIGHT_CHILD) > 0);
        HuffmanTree fork1 = generateInternalNode();
        HuffmanTree fork2 = generateInternalNode();
        assertTrue(fork1.equals(fork2));
        assertEquals(fork1.compareTo(fork2), 0);
        assertTrue(RIGHT_CHILD.compareTo(fork1) < 0);
        assertTrue(fork1.compareTo(RIGHT_CHILD) > 0);
        HuffmanTree ht1 = generateStandardTestTree();
        HuffmanTree ht2 = generateStandardTestTree();
        assertTrue(ht1.equals(ht2));
        assertEquals(ht1.compareTo(ht2), 0);
        assertTrue(RIGHT_CHILD.compareTo(ht1) < 0);
        assertTrue(ht1.compareTo(RIGHT_CHILD) > 0);
    }
}

