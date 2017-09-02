import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Tests for StringOfBits.
 *
 * @author Bennett Alex Myers
 * @version 13 October 2016
 */
public class StringOfBitsTest {

    /**
     * Empty bit string value.
     */
    private static final StringOfBits EMPTY = new StringOfBits();

    /**
     * Length of empty bit string.
     */
    private static final int EMPTY_LENGTH = 0;

    /**
     * Default bit string value as String.
     */
    private static final String DEFAULT_STRING = "1010";

    /**
     * Length of default bit string.
     */
    private static final int DEFAULT_LENGTH = 4;

    /**
     * Default bit string value for tests.
     */
    private static final StringOfBits DEFAULT =
            new StringOfBits(DEFAULT_STRING);

    /**
     * Test fixture corresponding to empty bit string.
     */
    private StringOfBits mt;

    /**
     * Test fixture corresponding to default bit string.
     */
    private StringOfBits sb;

    /**
     * Initialize test fixtures.
     */
    @Before
    public void setUp() {
        this.mt = new StringOfBits();
        this.sb = new StringOfBits(DEFAULT);
    }

    /**
     * Verifies empty constructor creates empty bit string.
     */
    @Test
    public void emptyConstructorTest() {
        StringOfBits bits = new StringOfBits();
        assertEquals(bits.length(), EMPTY_LENGTH);
        assertNotNull(bits);
    }

    /**
     * Verifies copy constructor clones object.
     */
    @Test
    public void copyConstructorTest() {
        StringOfBits bits = new StringOfBits(EMPTY);
        assertEquals(bits.length(), EMPTY_LENGTH);
        assertNotNull(bits);
        bits = new StringOfBits(DEFAULT);
        assertEquals(bits.length(), DEFAULT_LENGTH);
        assertNotNull(bits);
    }

    /**
     * Verifies parameterized constructor creates appropriate object.
     */
    @Test
    public void constructorTest() {
        StringOfBits bits = new StringOfBits("");
        assertEquals(bits.length(), EMPTY_LENGTH);
        assertNotNull(bits);
        bits = new StringOfBits(DEFAULT_STRING);
        assertEquals(bits.booleanAt(0), true);
        assertEquals(bits.charAt(0), '1');
        assertEquals(bits.intAt(0), 1);
        assertEquals(bits.length(), DEFAULT_LENGTH);
        assertNotNull(bits);
    }

    /**
     * Tests for appending the boolean zero.
     */
    @Test
    public void appendBooleanZeroTest() {
        mt.append(false);
        assertNotNull(mt);
        assertEquals(mt.length(), 1);
        assertEquals(mt.charAt(0), '0');
        assertEquals(mt.intAt(0), 0);
        assertEquals(mt.booleanAt(0), false);

        sb.append(false);
        assertNotNull(sb);
        assertEquals(sb.length(), 5);
        assertEquals(sb.charAt(4), '0');
        assertEquals(sb.intAt(4), 0);
        assertEquals(sb.booleanAt(4), false);
    }

    /**
     * Tests for appending the boolean one.
     */
    @Test
    public void appendBooleanOneTest() {
        mt.append(true);
        assertNotNull(mt);
        assertEquals(mt.length(), 1);
        assertEquals(mt.charAt(0), '1');
        assertEquals(mt.intAt(0), 1);
        assertEquals(mt.booleanAt(0), true);

        sb.append(true);
        assertNotNull(sb);
        assertEquals(sb.length(), 5);
        assertEquals(sb.charAt(4), '1');
        assertEquals(sb.intAt(4), 1);
        assertEquals(sb.booleanAt(4), true);
    }

    /**
     * Tests for appending the char zero.
     */
    @Test
    public void appendCharZeroTest() {
        mt.append('0');
        assertNotNull(mt);
        assertEquals(mt.length(), 1);
        assertEquals(mt.charAt(0), '0');
        assertEquals(mt.intAt(0), 0);
        assertEquals(mt.booleanAt(0), false);

        sb.append('0');
        assertNotNull(sb);
        assertEquals(sb.length(), 5);
        assertEquals(sb.charAt(4), '0');
        assertEquals(sb.intAt(4), 0);
        assertEquals(sb.booleanAt(4), false);
    }

