package sbuciu.sudoku.model;

public class SudokuSolution {
    public final short[][] board;
    public final boolean isSolved;

    public SudokuSolution(short[][] board, boolean isSolved) {
        this.board = board;
        this.isSolved = isSolved;
    }
}
