package maxcut;

import java.util.HashSet;
import java.util.Set;


/**
 * Class that solves the MaxCut problem formulated as a CSP by using backtracking.
 */
public class MaxCutBacktrack extends MaxCut {

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

    public MaxCutBacktrack(Graph graph) {
        super(graph);
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

            if (E.contains(edge)) {
                // No reason to consider this edge again.
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

    @Override
    protected void internalSolve() {
        backtrack(0);
    }
}
