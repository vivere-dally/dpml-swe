package maxcut;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Graph {
    private final int n;
    private final List<Edge> edges = new ArrayList<>();

    public Graph(int n) {
        this.n = n;
    }

    public int getN() {
        return n;
    }

    public void addEdge(int u, int v, int w) {
        edges.add(new Edge(u, v, w));
    }

    public void addUndirectedEdge(int u, int v, int w) {
        edges.add(new Edge(u, v, w));
        edges.add(new Edge(v, u, w));
    }

    public void addEdge(int u, int v) {
        edges.add(new Edge(u, v));
    }

    public void addUndirectedEdge(int u, int v) {
        edges.add(new Edge(u, v));
        edges.add(new Edge(v, u));
    }

    public void addEdge(Edge edge) {
        edges.add(edge);
    }

    public List<Edge> getEdges() {
        return edges;
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
    public boolean equals(Object o) {
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
