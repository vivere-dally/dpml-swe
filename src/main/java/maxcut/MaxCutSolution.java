package maxcut;

public class MaxCutSolution {
    private final int n;
    private final int cost;
    private final int[] partitions;

    public MaxCutSolution(int n, int cost, int[] partitions) {
        this.n = n;
        this.cost = cost;
        this.partitions = partitions;
    }

    public int getN() {
        return n;
    }

    public int getCost() {
        return cost;
    }

    public int[] getPartitions() {
        return partitions;
    }
}
