import java.util.ArrayList;
import java.util.Arrays;

/**
 * Represents a string of bit values (0 or 1).
 *
 * @author Bennett Alex Myers
 * @version 12 October 2016
 */
public class StringOfBits {

    /** Default bit array for undefined behavior. */
    private static final ArrayList<Integer> DEFAULT =
            new ArrayList<>(Arrays.asList(0, 0, 0, 0, 0, 0, 0, 0, 0, 0));
    /** Internal bit array. */
    private ArrayList<Integer> bits;

    /**
     * Constructs the empty bit string; length == 0.
     */
    public StringOfBits() {
        this.bits = new ArrayList<>(0);
    }

    /**
     * Copy constructor.
     * @param sb the object to be cloned
     */
    public StringOfBits(final StringOfBits sb) {
        this.bits = new ArrayList<>();
        for (int i = 0; i < sb.length(); i++) {
            this.bits.add(sb.intAt(i));
        }
    }

    /**
     * Constructs a bit string from a sequence of '0' and '1' characters. If
     * the parameter is not comprised of '0' and '1' characters the behavior
     * is undefined.
     * @param chars the sequence to convert into bits
     */
    public StringOfBits(final CharSequence chars) {
        String bitString = chars.toString();
        if (bitString.matches("[01]*")) {
            this.bits = toBitArray(bitString);
        } else {
            this.bits = DEFAULT;
        }
    }

    /**
     * Returns the length of this bit string.
     * @return the number of bits in this string
     */
    public int length() {
        return bits.size();
    }

    /**
     * Appends the bit string representation of the parameter to this bit
     * string. If the parameter is not '0' or '1' the behavior of this method
     * is undefined.
     * @param c the bit to append
     * @return a reference to this bit string
     */
    public StringOfBits append(final char c) {
        this.bits.add(charToInt(c));
        return this;
    }

    /**
     * Appends the bit string representation of the parameter to this bit
     * string; false corresponds to 0, true corresponds to 1.
     * @param b the bit to append
     * @return a reference to this bit string
     */
    public StringOfBits append(final boolean b) {
        this.bits.add(booleanToInt(b));
        return this;
    }

    /**
     * Appends the bit string representation of the parameter to this bit
     * string. Each digit represents a single bit. If any digit of the
     * parameter is not 0 or 1 the behavior is undefined.
     * @param i an integer representation of a bit sequence
     * @return a reference to this bit string
     */
    public StringOfBits append(final int i) {
        this.bits.add(constrainInt(i));
        return this;
    }

    /**
     * Appends the bit string representation of the parameter to this bit
     * string. Each substring of "0" corresponds to 0; each "1" corresponds to
     * 1. If the string contains other than "0" and "1" the behavior of this
     * method is undefined.
     * @param str a string
     * @return a reference to this bit string
     */
    public StringOfBits append(final CharSequence str) {
        ArrayList<Integer> bitArray = toBitArray(str.toString());
        this.bits.addAll(bitArray);
        return this;
    }

    /**
     * Appends the parameter to this bit string.
     * @param bitstr a bit string to be appended
     * @return a reference to this bit string
     */
    public StringOfBits append(final StringOfBits bitstr) {
        for (int i = 0; i < bitstr.length(); i++) {
            this.bits.add(bitstr.intAt(i));
        }
        return this;
    }

    /**
     * Returns a char corresponding to the bit at the specified index.
     * @param index the index of the desired bit value
     * @return the char value at the specified index
     * @throws IndexOutOfBoundsException if index is negative or greater than
     * or equal to length()
     */
    public char charAt(final int index) throws IndexOutOfBoundsException {
        if (index >= length() || index < 0) {
            throw new IndexOutOfBoundsException();
        }
        if (bits.get(index) == 0) {
            return '0';
        } else {
            return '1';
        }
    }

