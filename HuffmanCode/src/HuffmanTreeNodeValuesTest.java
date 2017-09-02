import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * Tests for HuffmanTreeNodeValues.
 *
 * @author Bennett Alex Myers
 * @version 12 October 2016
 */
public class HuffmanTreeNodeValuesTest {

    /**
     * Symbol stored by default tree node.
     */
    private static final Character DEFAULT_SYMBOL = new Character('a');

    /**
     * Frequency stored by default tree node.
     */
    private static final Double DEFAULT_FREQUENCY = new Double(0.25);

    /**
     * Code stored by default tree node.
     */
    private static final StringOfBits DEFAULT_CODE = new StringOfBits("10");

    /**
     * Default tree node used for tests.
     */
    private static final HuffmanTreeNodeValues DEFAULT_NODE_VALUES =
            new HuffmanTreeNodeValues(DEFAULT_SYMBOL,
                                      DEFAULT_FREQUENCY,
                                      DEFAULT_CODE);

    /**
     * Verifies that empty constructor initializes values to null.
     */
    @Test
    public void emptyConstructorTest() {
        HuffmanTreeNodeValues nodeValues = new HuffmanTreeNodeValues();
        assertNull(nodeValues.getCode());
        assertNull(nodeValues.getFrequency());
        assertNull(nodeValues.getSymbol());
    }

    /**
     * Verifies that parameterized constructor initializes correct values.
     */
    @Test
    public void constructorTest() {
        HuffmanTreeNodeValues nodeValues =
                new HuffmanTreeNodeValues(DEFAULT_SYMBOL,
                DEFAULT_FREQUENCY,
                DEFAULT_CODE);
        assertEquals(nodeValues.getCode(), DEFAULT_CODE);
        assertEquals(nodeValues.getSymbol(), DEFAULT_SYMBOL);
        assertEquals(nodeValues.getFrequency(), DEFAULT_FREQUENCY);
    }

    /**
     * Test for symbol accessor.
     */
    @Test
    public void getSymbolTest() {
        assertEquals(DEFAULT_NODE_VALUES.getSymbol(), DEFAULT_SYMBOL);
    }

    /**
     * Test for frequency accessor.
     */
    @Test
    public void getFrequencyTest() {
        assertEquals(DEFAULT_NODE_VALUES.getFrequency(), DEFAULT_FREQUENCY);
    }

    /**
     * Test for code accessor.
     */
    @Test
    public void getCodeTest() {
        assertEquals(DEFAULT_NODE_VALUES.getCode(), DEFAULT_CODE);
    }

    /**
     * Test for symbol mutator.
     */
    @Test
    public void setSymbolTest() {
        Character otherValue = 'b';
        HuffmanTreeNodeValues nodeValues =
                new HuffmanTreeNodeValues(otherValue,
                        DEFAULT_FREQUENCY,
                        DEFAULT_CODE);
        nodeValues.setSymbol(DEFAULT_SYMBOL);
        assertEquals(nodeValues.getSymbol(), DEFAULT_SYMBOL);
        assertFalse(nodeValues.getSymbol().equals(otherValue));
    }

    /**
     * Test for frequency mutator.
     */
    @Test
    public void setFrequencyTest() {
        Double otherValue = 1.0;
        HuffmanTreeNodeValues nodeValues =
                new HuffmanTreeNodeValues(DEFAULT_SYMBOL,
                        otherValue,
                        DEFAULT_CODE);
        nodeValues.setFrequency(DEFAULT_FREQUENCY);
        assertEquals(nodeValues.getFrequency(), DEFAULT_FREQUENCY);
        assertFalse(nodeValues.getFrequency().equals(otherValue));
    }

    /**
     * Test for code mutator.
     */
    @Test
    public void setCodeTest() {
        StringOfBits otherValue = new StringOfBits("1010");
        HuffmanTreeNodeValues nodeValues =
                new HuffmanTreeNodeValues(DEFAULT_SYMBOL,
                        DEFAULT_FREQUENCY,
                        otherValue);
        nodeValues.setCode(DEFAULT_CODE);
        assertEquals(nodeValues.getCode(), DEFAULT_CODE);
        assertFalse(nodeValues.getCode().equals(otherValue));
    }

    /**
     * Verifies toString returns correct string representation.
     */
    @Test
    public void toStringTest() {
        assertEquals(DEFAULT_NODE_VALUES.toString(), "(a, 0.25, 10)");
    }
}

