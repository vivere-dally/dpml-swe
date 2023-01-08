package sbuciu.maxcut;

import sbuciu.maxcut.model.Graph;
import sbuciu.maxcut.model.MaxCutSolution;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public abstract class MaxCut {
    /**
     * Represents the given graph for which we need to find the max cut.
     */
    protected final Graph graph;

    /**
     * Represents a solution to the max cut problem represented as a variable assignment array where a value of:
     * - -1 means that the vertex at that index is part of set S
     * - 1 means that the vertex at that index is part of set T
     * There can be a multiple cuts, however this variable will keep the assignment of a maximum cut.
     */
    protected int[] maxCutAssignment = new int[0];

    /**
     * Keeps track of the count of edges that are in the maximum cut.
     */
    protected int maxCut = 0;

    private long startTime;

    public MaxCut(Graph graph) {
        this.graph = graph;
    }

    /**
     * Checks if a value assignment is valid by verifying that no vertex is present in both sets.
     *
     * @return true if intersection is an empty set, false otherwise.
     */
    protected boolean isValid(final Set<Integer> S, final Set<Integer> T) {
        if (S.size() + T.size() != graph.getN()) {
            return false;
        }

        final Set<Integer> intersection = new HashSet<>(S);
        intersection.retainAll(T);

        return intersection.isEmpty();
    }

    /**
     * When we have a solution, we check if the cut is better than the maximum cut we keep track of. If it is, then we
     * update the cut.
     */
    protected void updateCut(final Set<Integer> S, final Set<Integer> T) {
        int cut = 0;
        for (final int u : S) {
            for (final int v : T) {
                // Count the number of edges that are cut.
                if (graph.areConnected(u, v)) {
                    cut += 1;
                }
            }
        }

        // If this is a better cut, we update the global parameters.
        if (cut > maxCut) {
            maxCut = cut;
            maxCutAssignment = new int[graph.getN()];
            for (final int var : S) {
                maxCutAssignment[var] = -1;
            }

            for (final int var : T) {
                maxCutAssignment[var] = 1;
            }

            l(String.format("MaxCut cost=%s partition=%s", maxCut, Arrays.toString(maxCutAssignment)));
        }
    }

    protected abstract void internalSolve();

    protected void lbt(final int depth, final Set<Integer> S, final Set<Integer> T) {
        final StringBuilder bob = new StringBuilder();
        for (int i = 0; i < depth; i += 1) {
            bob.append("  ");
        }

        bob.append(depth + 1);
        bob.append(" S={");
        int i = 0;
        for (final int v : S) {
            bob.append(v);
            if (i < S.size() - 1) {
                bob.append(",");
            }

            i += 1;
        }

        bob.append("} T={");
        i = 0;
        for (final int v : T) {
            bob.append(v);
            if (i < T.size() - 1) {
                bob.append(",");
            }

            i += 1;
        }

        l(bob.append("}").toString());
    }

    protected void l(final String s) {
        final long elapsed = (System.nanoTime() - startTime) / 1_000_000;
        System.out.println("elapsed " + elapsed + "ms: " + s);
    }

    public MaxCutSolution solve() {
        startTime = System.nanoTime();
        internalSolve();
        final long elapsed = (System.nanoTime() - startTime) / 1_000_000;
        System.out.printf("elapsed %d ms%n", elapsed);

        return new MaxCutSolution(graph, maxCut, maxCutAssignment);
    }
}
