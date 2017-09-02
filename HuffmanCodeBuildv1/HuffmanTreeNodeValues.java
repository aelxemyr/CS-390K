/**
 * The collected values stored at a HuffmanTree node which include:
 * {@link java.lang.Character Character} symbol,
 * {@link java.lang.Double Double} frequency, and
 * {@link StringOfBits StringOfBits} code.
 *
 * @author Bennett Alex Myers
 * @version 12 October 2016
 */
public class HuffmanTreeNodeValues {

    /** The symbol stored in this node. */
    private Character nodeSymbol;
    /** The frequency stored in this node. */
    private Double nodeFrequency;
    /** The code stored in this node. */
    private StringOfBits nodeCode;

    /**
     * Constructor that sets all values to null.
     */
    public HuffmanTreeNodeValues() {
        this.nodeSymbol = null;
        this.nodeFrequency = null;
        this.nodeCode = null;
    }

    /**
     * Fully parameterized constructor.
     * @param symbol the symbol
     * @param frequency the frequency of the symbol
     * @param code the code for the symbol
     */
    public HuffmanTreeNodeValues(final Character symbol,
                                 final Double frequency,
                                 final StringOfBits code) {
        this.nodeSymbol = symbol;
        this.nodeFrequency = frequency;
        this.nodeCode = code;
    }

    /**
     * Accesses the symbol.
     * @return the symbol
     */
    public Character getSymbol() {
        return this.nodeSymbol;
    }

    /**
     * Accesses the code.
     * @return the code
     */
    public StringOfBits getCode() {
        return this.nodeCode;
    }

    /**
     * Accesses the frequency.
     * @return the frequency
     */
    public Double getFrequency() {
        return this.nodeFrequency;
    }

    /**
     * Modifies the symbol.
     * @param newsymbol the replacement symbol
     */
    public void setSymbol(final Character newsymbol) {
        this.nodeSymbol = newsymbol;
    }

    /**
     * Modifies the code.
     * @param newcode the replacement code
     */
    public void setCode(final StringOfBits newcode) {
        this.nodeCode = newcode;
    }

    /**
     * Modifies the frequency.
     * @param newfrequency the replacement frequency
     */
    public void setFrequency(final Double newfrequency) {
        this.nodeFrequency = newfrequency;
    }

    @Override
    public String toString() {
        return "(" + this.nodeSymbol + ", "
                + this.nodeFrequency + ", "
                + this.nodeCode + ")";
    }
}

