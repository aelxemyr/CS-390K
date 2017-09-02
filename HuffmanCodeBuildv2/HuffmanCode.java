import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.ArrayList;

/**
 * Implementation of Huffman coding. An instance of stores a code used
 * for subsequent encoding and decoding of strings.
 *
 * @author Bennett Alex Myers
 * @version 17 October 2016
 */
public class HuffmanCode {

    /** The HuffmanTree corresponding to this HuffmanCode object. */
    private HuffmanTree codeTree;
    /** The symbol-to-code mapping for this HuffmanCode object. */
    private Map<Character, StringOfBits> codeTable;

    /**
     * Creates a Huffman code for a given seed string.
     * @param seed the string from which the code is generated
     */
    public HuffmanCode(final String seed) {
        this(getFrequencyTable(seed));
    }

    /**
     * Creates a Huffman code for a given frequency table.
     * @param table the symbol frequency table from which the code is generated
     */
    public HuffmanCode(final HashMap<Character, Double> table) {
        this.codeTree = generateCodeTree(table);
        assignCodes(this.codeTree);
        this.codeTable = generateCodeTable(this.codeTree);
    }

    /**
     * Creates a Huffman code for a given mapping of symbols to codes.
     * @param hmap the mapping from symbols to their encodings.
     */
    public HuffmanCode(final Map<Character, StringOfBits> hmap) {
        this.codeTable = hmap;
        this.codeTree = generateTreeFromTable(this.codeTable);
    }

    /**
     * Encodes a string using the Huffman code of this object.
     * @param inputString the string to be encoded
     * @return the compressed encoding of the parameter
     */
    public StringOfBits encode(final String inputString) {
        StringOfBits code = new StringOfBits();
        inputString.chars().forEach(c -> code.append(codeTable.get((char) c)));
        return code;
    }

    /**
     * Decodes a bit string (0s and 1s) using the Huffman code of this object.
     * @param encodedString the string to be decoded
     * @return the decoded version of the parameter
     */
    public String decode(final StringOfBits encodedString) {
        HuffmanTree currentBranch = codeTree;
        String decodedString = "";
        int i = 0;
        while (i < encodedString.length() || currentBranch.isLeaf()) {
            if (currentBranch.isLeaf()) {
                decodedString += currentBranch.getSymbol();
                currentBranch = codeTree;
            } else if (encodedString.intAt(i) == 0) {
                currentBranch = currentBranch.getLeftChild();
                i++;
            } else {
                currentBranch = currentBranch.getRightChild();
                i++;
            }
        }
        return decodedString;
    }

    /**
     * Returns the mapping of symbols to codes for this object.
     * @return this Huffman code as a map
     */
    public Map<Character, StringOfBits> getCode() {
        return new HashMap<>(this.codeTable);
    }

    /**
     * Generate a character frequency table for a given string.
     * @param inputString the string from which the table is generated
     * @return a character frequency table for the input string
     */
    private static HashMap<Character, Double> getFrequencyTable(
            final String inputString) {
        HashMap<Character, Double> freqTable = new HashMap<>();
        countChars(inputString).forEach((symbol, count)
                -> freqTable.put(symbol, count / inputString.length()));
        return freqTable;
    }

    /**
     * Create a mapping of characters from a string to the number of times
     * they appear in the string.
     * @param inputString the string whose characters are counted
     * @return a mapping from characters to counts
     */
    private static HashMap<Character, Double> countChars(
            final String inputString) {
        HashMap<Character, Double> countMap = new HashMap<>();
        inputString.chars().mapToObj(c -> (char) c)
                           .forEach(c -> {
                               countMap.putIfAbsent(c, 0.0);
                               countMap.replace(c, countMap.get(c) + 1.0);
                           });
        return countMap;
    }

    /**
     * Generate a HuffmanTree based on the given frequency table.
     * @param table the frequency table from which to generate the tree
     * @return a HuffmanTree corresponding to the frequency table
     */
    private static HuffmanTree generateCodeTree(
            final HashMap<Character, Double> table) {
        PriorityQueue<HuffmanTree> treeQueue = generateLeafQueue(table);
        while (treeQueue.size() > 1) {
            treeQueue = combineTrees(treeQueue);
        }
        return treeQueue.poll();
    }

    /**
     * Generate a priority queue containing leaves corresponding to a given
     * frequency table.
     * @param table the frequency table from which leaves are generated
     * @return a priority queue of the leaves
     */
    private static PriorityQueue<HuffmanTree> generateLeafQueue(
            final HashMap<Character, Double> table) {
        ArrayList<HuffmanTree> leafList = new ArrayList<>();
        table.forEach((symbol, frequency) -> leafList.add(
                new HuffmanTree(symbol, frequency, new StringOfBits())));
        return new PriorityQueue<>(leafList);
    }

