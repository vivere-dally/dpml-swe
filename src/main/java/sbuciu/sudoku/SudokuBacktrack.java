package sbuciu.sudoku;

import sbuciu.sudoku.model.Board;
import sbuciu.sudoku.model.Pos;

/**
 * Class that solves the Sudoku problem formulated as a CSP by using backtracking.
 */
public class SudokuBacktrack extends Sudoku {
    public SudokuBacktrack(Board board) {
        super(board);
    }

    private boolean backtrack(final int depth) {
        final Pos pos = board.findEmptyPos();
        if (pos.noPos) {
            // All cells are filled, a solution is found
            return true;
        }

        // We iterate all 9 values
        for (short value = 1; value <= Board.N; value += 1) {
            // Check if the value can be used for that cell
            if (board.commit(pos, value)) {
                lbt(depth, pos, board.getBoard()[pos.r]);
                if (backtrack(depth + 1)) {
                    return true;
                }

                // No solution found for the selected value, we need to reset the cell.
                board.clear(pos);
            }
        }

        return false;
    }

    @Override
    protected boolean internalSolve() {
        return backtrack(0);
    }
}
