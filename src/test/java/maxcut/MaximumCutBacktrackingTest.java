package maxcut;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MaximumCutBacktrackingTest {


    @Test
    void solve() {
        final int[] expected = new int[]{0, 0, 0, 1, 1, 1};
        final int n = 6;
        final boolean[][] adj = new boolean[][]{
                {false, true, true, false, false, false},
                {true, false, true, true, false, false},
                {true, true, false, true, true, false},
                {false, true, true, false, true, true},
                {false, false, true, true, false, true},
                {false, false, false, true, true, false},
        };

//        final MaximumCutBacktracking solver = new MaximumCutBacktracking(n, adj);
        final MaximumCutChoco solver = new MaximumCutChoco(n, adj);

        final int[] actual = solver.solve();

        assertNotNull(actual);
        assertEquals(expected, actual);
    }
}