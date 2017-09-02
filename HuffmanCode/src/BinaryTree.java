import java.io.BufferedOutputStream;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * A generic binary tree whose root holds a non-null value.
 * <p>
 * The BinaryTree datatype is defined recursively as
 * the empty tree or
 * a rooted tree comprised of a root value,
 * a non-null reference to a left child of type BinaryTree, and
 * a non-null reference to a right child of type BinaryTree.
 * As this datatype represents a binary tree,
 * there must be no duplicate references nor any references
 * to the root.
 * </p>
 * <p>
 * The empty tree is distinguished by the following properties:<br>
 * <code>isEmpty() == true</code>, and<br>
 * <code>height() == -1</code>,<br>
 * <code>numberOfNodes() == 0</code>.<br>
 * Further, the empty tree throws exceptions when any of the following
 * methods is invoked:<br>
 * <code>getLeftChild()</code>,
 * <code>getRightChild()</code>,
 * <code>getValue()</code>,
 * <code>isLeaf()</code>,
 * <code>numberOfLeaves()</code>,
 * <code>setLeftChild()</code>,
 * <code>setRightChild()</code>,
 * <code>setValue()</code>.
 * </p>
 * <p>
 * Every leaf node has the empty tree as its left child
 * and the empty tree as its right child.
 * </p>
 * <p>
 * Two binary trees are equal if and only if<br>
 * (1) they have the same structure and<br>
 * (2) respective elements stored at respective nodes
 * in both trees are equal.<br>
 * <small>[Walicki, M., <i>Introduction to Mathematical Logic,</i>
 * World Scientific, 2011]</small>
 * </p>
 * <p>&nbsp;</p>
 * Terminology:<ul>
 * <li><em>ancestor</em> = a parent, grandparent,
 * great-grandparent, etc.</li>
 * <li><em>descendant</em> = a child, grandchild,
 * great-grandchild, etc.</li>
 * <li><em>height</em> = the length of the longest downward path to a leaf</li>
 * <li><em>internal</em> = a tree with at least one child</li>
 * <li><em>leaf</em> = a tree with no children</li>
 * <li><em>root</em> = any BinaryTree object</li>
 * <li><em>tree</em> = a BinaryTree with all its descendants</li>
 * <li><em>subtree of tree A</em> = a tree whose root is within tree A</li>
 * </ul>
 *
 * @param <T> the type of value stored by this tree
 * @author Bennett Alex Myers, Programming Pragmatics Fall 2016
 * @version 6 October 2016
 */
