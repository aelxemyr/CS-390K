import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Collection;
import java.util.HashSet;
import java.util.ArrayList;

/**
 * Tests for GreedyBalanceWeightSystem.
 *
 * @author Bennett Alex Myers
 * @version 11/20/2016
 */
public class GreedyBalanceWeightSystemTest {

    /** Unit Weight. */
    private static final Weight WEIGHT_1 = new Weight(1);
    /** 2-Weight. */
    private static final Weight WEIGHT_2 = new Weight(2);
    /** 3-Weight. */
    private static final Weight WEIGHT_3 = new Weight(3);
    /** 4-Weight. */
    private static final Weight WEIGHT_4 = new Weight(4);
    /** 5-Weight. */
    private static final Weight WEIGHT_5 = new Weight(5);
    /** 6-Weight. */
    private static final Weight WEIGHT_6 = new Weight(6);
    /** 7-Weight. */
    private static final Weight WEIGHT_7 = new Weight(7);
    /** 8-Weight. */
    private static final Weight WEIGHT_8 = new Weight(8);
    /** 9-Weight. */
    private static final Weight WEIGHT_9 = new Weight(9);
    /** 10-Weight. */
    private static final Weight WEIGHT_10 = new Weight(10);
    /** 11-Weight. */
    private static final Weight WEIGHT_11 = new Weight(11);
    /** 12-Weight. */
    private static final Weight WEIGHT_12 = new Weight(12);
    /** 13-Weight. */
    private static final Weight WEIGHT_13 = new Weight(13);
    /** 14-Weight. */
    private static final Weight WEIGHT_14 = new Weight(14);
    /** 15-Weight. */
    private static final Weight WEIGHT_15 = new Weight(15);

    /** Default family of weights for testing. */
    private static final WeightFamily DEFAULT_FAMILY =
            new WeightFamily(Arrays.asList(WEIGHT_1, WEIGHT_2,
                    WEIGHT_5, WEIGHT_6));

    /** Map of solutions; desired total weights map to possible solutions. */
    private static final HashMap<Weight, HashSet<ArrayList<Weight>>>
            SOLUTION_MAP;

    /** Initialization for SOLUTION_MAP. */
    static {
        SOLUTION_MAP = new HashMap<>();
        // 1 -> {{1}}
        SOLUTION_MAP.put(WEIGHT_1, new HashSet<>());
        SOLUTION_MAP.get(WEIGHT_1).add(
                new ArrayList<>(Collections.singletonList(WEIGHT_1)));
        // 2 -> {{2}}
        SOLUTION_MAP.put(WEIGHT_2, new HashSet<>());
        SOLUTION_MAP.get(WEIGHT_2).add(
                new ArrayList<>(Collections.singletonList(WEIGHT_2)));
        // 3 -> {{1, 2}}
        SOLUTION_MAP.put(WEIGHT_3, new HashSet<>());
        SOLUTION_MAP.get(WEIGHT_3).add(
                new ArrayList<>(Arrays.asList(WEIGHT_1, WEIGHT_2)));
        // 4 -> {{2, 2}}
        SOLUTION_MAP.put(WEIGHT_4, new HashSet<>());
        SOLUTION_MAP.get(WEIGHT_4).add(
                new ArrayList<>(Arrays.asList(WEIGHT_2, WEIGHT_2)));
        // 5 -> {{5}}
        SOLUTION_MAP.put(WEIGHT_5, new HashSet<>());
        SOLUTION_MAP.get(WEIGHT_5).add(
                new ArrayList<>(Collections.singletonList(WEIGHT_5)));
        // 6 -> {{6}}
        SOLUTION_MAP.put(WEIGHT_6, new HashSet<>());
        SOLUTION_MAP.get(WEIGHT_6).add(
                new ArrayList<>(Collections.singletonList(WEIGHT_6)));
        // 7 -> {{1, 6}, {2, 5}}
        SOLUTION_MAP.put(WEIGHT_7, new HashSet<>());
        SOLUTION_MAP.get(WEIGHT_7).add(
                new ArrayList<>(Arrays.asList(WEIGHT_1, WEIGHT_6)));
        SOLUTION_MAP.get(WEIGHT_7).add(
                new ArrayList<>(Arrays.asList(WEIGHT_2, WEIGHT_5)));
        // 8 -> {{2, 6}}
        SOLUTION_MAP.put(WEIGHT_8, new HashSet<>());
        SOLUTION_MAP.get(WEIGHT_8).add(
                new ArrayList<>(Arrays.asList(WEIGHT_2, WEIGHT_6)));
        // 9 -> {{1, 2, 6}, {2, 2, 5}}
        SOLUTION_MAP.put(WEIGHT_9, new HashSet<>());
        SOLUTION_MAP.get(WEIGHT_9).add(
                new ArrayList<>(Arrays.asList(WEIGHT_1, WEIGHT_2, WEIGHT_6)));
        SOLUTION_MAP.get(WEIGHT_9).add(
                new ArrayList<>(Arrays.asList(WEIGHT_2, WEIGHT_2, WEIGHT_5)));
        // 10 -> {{5, 5}}
        SOLUTION_MAP.put(WEIGHT_10, new HashSet<>());
        SOLUTION_MAP.get(WEIGHT_10).add(
                new ArrayList<>(Arrays.asList(WEIGHT_5, WEIGHT_5)));
        // 11 -> {{5, 6}}
        SOLUTION_MAP.put(WEIGHT_11, new HashSet<>());
        SOLUTION_MAP.get(WEIGHT_11).add(
                new ArrayList<>(Arrays.asList(WEIGHT_5, WEIGHT_6)));
        // 12 -> {{6, 6}}
        SOLUTION_MAP.put(WEIGHT_12, new HashSet<>());
        SOLUTION_MAP.get(WEIGHT_12).add(
                new ArrayList<>(Arrays.asList(WEIGHT_6, WEIGHT_6)));
        // 13 -> {{1, 6, 6}, {2, 5, 6}}
        SOLUTION_MAP.put(WEIGHT_13, new HashSet<>());
        SOLUTION_MAP.get(WEIGHT_13).add(
                new ArrayList<>(Arrays.asList(WEIGHT_1, WEIGHT_6, WEIGHT_6)));
        SOLUTION_MAP.get(WEIGHT_13).add(
                new ArrayList<>(Arrays.asList(WEIGHT_2, WEIGHT_5, WEIGHT_6)));
        // 14 -> {{2, 6, 6}}
        SOLUTION_MAP.put(WEIGHT_14, new HashSet<>());
        SOLUTION_MAP.get(WEIGHT_14).add(
                new ArrayList<>(Arrays.asList(WEIGHT_2, WEIGHT_6, WEIGHT_6)));
        // 15 -> {{5, 5, 5}}
        SOLUTION_MAP.put(WEIGHT_15, new HashSet<>());
        SOLUTION_MAP.get(WEIGHT_15).add(
                new ArrayList<>(Arrays.asList(WEIGHT_5, WEIGHT_5, WEIGHT_5)));
    }

