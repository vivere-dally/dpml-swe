package sbuciu.sudoku;

import sbuciu.sudoku.model.Board;
import sbuciu.sudoku.model.Pos;
import sbuciu.sudoku.model.SudokuSolution;

import java.util.Arrays;

public abstract class Sudoku {
    /**
     * Represents the given Sudoku board that we need to solve.
     */
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

    protected void l(final String s) {
        System.out.println("elapsed " + elapsed() + "ms: " + s);
    }

    protected void lbt(final int depth, final Pos pos, final short[] row) {
        final StringBuilder bob = new StringBuilder();
        for (int i = 0; i < depth; i += 1) {
            bob.append("  ");
        }

        bob.append("depth ").append(depth + 1);
        bob.append(" ").append(pos);
        bob.append(" ").append(Arrays.toString(row));

        l(bob.toString());
    }
}
