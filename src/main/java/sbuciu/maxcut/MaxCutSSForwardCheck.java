package sbuciu.maxcut;

import sbuciu.maxcut.model.Edge;
import sbuciu.maxcut.model.Graph;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * MaxCut Simple Search
 */
public class MaxCutSSForwardCheck extends MaxCut {

    /**
     * Represents one set of a partition
     */
    private final Set<Integer> S = new HashSet<>();
    /**
     * Represents the other set of a partition
     */
    private final Set<Integer> T = new HashSet<>();

    public MaxCutSSForwardCheck(Graph graph) {
        super(graph);
    }

    /**
     * This function filters out any edge that would not produce a valid solution given the selected edges.
     *
     * @param edges - list of edges to filter
     * @param e     - the latest selected edge
     * @return a list with the filtered edges
     */
    private List<Edge> commit(final List<Edge> edges, final Edge e) {
        final List<Edge> validEdges = new ArrayList<>();
        for (final Edge edge : edges) {
            if (edge.equals(e)) {
                continue;
            }

            if (!S.contains(edge.v()) && !T.contains(edge.u())) {
                validEdges.add(edge);
            }
        }

        return validEdges;
    }

    private void backtrack(final int depth, List<Edge> edges) {
        if (isValid(S, T)) {
            updateCut(S, T);
        }

        lbt(depth, S, T);
        lcg(edges);
        for (final Edge edge : edges) {
            S.add(edge.u());
            T.add(edge.v());
            backtrack(depth + 1, commit(edges, edge));
            T.remove(edge.v());
            S.remove(edge.u());

            final Edge reversed = edge.reversed();
            S.add(reversed.u());
            T.add(reversed.v());
            backtrack(depth + 1, commit(edges, edge));
            T.remove(reversed.v());
            S.remove(reversed.u());
        }
    }

    @Override
    protected void internalSolve() {
        backtrack(0, graph.getEdges());
    }
}