    /**
     * Tests for appending the char one.
     */
    @Test
    public void appendCharOneTest() {
        mt.append('1');
        assertNotNull(mt);
        assertEquals(mt.length(), 1);
        assertEquals(mt.charAt(0), '1');
        assertEquals(mt.intAt(0), 1);
        assertEquals(mt.booleanAt(0), true);

        sb.append('1');
        assertNotNull(sb);
        assertEquals(sb.length(), 5);
        assertEquals(sb.charAt(4), '1');
        assertEquals(sb.intAt(4), 1);
        assertEquals(sb.booleanAt(4), true);
    }

    /**
     * Tests for appending the string zero.
     */
    @Test
    public void appendStringZeroTest() {
        mt.append("0");
        assertNotNull(mt);
        assertEquals(mt.length(), 1);
        assertEquals(mt.charAt(0), '0');
        assertEquals(mt.intAt(0), 0);
        assertEquals(mt.booleanAt(0), false);

        sb.append("0");
        assertNotNull(sb);
        assertEquals(sb.length(), 5);
        assertEquals(sb.charAt(4), '0');
        assertEquals(sb.intAt(4), 0);
        assertEquals(sb.booleanAt(4), false);
    }

    /**
     * Tests for appending the string one.
     */
    @Test
    public void appendStringOneTest() {
        mt.append("1");
        assertNotNull(mt);
        assertEquals(mt.length(), 1);
        assertEquals(mt.charAt(0), '1');
        assertEquals(mt.intAt(0), 1);
        assertEquals(mt.booleanAt(0), true);

        sb.append("1");
        assertNotNull(sb);
        assertEquals(sb.length(), 5);
        assertEquals(sb.charAt(4), '1');
        assertEquals(sb.intAt(4), 1);
        assertEquals(sb.booleanAt(4), true);
    }

    /**
     * Tests for appending the int zero.
     */
    @Test
    public void appendIntZeroTest() {
        mt.append(0);
        assertNotNull(mt);
        assertEquals(mt.length(), 1);
        assertEquals(mt.charAt(0), '0');
        assertEquals(mt.intAt(0), 0);
        assertEquals(mt.booleanAt(0), false);

        sb.append(0);
        assertNotNull(sb);
        assertEquals(sb.length(), 5);
        assertEquals(sb.charAt(4), '0');
        assertEquals(sb.intAt(4), 0);
        assertEquals(sb.booleanAt(4), false);
    }

    /**
     * Tests for appending the int one.
     */
    @Test
    public void appendIntOneTest() {
        mt.append(1);
        assertNotNull(mt);
        assertEquals(mt.length(), 1);
        assertEquals(mt.charAt(0), '1');
        assertEquals(mt.intAt(0), 1);
        assertEquals(mt.booleanAt(0), true);

        sb.append(1);
        assertNotNull(sb);
        assertEquals(sb.length(), 5);
        assertEquals(sb.charAt(4), '1');
        assertEquals(sb.intAt(4), 1);
        assertEquals(sb.booleanAt(4), true);
    }

    /**
     * Tests for appending the bit string zero.
     */
    @Test
    public void appendBitStringZeroTest() {
        mt.append(new StringOfBits("0"));
        assertNotNull(mt);
        assertEquals(mt.length(), 1);
        assertEquals(mt.charAt(0), '0');
        assertEquals(mt.intAt(0), 0);
        assertEquals(mt.booleanAt(0), false);

        sb.append(new StringOfBits("0"));
        assertNotNull(sb);
        assertEquals(sb.length(), 5);
        assertEquals(sb.charAt(4), '0');
        assertEquals(sb.intAt(4), 0);
        assertEquals(sb.booleanAt(4), false);
    }

