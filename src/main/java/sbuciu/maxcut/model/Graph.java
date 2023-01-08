package sbuciu.maxcut.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Graph {
    private final int n;
    private final boolean[][] adjacencyMatrix;
    private final List<Edge> edges = new ArrayList<>();

    public Graph(final int n) {
        this.n = n;
        adjacencyMatrix = new boolean[n][n];
    }

    public int getN() {
        return n;
    }

    public void addEdge(final int u, final int v, final int w) {
        edges.add(new Edge(u, v, w));
    }

    public void addEdge(final int u, final int v) {
        edges.add(new Edge(u, v));
        adjacencyMatrix[u][v] = adjacencyMatrix[v][u] = true;
    }

    public void addEdge(Edge edge) {
        edges.add(edge);
    }

    public List<Edge> getEdges() {
        return edges;
    }

    public boolean areConnected(final int u, final int v) {
        return adjacencyMatrix[u][v] || adjacencyMatrix[v][u];
    }

    @Override
    public String toString() {
        final StringBuilder bob = new StringBuilder();
        bob.append("Graph{n=").append(n).append(", edges=[").append(System.lineSeparator());

        for (final Edge edge : edges) {
            bob.append(edge.toString()).append(System.lineSeparator());
        }

        return bob.append("]}").toString();
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Graph graph = (Graph) o;
        return n == graph.n && edges.equals(graph.edges);
    }

    @Override
    public int hashCode() {
        return Objects.hash(n, edges);
    }
}
