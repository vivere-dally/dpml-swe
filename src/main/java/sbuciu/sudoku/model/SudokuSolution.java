package sbuciu.sudoku.model;

public class SudokuSolution {
    public final short[][] board;
    public final boolean isSolved;

    public SudokuSolution(short[][] board, boolean isSolved) {
        this.board = board;
        this.isSolved = isSolved;
    }

    public String encode() {
        final StringBuilder bob = new StringBuilder();
        for (short[] shorts : board) {
            for (int j = 0; j < board[0].length; j += 1) {
                bob.append(shorts[j]);
                if (j < board[0].length - 1) {
                    bob.append(',');
                }
            }

            bob.append(System.lineSeparator());
        }

        return bob.toString();
    }
}
