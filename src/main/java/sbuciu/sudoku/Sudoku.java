package sbuciu.sudoku;

import sbuciu.sudoku.model.SudokuSolution;

public abstract class Sudoku {

    private long startTime;

    protected abstract void internalSolve();

    private long elapsed() {
        return (System.nanoTime() - startTime) / 1_000_000;
    }

    public SudokuSolution solve() {
        startTime = System.nanoTime();
        internalSolve();
        System.out.printf("elapsed %d ms%n", elapsed());

        return new SudokuSolution();
    }
}
