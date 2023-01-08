package sbuciu.sudoku;

import sbuciu.sudoku.model.Board;
import sbuciu.sudoku.model.SudokuSolution;

public abstract class Sudoku {
    protected final Board board;

    private long startTime;

    public Sudoku(Board board) {
        this.board = board;
    }

    protected abstract boolean internalSolve();

    private long elapsed() {
        return (System.nanoTime() - startTime) / 1_000_000;
    }

    public SudokuSolution solve() {
        startTime = System.nanoTime();
        boolean isSolved = internalSolve();
        System.out.printf("elapsed %d ms%n", elapsed());

        return new SudokuSolution(board.getBoard(), isSolved);
    }
}
