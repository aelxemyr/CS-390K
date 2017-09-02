import java.util.Collection;

/**
 * <p>Abstract class for solution calculator to weight composition problem.
 * This class provides an implementation of a constructor using a family
 * of weights, to be called by subclasses.
 *
 * Subclasses must implement both the instance and the static version of the
 * <code>calculateSolution</code> method. The instance method is specified by
 * the abstract method in this class, and the static method should have the
 * following signature:</p>
 *
 * <pre>public static Collection&lt;Weight&gt; calculateSolution(
 *                  WeightFamily family, Weight desiredTotalWeight)</pre>
 *
 * @author CS 390K Section 001
 * @version $Id: BalanceWeightSystem.java 446 2016-11-17 17:15:07Z joe $
 */
public abstract class BalanceWeightSystem {

    /** The family of weights used for this system. */
    private WeightFamily familyOfWeights;

    /**
     * Create a new balance weight system with the given family of weights.
     *
     * @param family the family of weights with which to calculate a solution
     */
    public BalanceWeightSystem(final WeightFamily family) {
        this.familyOfWeights = family;
    }

    /**
     * Return the family of weights associated with this system.
     *
     * @return the family of weights associated with this system
     */
    public WeightFamily getFamily() {
        return new WeightFamily(this.familyOfWeights.getWeights());
    }

    /**
     * Given a desired total weight, calculate and return a minimum-sized
     * collection of weights from this object's weight family such that the
     * sum of the weights of the solution is the given desired total weight.
     *
     * @param desiredTotalWeight the desired total weight of the solution
     * @return a minimum-sized collection of weights whose sum is the given
     *         desired total weight
     */
    public abstract Collection<Weight> calculateSolution(
            final Weight desiredTotalWeight);
}

