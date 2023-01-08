package sbuciu.maxcut;

import sbuciu.maxcut.model.Edge;
import sbuciu.maxcut.model.Graph;

import java.util.HashSet;
import java.util.Set;

/**
 * MaxCut Problem Reduction
 */
public class MaxCutPRPruning extends MaxCut {

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

    public MaxCutPRPruning(Graph graph) {
        super(graph);
    }

    /**
     * Checks if we can consider the edge for the solution
     *
     * @param edge - the edge considered for pruning
     * @return - true if the edge violates the existing constraints, false otherwise
     */
    private boolean canPrune(final Edge edge) {
        return S.contains(edge.v()) || T.contains(edge.u());
    }

    private void backtrack(final int depth, final int index) {
        // We reached the end of the edges.
        if (index >= graph.getEdges().size()) {
            return;
        }

        // We have a solution if all vertices are in a set and if the configuration is valid
        if (isValid(S, T)) {
            updateCut(S, T);
        }

        lbt(depth, S, T);
        // We iterate all edges starting from the given index
        for (int i = index; i < graph.getEdges().size(); i += 1) {
            final Edge edge = graph.getEdges().get(i);

            if (E.contains(edge)) {
                // No reason to consider this edge again.
                continue;
            }

            E.add(edge);

            if (!canPrune(edge)) {
                // Update the sets and make a recursive call.
                S.add(edge.u());
                T.add(edge.v());
                backtrack(depth + 1, i);
                T.remove(edge.v());
                S.remove(edge.u());
            }

            if (!canPrune(edge.reversed())) {
                // Due to the fact that the graph is undirected, we can build a solution using the other direction.
                S.add(edge.v());
                T.add(edge.u());
                backtrack(depth + 1, i);
                T.remove(edge.u());
                S.remove(edge.v());
            }

            E.remove(edge);
        }
    }

    @Override
    protected void internalSolve() {
        backtrack(0, 0);
    }
}