    /**
     * Returns an int corresponding to the bit at the specified index.
     * @param index the index of the desired bit value
     * @return the int value corresponding to the specified index (0 == 0;
     * 1 == 1)
     * @throws IndexOutOfBoundsException if index is negative or greater than
     * or equal to length()
     */
    public int intAt(final int index) throws IndexOutOfBoundsException {
        if (index >= length() || index < 0) {
            throw new IndexOutOfBoundsException();
        }
        return bits.get(index);
    }

    /**
     * Returns a boolean corresponding to the bit at the specified index.
     * @param index the index of the desired value
     * @return the boolean at the specified index (0 == false; 1 == true)
     * @throws IndexOutOfBoundsException if index is negative or greater than
     * or equal to length()
     */
    public boolean booleanAt(final int index) throws IndexOutOfBoundsException {
        if (index >= length() || index < 0) {
            throw new IndexOutOfBoundsException();
        }
        return bits.get(index) == 1;
    }

    /**
     * Sets the bit at the specified index. If the parameter is not '0' or '1'
     * the behavior of this method is undefined.
     * @param index the index of the bit to modify
     * @param c the new value ('0' == 0; '1' == 1)
     */
    public void setBitAt(final int index, final char c) {
        if (index < length()) {
            this.bits.set(index, charToInt(c));
        } else {
            while (this.bits.size() < index) {
                this.bits.add(0);
            }
            this.bits.add(charToInt(c));
        }
    }

    /**
     * Sets the bit at the specified index. If the parameter is not 0 or 1 the
     * behavior of this method is undefined.
     * @param index the index of the bit to modify
     * @param i the new value (0 == 0; 1 == 1)
     */
    public void setBitAt(final int index, final int i) {
        if (index < length()) {
            this.bits.set(index, constrainInt(i));
        } else {
            while (this.bits.size() < index) {
                this.bits.add(0);
            }
            this.bits.add(constrainInt(i));
        }
    }

    /**
     * Sets the bit at the specified index.
     * @param index the index of the bit to modify
     * @param b the new value (false == 0; true == 1)
     */
    public void setBitAt(final int index, final boolean b) {
        if (index < length()) {
            this.bits.set(index, booleanToInt(b));
        } else {
            while (this.bits.size() < index) {
                this.bits.add(0);
            }
            this.bits.add(booleanToInt(b));
        }
    }

    @Override
    public String toString() {
        String str = "";
        for (int i : this.bits) {
            str += i;
        }
        return str;
    }

    /**
     * Utility method for converting string representations of bit string
     * to an array of ints.
     * @param bitString the string to be converted
     * @return an arraylist of bits; if the original string contained
     *         characters other than 0 or 1, they are converted to 0.
     */
    private static ArrayList<Integer> toBitArray(final String bitString) {
        char[] charArray = bitString.toCharArray();
        ArrayList<Integer> bitArray = new ArrayList<>(charArray.length);
        for (int i = 0; i < charArray.length; i++) {
            if (charArray[i] == '1') {
                bitArray.add(1);
            } else {
                bitArray.add(0);
            }
        }
        return bitArray;
    }

    /**
     * Utility method to convert char representation of bit string to int.
     * @param c the char to be converted
     * @return 1 if the char is '1', 0 otherwise
     */
    private static int charToInt(final char c) {
        if (c == '1') {
            return 1;
        } else {
            return 0;
        }
    }

    /**
     * Utility method to convert boolean representation of bit string to int.
     * @param b the boolean to be converted
     * @return 1 if the boolean is true, 0 otherwise
     */
    private static int booleanToInt(final boolean b) {
        if (b) {
            return 1;
        } else {
            return 0;
        }
    }

    /**
     * Utility method to constrain range of ints.
     * @param i the int to be constrained
     * @return 1 if the int is equal to 1, 0 otherwise
     */
    private static int constrainInt(final int i) {
        if (i == 1) {
            return i;
        } else {
            return 0;
        }
    }
}