    /** Balance weight system used for testing. */
    private GreedyBalanceWeightSystem bwsys;

    /**
     * Initialize test fixtures.
     */
    @Before
    public void setUp() {
        this.bwsys = new GreedyBalanceWeightSystem(DEFAULT_FAMILY);
    }

    /**
     * Verifies that constructor initializes correct family of weights.
     */
    @Test
    public void constructorTest() {
        assertEquals(DEFAULT_FAMILY.getWeights(),
                this.bwsys.getFamily().getWeights());
    }

    /**
     * Checks correctness of solution produced by the calculateSolution
     * instance method.
     */
    @Test
    public void calculateSolutionInstanceTest() {
        SOLUTION_MAP.forEach((weight, solutionSet) ->
                assertTrue(containsSolution(solutionSet,
                        bwsys.calculateSolution(weight))));
    }

    /**
     * Test of calculateSolution for which the greedy algorithm should succeed.
     */
    @Test
    public void calculateSolutionCorrectGreedyTest() {
        assertTrue(containsSolution(SOLUTION_MAP.get(WEIGHT_9),
                bwsys.calculateSolution(WEIGHT_9)));
    }

    /**
     * Test of calculateSolution for which the greedy algorithm should fail.
     */
    @Test
    public void calculateSolutionIncorrectGreedyTest() {
        assertEquals(new ArrayList<>(Arrays.asList(WEIGHT_5, WEIGHT_5)),
                bwsys.calculateSolution(WEIGHT_10));
    }

    /**
     * Checks correctness of solution produced by the calculateSolution
     * static method.
     */
    @Test
    public void calculateSolutionStaticTest() {
        SOLUTION_MAP.forEach((weight, solutionSet) ->
                assertTrue(containsSolution(solutionSet,
                        GreedyBalanceWeightSystem.calculateSolution(
                                DEFAULT_FAMILY,
                                weight))));
    }

    /**
     * Support method for tests. Verifies that a given solution set contains a
     * particular solution.
     */
    public static boolean containsSolution(
            final HashSet<ArrayList<Weight>> solutionSet,
            final Collection<Weight> solution) {
        ArrayList<Weight> solutionList = new ArrayList<>(solution);
        solutionList.sort(null);
        return solutionSet.contains(solutionList);
    }
}
