package maxcut;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MaxCutBacktrack2Test {

    @Test
    void solve() {
        final int n = 6;
        final boolean[][] adj = new boolean[][]{
                {false, false, true, true, false, false},
                {false, false, true, true, false, false},
                {true, true, false, false, true, true},
                {true, true, false, false, true, true},
                {false, false, true, true, false, false},
                {false, false, true, true, false, false}
        };

        final MaxCutBacktrack2 maxCut = new MaxCutBacktrack2(n, adj);
        final MaxCutSolution sol = maxCut.solve();

        assertEquals(8, sol.getCost());
    }
}