package maxcut;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MaxCutBacktrackTest {


    @Test
    void solve1() {
        final Graph graph = new Graph(6);
        graph.addEdge(0, 2);
        graph.addEdge(0, 3);
        graph.addEdge(1, 2);
        graph.addEdge(1, 3);
        graph.addEdge(2, 4);
        graph.addEdge(2, 5);
        graph.addEdge(3, 4);
        graph.addEdge(3, 5);
        final MaxCutBacktrack maxCut = new MaxCutBacktrack(graph);


        final MaxCutSolution sol = maxCut.solve();

        assertNotNull(sol.getPartitions());
        assertEquals(8, sol.getCost());
    }

    @Test
    void solve2() {
        final Graph graph = new Graph(6);
        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(0, 3);
        graph.addEdge(1, 3);
        graph.addEdge(2, 3);
        graph.addEdge(2, 4);
        graph.addEdge(3, 4);
        graph.addEdge(3, 5);
        graph.addEdge(4, 5);
        final MaxCutBacktrack maxCut = new MaxCutBacktrack(graph);


        final MaxCutSolution sol = maxCut.solve();

        assertNotNull(sol.getPartitions());
        assertEquals(6, sol.getCost());
    }
}