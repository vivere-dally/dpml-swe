package maxcut;

import org.chocosolver.solver.Model;
import org.chocosolver.solver.variables.IntVar;

public class MaximumCutChoco {
    // The number of vertices in the graph
    int n;
    // The weights of the edges in the graph
    boolean[][] weights;

    // The Choco model
    Model model;
    // The Choco variables representing the vertices
    IntVar[] variables;
    // The Choco variable representing the cut size
    IntVar cutSize;

    public MaximumCutChoco(int n, boolean[][] weights) {
        this.n = n;
        this.weights = weights;

        // Create the Choco model
        model = new Model();

        // Create the Choco variables
        variables = model.intVarArray("X", n, 0, 1);
        cutSize = model.intVar("cutSize", 0, n * n, true);

        // Add constraints to the model
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (weights[i][j]) {
                    // If there is an edge between vertices i and j,
                    // they should not be in the same partition
                    model.arithm(variables[i], "!=", variables[j]).post();
                }
            }
        }

        // Add a constraint to maximize the cut size
//        model.max(cutSize, variables).post();
        model.setObjective(Model.MAXIMIZE, cutSize);
    }

    public int[] solve() {
        // Solve the CSP
        if (!model.getSolver().solve()) {
            return null;
        }

        // Extract the solution from the model
        int[] solution = new int[n];
        for (int i = 0; i < n; i++) {
            solution[i] = variables[i].getValue();
        }

        return solution;
    }
}
