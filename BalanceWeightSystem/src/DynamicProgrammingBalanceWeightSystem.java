import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

/**
 * An implementation of BalanceWeightSystem utilizing a dynamic programming
 * algorithm.
 *
 * @author Bennett Alex Myers
 * @version 11/28/2016
 */
public class DynamicProgrammingBalanceWeightSystem
        extends BalanceWeightSystem {

    /** Mapping of previously calculated solutions for this instance. */
    private HashMap<Integer, Collection<Weight>> solutionMemo;

    /**
     * Given a family of weights, create a new balance weight system based on
     * that family which implements a dynamic programming algorithm.
     *
     * @param family the family of weights with which to calculate a solution
     */
    public DynamicProgrammingBalanceWeightSystem(final WeightFamily family) {
        super(family);
        this.solutionMemo = new HashMap<>();
        this.solutionMemo.put(0, new ArrayList<Weight>());
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
        int goal = desiredTotalWeight.getValue();
        if (!this.solutionMemo.containsKey(goal)) {
            memoize(goal);
        }
        return this.solutionMemo.get(goal);
    }

    /**
     * Fill out the mapping of solutions for this BalanceWeightSystem.
     *
     * @param goal the weight value of the desired weight whose solution
     *             to calculate
     */
    private void memoize(final int goal) {
        for (int i = 1; i <= goal; i++) {
            HashSet<List<Weight>> partitions = new HashSet<>();
            for (Weight w : getFamily()) {
                int current = w.getValue();
                ArrayList<Weight> solution = new ArrayList<>();
                if (current <= i) {
                    solution.add(w);
                    if (current < i) {
                        solution.addAll(calculateSolution(
                                new Weight(i - current)));
                        solution.sort(null);
                    }
                    partitions.add(solution);
                }
            }
            this.solutionMemo.put(i, minimum(partitions));
        }
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
        DynamicProgrammingBalanceWeightSystem dpbws =
                new DynamicProgrammingBalanceWeightSystem(family);
        return dpbws.calculateSolution(desiredTotalWeight);
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
