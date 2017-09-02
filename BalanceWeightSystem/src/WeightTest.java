import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Tests for Weight.
 *
 * @author Bennett Alex Myers
 * @version 11/18/2016
 */
public class WeightTest {

    /** Default weight value. */
    private static final int DEFAULT_WEIGHT_VALUE = 42;

    /** Default weight object (test fixture). */
    private Weight defaultWeight;

    /**
     * Initialize test fixtures.
     */
    @Before
    public void setUp() {
        this.defaultWeight = new Weight(DEFAULT_WEIGHT_VALUE);
    }

    /**
     * Verifies that constructor initializes the correct value.
     */
    @Test
    public void constructorTest() {
        assertEquals(DEFAULT_WEIGHT_VALUE, this.defaultWeight.getValue());
    }

    /**
     * Verifies exception for negative parameter passed to constructor.
     */
    @Test (expected = IllegalArgumentException.class)
    public void negativeConstructorException() {
        Weight w = new Weight(-1);
    }

    /**
     * Verifies exception for zero parameter passed to constructor.
     */
    @Test (expected = IllegalArgumentException.class)
    public void zeroConstructorException() {
        Weight w = new Weight(0);
    }

    /**
     * Test for value accessor.
     */
    @Test
    public void getValueTest() {
        assertEquals(DEFAULT_WEIGHT_VALUE, defaultWeight.getValue());
    }

    /**
     * Verifies correct behavior of equals predicate.
     */
    @Test
    public void equalsTest() {
        assertFalse(this.defaultWeight.equals(null));
        assertFalse(this.defaultWeight.equals(new Integer(DEFAULT_WEIGHT_VALUE)));
        assertTrue(this.defaultWeight.equals(this.defaultWeight));
        Weight w = new Weight(DEFAULT_WEIGHT_VALUE);
        assertTrue(this.defaultWeight.equals(w));
        assertTrue(w.equals(this.defaultWeight));
    }

    /**
     * Verifies that hashCode meets equals contract.
     */
    @Test
    public void hashCodeTest() {
        Weight w = new Weight(DEFAULT_WEIGHT_VALUE);
        assertTrue(this.defaultWeight.equals(w));
        assertEquals(this.defaultWeight.hashCode(), w.hashCode());
    }

    /**
     * Verifies correct behavior of compareTo.
     */
    @Test
    public void compareToTest() {
        Weight w = new Weight(DEFAULT_WEIGHT_VALUE);
        Weight lessThanW = new Weight(DEFAULT_WEIGHT_VALUE - 1);
        Weight greaterThanW = new Weight(DEFAULT_WEIGHT_VALUE + 1);
        assertTrue(w.equals(this.defaultWeight));
        assertEquals(w.compareTo(this.defaultWeight), 0);
        assertEquals(this.defaultWeight.compareTo(w), 0);
        assertTrue(w.compareTo(lessThanW) > 0);
        assertTrue(lessThanW.compareTo(w) < 0);
        assertTrue(w.compareTo(greaterThanW) < 0);
        assertTrue(greaterThanW.compareTo(w) > 0);
    }

    /**
     * Checks string rendering.
     */
    @Test
    public void toStringTest() {
        assertTrue(this.defaultWeight.toString().contains("42"));
    }
}

