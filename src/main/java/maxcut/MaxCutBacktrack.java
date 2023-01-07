package maxcut;

import java.util.HashSet;
import java.util.Set;


/**
 * Class that solves the MaxCut problem formulated as a CSP by using backtracking.
 */
public class MaxCutBacktrack {
    /**
     * Represents the given graph for which we need to find the max cut.
     */
    private final Graph graph;

    /**
     * Represents one set of a partition
     */
    private final Set<Integer> S = new HashSet<>();
    /**
     * Represents the other set of a partition
     */
    private final Set<Integer> T = new HashSet<>();
    /**
     * Set that keeps track of already chosen edges
     */
    private final Set<Edge> E = new HashSet<>();

    /**
     * Represents a solution to the max cut problem represented as a variable assignment array where a value of:
     * - -1 means that the vertex at that index is part of set S
     * - 1 means that the vertex at that index is part of set T
     * <p>
     * There can be a multiple cuts, however this variable will keep the assignment of a maximum cut.
     */
    private int[] maxCutAssignment;
    /**
     * Keeps track of the count of edges that are in the maximum cut.
     */
    private int maxCut = 0;

    public MaxCutBacktrack(Graph graph) {
        this.graph = graph;
    }

    /**
     * Checks if a value assignment is valid by verifying that no vertex is present in both sets.
     *
     * @return true if intersection is an empty set, false otherwise.
     */
    private boolean isValid() {
        final Set<Integer> intersection = new HashSet<>(S);
        intersection.retainAll(T);

        return intersection.isEmpty();
    }

    /**
     * When we have a solution, we check if the cut is better than the maximum cut we keep track of. If it is, then we
     * update the cut.
     */
    private void updateCut() {
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
        }
    }

    private void backtrack(int index) {
        // We reached the end of the edges.
        if (index >= graph.getEdges().size()) {
            return;
        }

        // We have a solution if all vertices are in a set and if the configuration is valid
        if (S.size() + T.size() == graph.getN() && isValid()) {
            updateCut();
        }

        // We iterate all edges starting from the given index
        for (int i = index; i < graph.getEdges().size(); i += 1) {
            final Edge edge = graph.getEdges().get(i);

            // No reason to consider this edge again.
            if (E.contains(edge)) {
                continue;
            }

            E.add(edge);

            // Update the sets and make a recursive call.
            S.add(edge.u());
            T.add(edge.v());
            backtrack(i);
            T.remove(edge.v());
            S.remove(edge.u());

            // Due to the fact that the graph is undirected, we can build a solution using the other direction.
            S.add(edge.v());
            T.add(edge.u());
            backtrack(i);
            T.remove(edge.u());
            S.remove(edge.v());

            E.remove(edge);
        }
    }

    public MaxCutSolution solve() {
        backtrack(0);
        return new MaxCutSolution(graph.getN(), maxCut, maxCutAssignment);
    }
}
