package maxcut;

import java.util.Objects;

public class Edge {
    private final int u;
    private final int v;
    private final int w;

    public Edge(int u, int v, int w) {
        this.u = u;
        this.v = v;
        this.w = w;
    }

    public Edge(int u, int v) {
        this.u = u;
        this.v = v;
        this.w = 1;
    }

    public int u() {
        return u;
    }

    public int v() {
        return v;
    }

    public int w() {
        return w;
    }

    @Override
    public String toString() {
        return "Edge{" +
                "u=" + u +
                ", v=" + v +
                ", w=" + w +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Edge edge = (Edge) o;
        return u == edge.u && v == edge.v && w == edge.w;
    }

    @Override
    public int hashCode() {
        return Objects.hash(u, v, w);
    }
}
