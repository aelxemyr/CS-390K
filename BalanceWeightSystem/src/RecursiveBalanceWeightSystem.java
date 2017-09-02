import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;

/**
 * An implementation of BalanceWeightSystem utilizing a recursive algorithm.
 *
 * @author Bennett Alex Myers
 * @version 11/18/2016
 */
public class RecursiveBalanceWeightSystem
        extends BalanceWeightSystem {

    /**
     * Given a family of weights, create a new balance weight system based on
     * that family which implements a recursive algorithm.
     *
     * @param family the family of weights with which to calculate a solution
     */
    public RecursiveBalanceWeightSystem(final WeightFamily family) {
        super(family);
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
    public Collection<Weight> calculateSolution(
            final Weight desiredTotalWeight) {
        return calculateSolution(getFamily(), desiredTotalWeight);
    }

    /**
     * Given a family of weights and a desired total weight, calculate and
     * return a minimum-sized collection of weights such that the sum of the
     * weights of the solution is the given desired total weight.
     *
     * @param family the family of weights with which to calculate a solution
     * @param desiredTotalWeight the desired total weight of the solution
     * @return a minimum-sized collection fo weights whose sum is the given
     *         desired total weight
     */
    public static Collection<Weight> calculateSolution(
            final WeightFamily family,
            final Weight desiredTotalWeight) {
        int goal = desiredTotalWeight.getValue();
        HashSet<List<Weight>> partitions = new HashSet<>();
        for (Weight w : family) {
            int current = w.getValue();
            ArrayList<Weight> solution = new ArrayList<>();
            if (current <= goal) {
                if (current < goal) {
                    solution.addAll(
                        calculateSolution(family, new Weight(goal - current)));
                }
                solution.add(w);
                solution.sort(null);
                partitions.add(solution);
            }
        }
        return minimum(partitions);
    }

    /**
     * Utility method that returns the minimum-sized list of weights, given a
     * set of lists of weights.
     * The set of lists of weights is assumed to be non-empty.
     *
     * @param partitions a nonempty set of lists of weights
     * @return the minimum-sized list of weights from the given set
     */
    private static Collection<Weight> minimum(
            final Set<List<Weight>> partitions) {
        return partitions.stream()
                .min((l1, l2) -> (l1.size() - l2.size()))
                .get();
    }
}