    /**
     * Tests for appending the bit string one.
     */
    @Test
    public void appendBitStringOneTest() {
        mt.append(new StringOfBits("1"));
        assertNotNull(mt);
        assertEquals(mt.length(), 1);
        assertEquals(mt.charAt(0), '1');
        assertEquals(mt.intAt(0), 1);
        assertEquals(mt.booleanAt(0), true);

        sb.append(new StringOfBits("1"));
        assertNotNull(sb);
        assertEquals(sb.length(), 5);
        assertEquals(sb.charAt(4), '1');
        assertEquals(sb.intAt(4), 1);
        assertEquals(sb.booleanAt(4), true);
    }

    /**
     * Verifies exception for index greater than length() - 1.
     */
    @Test (expected = IndexOutOfBoundsException.class)
    public void charAtIndexException() {
        DEFAULT.charAt(4);
    }

    /**
     * Verifies exception for negative index.
     */
    @Test (expected = IndexOutOfBoundsException.class)
    public void charAtNegativeIndexException() {
        DEFAULT.charAt(-1);
    }

    /**
     * Verifies exception for out of bounds index.
     */
    @Test (expected = IndexOutOfBoundsException.class)
    public void intAtIndexException() {
        DEFAULT.intAt(4);
    }

    /**
     * Verifies exception for negative index.
     */
    @Test (expected = IndexOutOfBoundsException.class)
    public void intAtNegativeIndexException() {
        DEFAULT.intAt(-1);
    }

    /**
     * Verifies exception for out of bounds index.
     */
    @Test (expected = IndexOutOfBoundsException.class)
    public void booleanAtIndexException() {
        DEFAULT.booleanAt(4);
    }

    /**
     * Verifies exception for negative index.
     */
    @Test (expected = IndexOutOfBoundsException.class)
    public void booleanAtNegativeIndexException() {
        DEFAULT.booleanAt(-1);
    }

    /**
     * Tests for charAt bit accessor.
     */
    @Test
    public void charAtTest() {
        assertEquals(DEFAULT.charAt(0), '1');
        assertEquals(DEFAULT.charAt(1), '0');
        sb.setBitAt(10, 0);
        assertEquals(sb.charAt(10), '0');
    }

    /**
     * Tests for intAt bit accessor.
     */
    @Test
    public void intAtTest() {
        assertEquals(DEFAULT.intAt(0), 1);
        assertEquals(DEFAULT.intAt(1), 0);
        sb.setBitAt(10, 0);
        assertEquals(sb.intAt(10), 0);
    }

    /**
     * Tests for booleanAt bit accessor.
     */
    @Test
    public void booleanAtTest() {
        assertEquals(DEFAULT.booleanAt(0), true);
        assertEquals(DEFAULT.booleanAt(1), false);
        sb.setBitAt(10, 0);
        assertEquals(sb.booleanAt(10), false);
    }

    /**
     * Tests for setBitAt bit mutators.
     */
    @Test
    public void setBitAtTest() {
        sb.setBitAt(3, '1');
        assertEquals(sb.intAt(3), 1);
        sb.setBitAt(3, '0');
        assertEquals(sb.intAt(3), 0);
        sb.setBitAt(10, '1');
        assertEquals(sb.intAt(10), 1);
        assertEquals(sb.intAt(9), 0);

        sb.setBitAt(3, 1);
        assertEquals(sb.intAt(3), 1);
        sb.setBitAt(3, 0);
        assertEquals(sb.intAt(3), 0);
        sb.setBitAt(10, 1);
        assertEquals(sb.intAt(10), 1);
        assertEquals(sb.intAt(9), 0);

        sb.setBitAt(3, true);
        assertEquals(sb.intAt(3), 1);
        sb.setBitAt(3, false);
        assertEquals(sb.intAt(3), 0);
        sb.setBitAt(10, true);
        assertEquals(sb.intAt(10), 1);
        assertEquals(sb.intAt(9), 0);
    }

    /**
     * Verifies toString returns correct string.
     */
    @Test
    public void toStringTest() {
        assertEquals(DEFAULT.toString(), DEFAULT_STRING);
        assertEquals(new StringOfBits("111").toString(), "111");
    }
}

