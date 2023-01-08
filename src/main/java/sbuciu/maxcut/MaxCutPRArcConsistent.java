package sbuciu.maxcut;

import sbuciu.maxcut.model.Edge;
import sbuciu.maxcut.model.Graph;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * MaxCut Problem Reduction
 */
public class MaxCutPRArcConsistent extends MaxCut {

    /**
     * Set that keeps track of already chosen edges
     */
    private final Set<Edge> E = new HashSet<>();

    public MaxCutPRArcConsistent(Graph graph) {
        super(graph);
    }

    static class QueueItem {
        public final Edge edge;
        public final boolean isReversed;

        QueueItem(Edge edge, boolean isReversed) {
            this.edge = edge;
            this.isReversed = isReversed;
        }
    }

    private void arcConsistency(final int depth) {
        // E contains the edges that we are currently consider for the solution. It represents the arcs.
        final Queue<QueueItem> agenda = new LinkedList<>();
        for (final Edge e : E) {
            agenda.add(new QueueItem(e, false));
            agenda.add(new QueueItem(e.reversed(), true)); // Add also the reversed arc
        }

        final Set<Integer> S = new HashSet<>(), T = new HashSet<>();
        for (int i = 0; i < this.graph.getN(); i += 1) {
            // Vertex i can be in either set
            S.add(i);
            T.add(i);
        }

        while (!agenda.isEmpty()) {
            final QueueItem item = agenda.remove();
            if (item.isReversed) {
                if (T.contains(item.edge.u())) {
                    S.remove(item.edge.u());

                    for (int i = 0; i < this.graph.getN(); i += 1) {
                        if (this.graph.areConnected(i, item.edge.u())) {
                            // The domain changed, so we add all arcs where u is on the right side.
                            agenda.add(new QueueItem(new Edge(i, item.edge.u()), false));
                        }
                    }
                }

                continue;
            }

            if (S.contains(item.edge.v())) {
                T.remove(item.edge.v());

                for (int i = 0; i < this.graph.getN(); i += 1) {
                    if (this.graph.areConnected(i, item.edge.v())) {
                        // The domain changed, so we add all arcs where v is on the right side.
                        agenda.add(new QueueItem(new Edge(i, item.edge.v()), true));
                    }
                }
            }
        }

        if (isValid(S, T)) {
            updateCut(S, T);
        }

        lbt(depth, S, T);
    }

    private void backtrack(final int depth, final int index) {
        // We reached the end of the edges.
        if (index >= graph.getEdges().size()) {
            return;
        }

        // We iterate all edges starting from the given index
        for (int i = index; i < graph.getEdges().size(); i += 1) {
            final Edge edge = graph.getEdges().get(i);

            if (E.contains(edge)) {
                // No reason to consider this edge again.
                continue;
            }

            E.add(edge);
            lcg(E);

            arcConsistency(depth);
            backtrack(depth + 1, i);
            E.remove(edge);
        }
    }

    @Override
    protected void internalSolve() {
        backtrack(0, 0);
    }
}
