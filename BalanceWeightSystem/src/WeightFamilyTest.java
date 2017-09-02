import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import java.util.TreeSet;
import java.util.Collections;
import java.util.Collection;
import java.util.Arrays;
import java.util.Iterator;

/**
 * Tests for WeightFamily.
 *
 * @author Bennett Alex Myers
 * @version 11/18/2016
 */
public class WeightFamilyTest {

    /** The unit weight. */
    private static final Weight UNIT_WEIGHT = new Weight(1);

    /** The singleton set containing the unit weight. */
    private static final TreeSet<Weight> SINGLETON =
            new TreeSet<>(Collections.singleton(UNIT_WEIGHT));

    /** Default set of weights for tests. */
    private static final TreeSet<Weight> DEFAULT_WEIGHTS;

    /** Initialization for DEFAULT_WEIGHTS. */
    static {
        DEFAULT_WEIGHTS = new TreeSet<>();
        DEFAULT_WEIGHTS.add(UNIT_WEIGHT);
        DEFAULT_WEIGHTS.add(new Weight(2));
        DEFAULT_WEIGHTS.add(new Weight(5));
    }

    /** Singleton weight family. */
    private WeightFamily singleton;

    /** Default weight family. */
    private WeightFamily defaultFamily;

    /**
     * Initialize test fixtures.
     */
    @Before
    public void setUp() {
        this.singleton = new WeightFamily(SINGLETON);
        this.defaultFamily = new WeightFamily(DEFAULT_WEIGHTS);
    }

    /**
     * Verifies that empty constructor creates singleton family containing
     * the unit weight.
     */
    @Test
    public void emptyConstructorTest() {
        assertNotNull(this.singleton);
        assertEquals(this.singleton.size(), 1);
        assertEquals(SINGLETON, new TreeSet<>(this.singleton.getWeights()));
    }

    /**
     * Verifies that parameterized constructor initializes correct values.
     */
    @Test
    public void constructorTest() {
        assertNotNull(this.defaultFamily);
        assertEquals(this.defaultFamily.size(), 3);
        assertEquals(DEFAULT_WEIGHTS,
                new TreeSet<>(this.defaultFamily.getWeights()));
    }

    /**
     * Verifies that constructor ignores null weights.
     */
    @Test
    public void constructorIgnoresNullWeightsTest() {
        Collection<Weight> l = Arrays.asList(UNIT_WEIGHT, null);
        WeightFamily wf = new WeightFamily(l);
        assertEquals(wf.size(), 1);
    }

    /**
     * Verifies that constructor adds unit weight.
     */
    @Test
    public void constructorAddsUnitWeightTest() {
        Collection<Weight> mt = Collections.emptySet();
        WeightFamily wf1 = new WeightFamily(mt);
        assertEquals(wf1.size(), 1);
        assertTrue(wf1.getWeights().contains(UNIT_WEIGHT));
        Collection<Weight> l = Arrays.asList(new Weight(2), new Weight(5));
        WeightFamily wf2 = new WeightFamily(l);
        assertEquals(wf2.size(), 3);
        assertTrue(wf2.getWeights().contains(UNIT_WEIGHT));
    }

    /**
     * Verifies exception for null passed to constructor.
     */
    @Test (expected = IllegalArgumentException.class)
    public void nullConstructorException() {
        Collection<Weight> nc = null;
        WeightFamily wf = new WeightFamily(nc);
    }

    /**
     * Checks that the collection returned by getWeights is correct.
     */
    @Test
    public void getWeightsTest() {
        TreeSet<Weight> singletonWeights =
                new TreeSet<>(this.singleton.getWeights());
        assertEquals(SINGLETON, singletonWeights);

        TreeSet<Weight> defaultWeights =
                new TreeSet<>(this.defaultFamily.getWeights());
        assertEquals(DEFAULT_WEIGHTS, defaultWeights);
    }

    /**
     * Checks if iterator returns the correct elements in the correct order.
     */
    @Test
    public void iteratorTest() {
        Iterator<Weight> itr = this.singleton.iterator();
        Weight current;
        assertNotNull(itr);
        assertTrue(itr.hasNext());
        current = itr.next();
        assertEquals(UNIT_WEIGHT, current);
        assertFalse(itr.hasNext());

        itr = this.defaultFamily.iterator();
        TreeSet<Weight> weights = new TreeSet<>(this.defaultFamily.getWeights());
        assertNotNull(itr);
        for (int i = 0; i < this.defaultFamily.size(); i++) {
            assertTrue(itr.hasNext());
            current = itr.next();
            assertEquals(weights.pollFirst(), current);
        }
        assertFalse(itr.hasNext());
    }

    /**
     * Checks string rendering.
     */
    @Test
    public void toStringTest() {
        assertEquals("[" + UNIT_WEIGHT + "]",
                this.singleton.toString());
        String defaultFamilyString = "[" + UNIT_WEIGHT
                + ", " + new Weight(2)
                + ", " + new Weight(5) + "]";
        assertEquals(defaultFamilyString, this.defaultFamily.toString());
    }
}

