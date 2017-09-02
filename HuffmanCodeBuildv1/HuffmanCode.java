import java.util.HashMap;
import java.util.TreeMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.ArrayList;

/**
 * Implementation of Huffman coding. An instance of which stores a code used
 * for subsequent encoding and decoding of strings.
 *
 * @author Bennett Alex Myers
 * @version 12 October 2016
 */
public class HuffmanCode {

    /** The HuffmanTree corresponding to this HuffmanCode object. */
    private HuffmanTree codeTree;
    /** The symbol-to-code mapping for this HuffmanCode object. */
    private Map<Character, StringOfBits> codeMap;

    /**
     * Creates a Huffman code for a given seed string.
     * @param seed the string from which the code is generated
     */
    public HuffmanCode(final String seed) {
        HashMap<Character, Double> table = getFrequencyTable(seed);
        this.codeTree = generateCodeTree(table);
        this.codeMap = generateCodeTable(this.codeTree);
        assignCodes(this.codeTree);
    }

    /**
     * Creates a Huffman code for a given frequency table.
     * @param table the symbol frequency table from which the code is generated
     */
    public HuffmanCode(final HashMap<Character, Double> table) {
        this.codeTree = generateCodeTree(table);
        this.codeMap = generateCodeTable(this.codeTree);
        assignCodes(this.codeTree);
    }

    /**
     * Creates a Huffman code for a given mapping of symbols to codes.
     * @param hmap the mapping from symbols to their encodings.
     */
    public HuffmanCode(final Map<Character, StringOfBits> hmap) {
        this.codeMap = hmap;
    }

    /**
     * Encodes a string using the Huffman code of this object.
     * @param inputString the string to be encoded
     * @return the compressed encoding of the parameter
     */
    public StringOfBits encode(final String inputString) {
        StringOfBits code = new StringOfBits();
        char[] charArray = inputString.toCharArray();
        for (char c : charArray) {
            code.append(codeMap.get(c));
        }
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
        while (i < encodedString.length() || isLeaf(currentBranch)) {
            if (isLeaf(currentBranch)) {
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
        return this.codeMap;
    }

    /**
     * Generates a character frequency table for a given mapping of characters
     * to their counts.
     * @param inputString the string from which the table is generated
     * @return a character frequency table for the input string
     */
    private HashMap<Character, Double> getFrequencyTable(
            final String inputString) {
        HashMap<Character, Double> freqTable = new HashMap<>();
        HashMap<Character, Double> countMap = countChars(inputString);
        countMap.forEach((k, v) -> freqTable.put(k, v / inputString.length()));
        return freqTable;
    }

    /**
     * Create a mapping of characters from a string to the number of times
     * they appear in the string.
     * @param inputString the string whose characters are counted
     * @return a mapping from characters to counts
     */
    private HashMap<Character, Double> countChars(final String inputString) {
        HashMap<Character, Double> countMap = new HashMap<>();
        char[] charArray = inputString.toCharArray();
        for (char c: charArray) {
            if (countMap.containsKey(c)) {
                double currentCount = countMap.get(c);
                countMap.put(c, currentCount + 1);
            } else {
                countMap.put(c, 1.0);
            }
        }
        return countMap;
    }

    /**
     * Generates a HuffmanTree with no codes based on the given frequency
     * table.
     * @param table the frequency table from which to generate the tree
     * @return a HuffmanTree corresponding to the frequency table with no
     *         codes.
     */
    private HuffmanTree generateCodeTree(
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
     * @return a min priority queue of the leaves
     */
    private PriorityQueue<HuffmanTree> generateLeafQueue(
            final HashMap<Character, Double> table) {
        ArrayList<HuffmanTree> leafList = new ArrayList<>();
        table.forEach((k, v) -> leafList.add(new HuffmanTree(k,
                v, new StringOfBits())));
        return new PriorityQueue<>(leafList);
    }

    /**
     * Combine the first two trees contained in the given priority queue of
     * trees.
     * @param treeQueue the queue from which to pull trees to combine
     * @return the original queue with the top two elements combined reinserted
     *         into the queue.
     */
    private PriorityQueue<HuffmanTree> combineTrees(
            final PriorityQueue<HuffmanTree> treeQueue) {
        HuffmanTree leftTree = treeQueue.poll();
        HuffmanTree rightTree = treeQueue.poll();
        Double newFreq = leftTree.getFrequency() + rightTree.getFrequency();
        HuffmanTree newTree = new HuffmanTree(newFreq, leftTree, rightTree);
        treeQueue.offer(newTree);
        return treeQueue;
    }

    /**
     * Generate a code table based on a code tree.
     * @param tree the code tree from which to generate a table
     * @return the code table corresponding to the given tree
     */
    private Map<Character, StringOfBits> generateCodeTable(
            final HuffmanTree tree) {
        HashMap<Character, StringOfBits> codeTable = new HashMap<>();
        if (isLeaf(tree)) {
            codeTable.put(tree.getSymbol(), new StringOfBits());
            return codeTable;
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
    private Map<Character, StringOfBits> mergeCodeTables(
            final Map<Character, StringOfBits> table1,
            final Map<Character, StringOfBits> table2) {
        HashMap<Character, StringOfBits> table = new HashMap<>();
        table1.forEach((k, v)
                -> table.put(k, new StringOfBits("0").append(v)));
        table2.forEach((k, v)
                -> table.put(k, new StringOfBits("1").append(v)));
        return table;
    }

    /**
     * Assign codes to nodes of a given tree.
     * @param tree the tree whose nodes to assign codes
     */
    private void assignCodes(HuffmanTree tree) {
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

    private void generateTreeFromTable() {
        TreeMap<StringOfBits, Character> inverseMap = new TreeMap<>();

    }

    /**
     * Utility method to determine if a given HuffmanTree is a leaf.
     * @param tree the tree to be tested
     * @return true if the tree is a leaf, false otherwise
     */
    private static boolean isLeaf(HuffmanTree tree) {
        return (tree.getLeftChild() == null) && (tree.getRightChild() == null);
    }
}
