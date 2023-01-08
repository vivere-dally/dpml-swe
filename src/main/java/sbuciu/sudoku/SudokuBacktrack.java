package sbuciu.sudoku;

import sbuciu.sudoku.model.Board;
import sbuciu.sudoku.model.Pos;

public class SudokuBacktrack extends Sudoku {
    public SudokuBacktrack(Board board) {
        super(board);
    }

    @Override
    protected boolean internalSolve() {
        final Pos pos = board.findEmptyPos();
        if (pos.noPos) {
            // All cells are filled, a solution is found
            return true;
        }

        for (short value = 1; value <= Board.N; value += 1) {
            if (board.commit(pos, value)) {
                if (internalSolve()) {
                    return true;
                }

                board.clear(pos);
            }
        }

        return false;
    }
}
