package sbuciu.maxcut.model;

public class MaxCutSolution {
    private final Graph graph;
    private final int cost;
    private final int[] partitions;

    public MaxCutSolution(Graph graph, int cost, int[] partitions) {
        this.graph = graph;
        this.cost = cost;
        this.partitions = partitions;
    }

    public Graph getGraph() {
        return graph;
    }

    public int getCost() {
        return cost;
    }

    public int[] getPartitions() {
        return partitions;
    }
}
