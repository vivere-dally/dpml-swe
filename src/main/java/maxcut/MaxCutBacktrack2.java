package maxcut;

import java.util.HashSet;
import java.util.Set;

public class MaxCutBacktrack2 {
    private final int n;
    private final boolean[][] adjacencyMatrix;

    private final Set<Integer> S = new HashSet<>();
    private final Set<Integer> T = new HashSet<>();
    private final Set<Edge> E = new HashSet<>();

    private int[] maxCutAssignment;
    private int maxCut = 0;

    public MaxCutBacktrack2(int n, boolean[][] adjacencyMatrix) {
        this.n = n;
        this.adjacencyMatrix = adjacencyMatrix;
    }

    private boolean isValid() {
        final Set<Integer> intersection = new HashSet<>(S);
        intersection.retainAll(T);

        return intersection.isEmpty();
    }

    private void updateCut() {
        if (E.size() / 2 > maxCut) {
            maxCut = E.size() / 2;
            maxCutAssignment = new int[n];
            for (final int var : S) {
                maxCutAssignment[var] = -1;
            }

            for (final int var : T) {
                maxCutAssignment[var] = 1;
            }
        }
    }

    private void backtrack() {
        if (S.size() + T.size() == n && isValid()) {
            updateCut();
        }

        for (int i = 0; i < n; i += 1) {
            for (int j = i + 1; j < n; j += 1) {
                if (!adjacencyMatrix[i][j]) {
                    continue;
                }

                final Edge e1 = new Edge(i, j), e2 = new Edge(j, i);
                if (E.contains(e1) || E.contains(e2)) {
                    continue;
                }

                E.add(e1);
                E.add(e2);
                S.add(i);
                T.add(j);
                backtrack();
                T.remove(j);
                S.remove(i);
                E.remove(e2);
                E.remove(e1);
            }
        }
    }

    public MaxCutSolution solve() {
        backtrack();
        return new MaxCutSolution(n, maxCut, maxCutAssignment);
    }
}
