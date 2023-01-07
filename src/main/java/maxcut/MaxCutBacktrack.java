package maxcut;

import java.util.HashSet;
import java.util.Set;

public class MaxCutBacktrack {
    private final Graph graph;

    private final Set<Integer> S = new HashSet<>();
    private final Set<Integer> T = new HashSet<>();
    private final Set<Edge> E = new HashSet<>();

    private int[] maxCutAssignment;
    private int maxCut = 0;

    public MaxCutBacktrack(Graph graph) {
        this.graph = graph;
    }

    private boolean isValid() {
        final Set<Integer> intersection = new HashSet<>(S);
        intersection.retainAll(T);

        return intersection.isEmpty();
    }

    private void updateCut() {
        int cut = 0;
        for (final int u : S) {
            for (final int v : T) {
                if (graph.areConnected(u, v)) {
                    cut += 1;
                }
            }
        }

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

    private void backtrack() {
        if (S.size() + T.size() == graph.getN() && isValid()) {
            updateCut();
        }

        for (final Edge edge : graph.getEdges()) {
            if (E.contains(edge) ||
                    (S.contains(edge.u()) && S.contains(edge.v())) ||
                    (T.contains(edge.u()) && T.contains(edge.v()))) {
                continue;
            }

            E.add(edge);

            S.add(edge.u());
            T.add(edge.v());
            backtrack();
            T.remove(edge.v());
            S.remove(edge.u());

            // Due to the fact that the graph is undirected, we can build a solution using the other direction.
            S.add(edge.v());
            T.add(edge.u());
            backtrack();
            T.remove(edge.u());
            S.remove(edge.v());

            E.remove(edge);
        }
    }

    public MaxCutSolution solve() {
        backtrack();
        return new MaxCutSolution(graph.getN(), maxCut, maxCutAssignment);
    }
}
