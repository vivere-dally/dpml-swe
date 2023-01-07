package maxcut;

public abstract class MaxCut {
    /**
     * Represents the given graph for which we need to find the max cut.
     */
    protected final Graph graph;

    /**
     * Represents a solution to the max cut problem represented as a variable assignment array where a value of:
     * - -1 means that the vertex at that index is part of set S
     * - 1 means that the vertex at that index is part of set T
     * There can be a multiple cuts, however this variable will keep the assignment of a maximum cut.
     */
    protected int[] maxCutAssignment = new int[0];

    /**
     * Keeps track of the count of edges that are in the maximum cut.
     */
    protected int maxCut = 0;

    protected MaxCut(Graph graph) {
        this.graph = graph;
    }

    protected abstract void internalSolve();

    public MaxCutSolution solve() {
        internalSolve();

        return new MaxCutSolution(graph, maxCut, maxCutAssignment);
    }
}
