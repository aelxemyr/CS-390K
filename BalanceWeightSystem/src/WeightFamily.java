import java.util.Collection;
import java.util.Iterator;
import java.util.TreeSet;
import java.util.Collections;
import java.util.stream.Collectors;

/**
 * Represents a family of weights in the weight composition problem. A family
 * of weights is a nonempty set of weights possessing positive integer values
 * and must contain the unit weight.
 *
 * @author Bennett Alex Myers
 * @version 18 November 2016
 */
public class WeightFamily implements Iterable<Weight> {

    /** The unit weight. */
    private static final Weight UNIT_WEIGHT = new Weight(1);

    /** The set containing this family of weights. */
    private TreeSet<Weight> family;

    /**
     * Creates the default family of weights consisting of only the unit
     * weight.
     */
    public WeightFamily() {
        this(Collections.singleton(UNIT_WEIGHT));
    }

    /**
     * Create a new family of weights based on a given collection of weights.
     * The resulting family of weights must be a nonempty set of weights
     * containing the unit weight.
     * Note that the given collection of weights may be empty, may contain null
     * references and may not contain the unit weight. The constructor should
     * always supply the unit weight regardless of its presence in the given
     * collection, and should ignore null references within the collection
     * when creating a family.
     * @param weights the collection of weights from which to create a family
     *                of weights
     * @throws IllegalArgumentException if the parameter <code>weights</code>
     *         is null
     */
    public WeightFamily(final Collection<Weight> weights)
            throws IllegalArgumentException {
        if (weights == null) {
            throw new IllegalArgumentException("Collection cannot be null.");
        }
        this.family = weights
                .stream()
                .filter(e -> (e != null))
                .collect(Collectors.toCollection(TreeSet::new));
        this.family.add(UNIT_WEIGHT);
    }

    /**
     * Return the collection of weights associated with this family of weights.
     * @return the collection of weights associated with this family of weights
     */
    public Collection<Weight> getWeights() {
        return new TreeSet<>(this.family);
    }

    /**
     * Return the size of this family of weights.
     * @return the size of this family of weights
     */
    public int size() {
        return this.family.size();
    }

    /**
     * Returns an iterator over the weights in this family of weights in their
     * natural order.
     * @return an iterator over the weights in this family of weights in their
     *         natural order
     */
    @Override
    public Iterator<Weight> iterator() {
        return this.family.iterator();
    }

    /**
     * Renders this weight family as human-readable string.
     * The string begins with "[" and ends with "]".
     * The middle of the string includes the {@link Weight#toString}
     * rendering of each member weight separated by ", ".
     * For example, if the renderings of the members of this family
     * were "1", "5", "9", then the string returned by this method
     * would be "[1, 5, 9]".
     * @return the rendering of this family as a string
     */
    @Override
    public String toString() {
        return this.family.toString();
    }
}