    /**
     * Combine the first two trees contained in the given priority queue of
     * trees.
     * @param treeQueue the queue from which to pull trees to combine
     * @return the original queue with the top two elements combined reinserted
     *         into the queue.
     */
    private static PriorityQueue<HuffmanTree> combineTrees(
            final PriorityQueue<HuffmanTree> treeQueue) {
        HuffmanTree leftTree = treeQueue.poll();
        HuffmanTree rightTree = treeQueue.poll();
        Double newFreq = leftTree.getFrequency() + rightTree.getFrequency();
        treeQueue.offer(new HuffmanTree(newFreq, leftTree, rightTree));
        return treeQueue;
    }

    /**
     * Generate a code table based on a code tree.
     * @param tree the code tree from which to generate a table
     * @return the code table corresponding to the given tree
     */
    private static Map<Character, StringOfBits> generateCodeTable(
            final HuffmanTree tree) {
        HashMap<Character, StringOfBits> table = new HashMap<>();
        if (tree.isLeaf()) {
            table.put(tree.getSymbol(), new StringOfBits());
            return table;
        } else {
            return mergeCodeTables(generateCodeTable(tree.getLeftChild()),
                    generateCodeTable(tree.getRightChild()));
        }
    }

    /**
     * Merge two code tables together.
     * @param table1 the first table to merge
     * @param table2 the second table to merge
     * @return the result of merging the two tables
     */
    private static Map<Character, StringOfBits> mergeCodeTables(
            final Map<Character, StringOfBits> table1,
            final Map<Character, StringOfBits> table2) {
        HashMap<Character, StringOfBits> table = new HashMap<>();
        table1.forEach((symbol, code)
                -> table.put(symbol, new StringOfBits("0").append(code)));
        table2.forEach((symbol, code)
                -> table.put(symbol, new StringOfBits("1").append(code)));
        return table;
    }

    /**
     * Assign codes to nodes of a given tree.
     * @param tree the tree whose nodes to assign codes
     */
    private static void assignCodes(final HuffmanTree tree) {
        if (tree.getCode() == null) {
            tree.setCode(new StringOfBits());
        }
        HuffmanTree left = tree.getLeftChild();
        if (left != null) {
            left.setCode(new StringOfBits(tree.getCode()).append(0));
            assignCodes(left);
        }
        HuffmanTree right = tree.getRightChild();
        if (right != null) {
            right.setCode(new StringOfBits(tree.getCode()).append(1));
            assignCodes(right);
        }
    }

    /**
     * Generate a HuffmanTree based on a symbol-to-code mapping.
     * @param code a mapping of symbols to codes
     * @return a HuffmanTree whose structure reflects the given code
     */
    private static HuffmanTree generateTreeFromTable(
            final Map<Character, StringOfBits> code) {
        return generateTreeFromTableInner(getInverseCodeMap(code), "");
    }

    /**
     * Generate a HuffmanTree based on a mapping of codes (string
     * representations of bit strings) to symbols.
     * @param inverseMap a mapping of bit strings to characters
     * @param currentCode the bit string corresponding to the current location
     *                    in the recursive structure of the tree being
     *                    constructed.
     * @return a HuffmanTree whose structure reflects the given mapping of codes
     *         to symbols.
     */
    private static HuffmanTree generateTreeFromTableInner(
            final Map<String, Character> inverseMap,
            final String currentCode) {
        Character symbol = inverseMap.getOrDefault(currentCode, null);
        if (symbol != null) {
            return new HuffmanTree(symbol, null,
                    new StringOfBits(currentCode), null, null);
        } else {
            return new HuffmanTree(null, null, new StringOfBits(currentCode),
                    generateTreeFromTableInner(inverseMap, currentCode + "0"),
                    generateTreeFromTableInner(inverseMap, currentCode + "1"));
        }
    }

    /**
     * Return the inverse of the given symbol-to-code mapping as a mapping of
     * string representations of code to symbols.
     * @param codeMap the mapping whose inverse to retrieve
     * @return the code-to-symbol mapping that is the inverse of the parameter
     */
    private static HashMap<String, Character> getInverseCodeMap(
            final Map<Character, StringOfBits> codeMap) {
        HashMap<String, Character> inverseMap = new HashMap<>();
        codeMap.forEach((symbol, code)
                -> inverseMap.put(makeBitString(code), symbol));
        return inverseMap;
    }

    /**
     * Convert a StringOfBits to a String of its bit string.
     * @param bits the StringOfBits to be converted
     * @return the string representation of the bit string
     */
    private static String makeBitString(final StringOfBits bits) {
        String bitString = "";
        for (int i = 0; i < bits.length(); i++) {
            bitString += bits.charAt(i);
        }
        return bitString;
    }
}
