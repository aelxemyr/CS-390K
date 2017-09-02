import java.util.Collection;
import java.util.ArrayList;
import java.util.TreeSet;

/**
 * An implementation of BalanceWeightSystem utilizing a greedy algorithm.
 *
 * @author Bennett Alex Myers
 * @version 11/18/2016
 */
public class GreedyBalanceWeightSystem
        extends BalanceWeightSystem {

    /**
     * Given a family of weights, create a new balance weight system based on
     * that family which implements a greedy algorithm.
     *
     * @param family the family of weights with which to calculate a solution
     */
    public GreedyBalanceWeightSystem(final WeightFamily family) {
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
        ArrayList<Weight> solution = new ArrayList<>();
        int total = desiredTotalWeight.getValue();
        TreeSet<Weight> weights = new TreeSet<>(family.getWeights());
        Weight current = weights.pollLast();
        while (total > 0) {
            if (current.getValue() > total) {
                current = weights.pollLast();
            } else {
                solution.add(current);
                total -= current.getValue();
            }
        }
        solution.sort(null);
        return solution;
    }
}
