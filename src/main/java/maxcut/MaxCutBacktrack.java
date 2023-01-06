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
        if (E.size() > maxCut) {
            maxCut = E.size();
            maxCutAssignment = new int[graph.getN()];
            for (final int var : S) {
                maxCutAssignment[var] = -1;
            }

            for (final int var : S) {
                maxCutAssignment[var] = 1;
            }
        }
    }

    private void backtrack() {
        if (S.size() + T.size() == graph.getN() && isValid()) {
            updateCut();
        }

        for (final Edge edge : graph.getEdges()) {
            if (E.contains(edge)) {
                continue;
            }

            E.add(edge);
            backtrack();
            E.remove(edge);
        }
    }

    public int[] solve() {
        backtrack();
        return maxCutAssignment;
    }
}
