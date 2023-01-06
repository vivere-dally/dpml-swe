package maxcut;

import java.util.Arrays;

public class MaximumCutBacktracking {
    private static final int[] PARTITIONS = new int[]{0, 1};

    private final int n;
    private final boolean[][] adj;

    private final int[] vars;

    private int[] maxVarAssignment;
    private int maxCut = 0;

    public MaximumCutBacktracking(int n, boolean[][] adj) {
        this.n = n;
        this.adj = adj;

        vars = new int[n];
    }

    private boolean isValid() {
        for (int i = 0; i < n; i += 1) {
            for (int j = i + 1; j < n; j += 1) {
                if (adj[i][j] && vars[i] == vars[j]) {
                    return false;
                }
            }
        }

        return true;
    }

    private void computeCut() {
        int cut = 0;
        for (int i = 0; i < n; i += 1) {
            for (int j = i + 1; j < n; j += 1) {
                if (adj[i][j] && vars[i] != vars[j]) {
                    cut += 1;
                }
            }
        }

        if (cut > maxCut) {
            maxVarAssignment = Arrays.copyOf(vars, n);
            maxCut = cut;
        }
    }

    private void backtrack(final int varIndex) {
        if (varIndex == n) {
            if (isValid()) {
                computeCut();
            }

            return;
        }

        for (final int partition : PARTITIONS) {
            vars[varIndex] = partition;
            backtrack(varIndex + 1);
        }
    }

    public int[] solve() {
        backtrack(0);
        return maxVarAssignment;
    }
}
