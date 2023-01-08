package sbuciu.maxcut;

import sbuciu.maxcut.model.Graph;
import sbuciu.maxcut.model.MaxCutSolution;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MaxCutSSForwardCheckTest {

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

        final MaxCutSolution sol = new MaxCutSSForwardCheck(graph).solve();

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

        final MaxCutSolution sol = new MaxCutSSForwardCheck(graph).solve();

        assertNotNull(sol.getPartitions());
        assertEquals(7, sol.getCost());
    }

    @Test
    void solve3() {
        final Graph graph = new Graph(4);
        graph.addEdge(0, 1);
        graph.addEdge(1, 2);
        graph.addEdge(1, 3);

        final MaxCutSolution sol = new MaxCutSSForwardCheck(graph).solve();

        assertEquals(3, sol.getCost());
    }

    @Test
    void solve4() {
        final Graph graph = new Graph(4);
        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(1, 2);
        graph.addEdge(2, 3);

        final MaxCutSolution sol = new MaxCutSSForwardCheck(graph).solve();

        assertEquals(3, sol.getCost());
    }
}