public class BinaryTree<T>
        implements Iterable<BinaryTree<T>>, java.io.Serializable {

    /**
     * Default return value for boolean methods.
     */
    private static final boolean DEFAULT_RETURN_BOOLEAN = false;
    /**
     * Default return value for int methods.
     */
    private static final int DEFAULT_RETURN_INT = -42;
    // Default return value for object methods is null.

    /**
     * Default state save/restore file name.
     */
    public static final String SERIAL_FILENAME = "bt.ser";

    /**
     * Serialization version indicator used to determine
     * if a file is compatible with this class.
     */
    private static final long serialVersionUID = 2016090422L;

    /**
     * Value of the root of this tree;
     * null if and only if empty tree.
     * @serial
     */
    private T rootValue;

    /**
     * Left child of the root of this tree.
     * <code>null</code> used as indicator of missing child.
     * @serial
     */
    private BinaryTree<T> leftChild;

    /**
     * Right child of the root of this tree.
     * <code>null</code> used as indicator of missing child.
     * @serial
     */
    private BinaryTree<T> rightChild;

    /**
     * Manages a singleton empty tree for leaf nodes.
     * @return the common empty tree
     */
    private BinaryTree<T> emptyTree() {
        return new BinaryTree<>();
    }

    /**
     * Constructs an empty tree.
     */
    public BinaryTree() {
        this.rootValue = null;
        this.leftChild = null;
        this.rightChild = null;
    }

    /**
     * Constructs a tree with no children
     * whose value is specified by the parameter.
     *
     * @param rootvalue the value stored at the root of the tree
     * @throws IllegalArgumentException if parameter is null
     */
    public BinaryTree(final T rootvalue)
            throws IllegalArgumentException {
        this(rootvalue, null, null);
    }

    /**
     * Constructs a tree with specified value,
     * left child, and right child.
     *
     * @param rootvalue  the value stored at the root of the tree
     * @param leftchild  the left child of the root;
     *                   <code>null</code> if no such child
     * @param rightchild the right child of the root;
     *                   <code>null</code> if no such child
     * @throws IllegalArgumentException if <code>rootvalue</code> parameter
     * is null
     */
    public BinaryTree(final T rootvalue,
                      final BinaryTree<T> leftchild,
                      final BinaryTree<T> rightchild)
            throws IllegalArgumentException {
        if (rootvalue == null) {
            throw new IllegalArgumentException();
        }
        this.rootValue = rootvalue;
        if (leftchild == null) {
            this.leftChild = emptyTree();
        } else {
            this.leftChild = leftchild;
        }
        if (rightchild == null) {
            this.rightChild = emptyTree();
        } else {
            this.rightChild = rightchild;
        }
    }

    /**
     * Empty tree predicate.
     *
     * @return <code>true</code> if this is an empty tree;
     * <code>false</code> otherwise
     */
    public boolean isEmpty() {
        return this.rootValue == null;
    }

    /**
     * Returns the number of nodes (subtrees) in this tree;
     * 0 if empty tree.
     *
     * @return the number of nodes (subtrees)
     */
    public int numberOfNodes() {
        int numNodes = 0;
        if (this.rootValue != null) {
            numNodes++;
            if (this.getLeftChild() != null) {
                numNodes += this.getLeftChild().numberOfNodes();
            }
            if (this.getRightChild() != null) {
                numNodes += this.getRightChild().numberOfNodes();
            }
        }
        return numNodes;
    }

    /**
     * Leaf predicate.
     *
     * @return <code>true</code> if this is a leaf;
     * <code>false</code> otherwise.
     * @throws NullPointerException if this tree is empty
     */
    public boolean isLeaf() throws NullPointerException {
        if (isEmpty()) {
            throw new NullPointerException();
        }
        return numberOfNodes() == 1;
    }

    /**
     * Returns the value of the root of this tree.
     *
     * @return the value of the root
     * @throws NullPointerException if this tree is empty
     */
    public T getValue() throws NullPointerException {
        if (isEmpty()) {
            throw new NullPointerException();
        }
        return this.rootValue;
    }

    /**
     * Returns the left child of this tree.
     * @return the left child; null if no such child
     * @throws NullPointerException if this tree is empty
     */
    public BinaryTree<T> getLeftChild() throws NullPointerException {
        if (isEmpty()) {
            throw new NullPointerException();
        }
        if (this.leftChild.isEmpty()) {
            return null;
        }
        return this.leftChild;
    }

    /**
     * Returns the right child of this tree.
     * @return the right child; null if no such child
     * @throws NullPointerException if this tree is empty
     */
    public BinaryTree<T> getRightChild() throws NullPointerException {
        if (isEmpty()) {
            throw new NullPointerException();
        }
        if (this.rightChild.isEmpty()) {
            return null;
        }
        return this.rightChild;
    }

    /**
     * Modifies the value of the root of this tree.
     * @param value the new value for the root
     * @throws NullPointerException if this tree is empty
     */
    public void setValue(final T value) throws NullPointerException {
        if (isEmpty()) {
            throw new NullPointerException();
        }
        this.rootValue = value;
    }

    /**
     * Replaces the left child of the root of this tree.
     * @param child the new left child for this tree;
     *              null or empty tree indicates no child
     * @throws NullPointerException if this tree is empty
     */
    public void setLeftChild(final BinaryTree<T> child) {
        if (isEmpty()) {
            throw new NullPointerException();
        }
        if (null == child) {
            this.leftChild = emptyTree();
        } else {
            this.leftChild = child;
        }
    }

    /**
     * Replaces the right child of the root of this tree.
     * @param child the new right child for this tree;
     *              null or empty tree indicates no child
     * @throws NullPointerException if this tree is empty
     */
    public void setRightChild(final BinaryTree<T> child) {
        if (isEmpty()) {
            throw new NullPointerException();
        }
        if (null == child) {
            this.rightChild = emptyTree();
        } else {
            this.rightChild = child;
        }
    }

    /**
     * Determines the number of leaves of this tree.
     * @return the number of leaves
     * @throws NullPointerException if this tree is empty
     */
    public int numberOfLeaves() throws NullPointerException {
        if (isEmpty()) {
            throw new NullPointerException();
        }
        int numLeaves = 0;
        if (this.isLeaf()) {
            numLeaves = 1;
        } else {
            if (!this.leftChild.isEmpty()) {
                numLeaves += this.leftChild.numberOfLeaves();
            }
            if (!this.rightChild.isEmpty()) {
                numLeaves += this.rightChild.numberOfLeaves();
            }
        }
        return numLeaves;
    }

    /**
     * Determines the height of this tree.
     * The height of a tree is the height of its root,
     * which is the number of edges on the longest downward path
     * between the root and a leaf.
     *
     * @return the height of this tree, -1 if empty
     */
    public final int height() {
        if (isEmpty()) {
            return -1;
        }
        int maxChildHeight = -1;
        if (!this.leftChild.isEmpty()) {
            maxChildHeight = this.leftChild.height();
        }
        if (!this.rightChild.isEmpty()) {
            maxChildHeight = Math.max(maxChildHeight, this.rightChild.height());
        }
        return 1 + maxChildHeight;
    }

    /**
     * Indicates whether some other object is "equal to" this one.
     * @param obj the reference object with which to compare
     * @return true if and only if
     * <ul>
     * <li>both are non-null</li>
     * <li>both have the same number of subtrees</li>
     * <li>both are the same height</li>
     * <li>if non-empty, both have equal root values</li>
     * <li>if left-children are present, they are equal to each other</li>
     * <li>if right-children are present, they are equal to each other</li>
     * </ul>
     * @see #hashCode()
     */
    @Override
    public boolean equals(final Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj.getClass() == this.getClass()) {
            return compareTrees(this, (BinaryTree<?>) obj);
        }
        return false;
    }

    /**
     * Returns a hash code value for this tree.
     * @return a hash code value for this tree
     * @see #equals(Object o)
     */
    @Override
    public int hashCode() {
        int mycode = 1;
        if (null != this.rootValue) {
            mycode = this.rootValue.hashCode();
        }
        if (null == this.leftChild) {
            mycode += 2;
        } else {
            mycode += this.leftChild.hashCode();
        }
        if (null == this.rightChild) {
            mycode += 2;
        } else {
            mycode += this.rightChild.hashCode();
        }
        return mycode;
    }

    /**
     * Returns a list of values in the order in which
     * the nodes would be visited using preorder traversal.
     * If this tree is empty, returns the empty list.
     * @return values of all nodes in preorder
     */
    public List<T> preorderValues() {
        List<T> preorderV = new ArrayList<>();
        if (this.numberOfNodes() != 0) {
            preorderV.add(this.getValue());
            if (this.getLeftChild() != null) {
                preorderV.addAll(this.getLeftChild().preorderValues());
            }
            if (this.getRightChild() != null) {
                preorderV.addAll(this.getRightChild().preorderValues());
            }
        }
        return preorderV;
    }

    /**
     * Returns a list of values in the order in which
     * the nodes would be visited using inorder traversal.
     * If this tree is empty, returns the empty list.
     * @return values of all nodes in inorder
     */
    public List<T> inorderValues() {
        List<T> inorderV = new ArrayList<>();
        if (this.numberOfNodes() != 0) {
            if (this.getLeftChild() != null) {
                inorderV.addAll(this.getLeftChild().inorderValues());
            }
            inorderV.add(this.getValue());
            if (this.getRightChild() != null) {
                inorderV.addAll(this.getRightChild().inorderValues());
            }
        }
        return inorderV;
    }

    /**
     * Returns a list of values in the order in which
     * the nodes would be visited using postorder traversal.
     * If this tree is empty, returns the empty list.
     * @return values of all nodes in postorder
     */
    public List<T> postorderValues() {
        List<T> postorderV = new ArrayList<>();
        if (this.numberOfNodes() != 0) {
            if (this.getLeftChild() != null) {
                postorderV.addAll(this.getLeftChild().postorderValues());
            }
            if (this.getRightChild() != null) {
                postorderV.addAll(this.getRightChild().postorderValues());
            }
            postorderV.add(this.getValue());
        }
        return postorderV;
    }

    /**
     * Returns a list of subtrees in the order in which
     * they would be visited using preorder traversal.
     * If this tree is empty, returns the empty list.
     * @return all subtrees in preorder
     */
    public List<BinaryTree<T>> preorderSubtrees() {
        List<BinaryTree<T>> preorderList
                = new ArrayList<>();
        if (this.numberOfNodes() != 0) {
            preorderList.add(this);
            if (this.getLeftChild() != null) {
                preorderList.addAll(this.getLeftChild().preorderSubtrees());
            }
            if (this.getRightChild() != null) {
                preorderList.addAll(this.getRightChild().preorderSubtrees());
            }
        }
        return preorderList;
    }

    /**
     * Returns a list of subtrees in the order in which
     * they would be visited using inorder traversal.
     * If this tree is empty, returns the empty list.
     * @return all subtrees in inorder
     */
    public List<BinaryTree<T>> inorderSubtrees() {
        List<BinaryTree<T>> inorderList
                = new ArrayList<>();
        if (this.numberOfNodes() != 0) {
            if (this.getLeftChild() != null) {
                inorderList.addAll(this.getLeftChild().inorderSubtrees());
            }
            inorderList.add(this);
            if (this.getRightChild() != null) {
                inorderList.addAll(this.getRightChild().inorderSubtrees());
            }
        }
        return inorderList;
    }

    /**
     * Returns a list of subtrees in the order in which
     * they would be visited using postorder traversal.
     * If this tree is empty, returns the empty list.
     * @return all subtrees in postorder
     */
    public List<BinaryTree<T>> postorderSubtrees() {
        List<BinaryTree<T>> postorderList
                = new ArrayList<>();
        if (this.numberOfNodes() != 0) {
            if (this.getLeftChild() != null) {
                postorderList.addAll(this.getLeftChild().postorderSubtrees());
            }
            if (this.getRightChild() != null) {
                postorderList.addAll(this.getRightChild().postorderSubtrees());
            }
            postorderList.add(this);
        }
        return postorderList;
    }

    /**
     * Converts a list of trees into a list of the
     * root values of those trees.
     * If the parameter is an empty list, returns the empty list.
     * @param <E>         the type of values stored by trees in the parameter
     * @param listOfTrees the list of trees from which to extract values
     * @return all root values in the order they appear in the parameter
     */
    public static <E> List<E> values(
            final List<BinaryTree<E>> listOfTrees) {
        List<E> listOfValues = new ArrayList<>();
        for (BinaryTree<E> bt : listOfTrees) {
            if (bt == null) {
                listOfValues.add(null);
            } else if (!bt.isEmpty()) {
                listOfValues.add(bt.getValue());
            }
        }
        return listOfValues;
    }

    /**
     * Returns an iterator over the subtrees (nodes) of this tree.
     * @return an iterator over subtrees of this tree
     */
    @Override
    public java.util.Iterator<BinaryTree<T>> iterator() {
        return this.postorderSubtrees().iterator();
    }

    /**
     * Renders tree as a non-null and non-empty string.
     * The rendering must include every value of the root of
     * every subtree in the tree.
     * It may include additional characters to provide
     * human-readable results.
     * @return string rendering of this object
     */
    @Override
    public String toString() {
        return indentedToString(0);
    }

    /**
     * Provides indented multi-line rendering of tree.
     * @param level the depth of this tree in overall rendering
     * @return rendering of tree using multiple lines and indentation
     */
    private String indentedToString(final int level) {
        String indent = "";
        if (0 < level) {
            indent = String.format("%" + (level * 2) + "s", "");
        }
        if (isEmpty()) {
            return indent + "X_";
        }
        String rendering = indent;
        rendering += "[";
        rendering += "\"" + getValue() + "\": \n";
        if (!this.leftChild.isEmpty()) {
            rendering += this.leftChild.indentedToString(level + 1);
        } else {
            rendering += indent + " _";
        }
        rendering += ",\n";
        if (!this.rightChild.isEmpty()) {
            rendering += this.rightChild.indentedToString(level + 1);
        } else {
            rendering += indent + " _";
        }
        rendering += "]";
        return rendering;
    }

    /**
     * Saves this tree to a file.
     * @param filename the name of the file in which to save this tree;
     *                 if null, uses default file name
     * @return <code>true</code> if successful save;
     * <code>false</code> otherwise
     * @throws IOException if unexpected IO error
     */
    public final boolean save(final String filename)
            throws IOException {
        boolean success = true;
        String treeFileName = filename;
        if (treeFileName == null) {
            treeFileName = BinaryTree.SERIAL_FILENAME;
        }
        // Serialize the tree.
        try {
            OutputStream file = new FileOutputStream(treeFileName);
            OutputStream buffer = new BufferedOutputStream(file);
            ObjectOutput output = new ObjectOutputStream(buffer);
            try {
                output.writeObject(this);
            } finally {
                output.close();
            }
        } catch (IOException ex) {
            System.err.println("Unsuccessful save. " + ex);
            throw ex;
        }

        // Attempt to deserialize the graph as verification.
        try {
            InputStream file = new FileInputStream(treeFileName);
            InputStream buffer = new BufferedInputStream(file);
            ObjectInput input = new ObjectInputStream(buffer);
            try {
                BinaryTree<T> restored = readObject(input);
                // Use toString for quick check.
                if (!this.toString().equals(restored.toString())) {
                    success = false;
                }
                // Use compareTrees predicate for deeper check.
                if (!compareTrees(this, restored)) {
                    success = false;
                }
            } finally {
                input.close();
            }
        } catch (ClassNotFoundException ex) {
            // System.err.println("save: restore-check Class not found. " + ex);
            success = false;
        } catch (IOException ex) {
            // System.err.println("save: restore-check exception.  " + ex);
            success = false;
        }
        return success;    }

    /**
     * Restores this tree from a file.
     * <br><em>Postconditions:</em>
     * <blockquote>If successful, previous contents of this tree have
     * been replaced by the contents of the file.
     * If unsuccessful, content of the tree is unchanged.</blockquote>
     * @param filename the name of the file from which to restore this tree;
     *                 if null, uses default file name
     * @return <code>true</code> if successful restore;
     * <code>false</code> otherwise
     * @throws IOException if unexpected IO error
     */
    public final boolean restore(final String filename) throws
            IOException {
        boolean success = false;
        String treeFileName = filename;
        if (treeFileName == null) {
            treeFileName = BinaryTree.SERIAL_FILENAME;
        }
        BinaryTree<T> restored = null;
        try {
            InputStream file = new FileInputStream(treeFileName);
            InputStream buffer = new BufferedInputStream(file);
            ObjectInput input = new ObjectInputStream(buffer);
            try {
                BinaryTree<T> retrieved = readObject(input);
                restored = retrieved;
            } finally {
                input.close();
                success = true;
            }
        } catch (ClassNotFoundException ex) {
            // ClassNotFoundException is common.
            success = false;
        } catch (FileNotFoundException ex) {
            // FileNoteFoundException is common.
            success = false;
        } catch (IOException ex) {
            // Throw other IOExceptions as unexpected.
            throw ex;
        }
        if (restored == null) {
            // Invalid; BinaryTree cannot be null.
            success = false;
        } else {
            this.rootValue = restored.rootValue;
            this.leftChild = restored.leftChild;
            this.rightChild = restored.rightChild;
        }
        return success;    }

    /**
     * Utility to read object from ObjectInputStream and cast to appropriate
     * type to work around type erasure. Suppresses unchecked cast warnings.
     * @param input the object input from which to read
     * @return the object read, cast as type T
     * @throws IOException if IO error
     * @throws ClassNotFoundException if class could not be found
     */
    @SuppressWarnings("unchecked")
    private BinaryTree<T> readObject(final ObjectInput input)
            throws IOException, ClassNotFoundException {
        return (BinaryTree<T>) input.readObject();
    }

    /**
     * Utility to compare two trees for shape and contents.
     *
     * @param bt1 first tree for comparison
     * @param bt2 second tree for comparison
     * @return true if and only if both are non-null,
     * have the same number of nodes and same height,
     * and if non-empty then also have
     * equal root values, and if present
     * both the left children and right children
     * also return true to this predicate
     */
    private boolean compareTrees(final BinaryTree<?> bt1,
                                 final BinaryTree<?> bt2) {
        if (bt1 == null || bt2 == null) {
            return false;
        }
        if (bt1.isEmpty()) {
            return bt2.isEmpty();
        }
        if (bt1.height() != bt2.height()) {
            return false;
        }
        if (!bt1.getValue().equals(bt2.getValue())) {
            return false;
        }
        if (bt1.getLeftChild() == null) {
            if (bt2.getLeftChild() != null) {
                return false;
            }
        } else if (bt2.getLeftChild() == null) {
            return false;
        }
        if (bt1.getLeftChild() != null
                && !compareTrees(bt1.getLeftChild(), bt2.getLeftChild())) {
            return false;
        }
        if (bt1.getRightChild() == null) {
            if (bt2.getRightChild() != null) {
                return false;
            }
        } else if (bt2.getRightChild() == null) {
            return false;
        }
        if (bt1.getRightChild() != null
                && !compareTrees(bt1.getRightChild(), bt2.getRightChild())) {
            return false;
        }
        return true;
    }
}
