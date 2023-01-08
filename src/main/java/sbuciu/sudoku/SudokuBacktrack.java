package sbuciu.sudoku;

import sbuciu.sudoku.model.Board;
import sbuciu.sudoku.model.Pos;

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

        for (short value = 1; value <= Board.N; value += 1) {
            if (board.commit(pos, value)) {
                lbt(depth, pos, board.getBoard()[pos.r]);
                if (backtrack(depth + 1)) {
                    return true;
                }

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
