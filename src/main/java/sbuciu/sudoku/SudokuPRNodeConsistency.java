package sbuciu.sudoku;

import sbuciu.sudoku.model.Board;
import sbuciu.sudoku.model.Pos;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static sbuciu.sudoku.model.Board.*;

public class SudokuPRNodeConsistency extends Sudoku {

    private final List<List<Set<Short>>> constraints = new ArrayList<>();

    public SudokuPRNodeConsistency(Board board) {
        super(board);
        initConstraints();
    }

    private void initConstraints() {
        final Set<Short> values = new HashSet<>();
        for (short val = 1; val <= N; val += 1) {
            values.add(val);
        }

        for (int r = 0; r < N; r += 1) {
            final List<Set<Short>> row = new ArrayList<>(N);
            for (int c = 0; c < N; c += 1) {
                row.add(new HashSet<>(values));
            }
            constraints.add(row);
        }

        for (int r = 0; r < N; r += 1) {
            for (int c = 0; c < N; c += 1) {
                final short value = board.getBoard()[r][c];
                if (value == EMPTY) {
                    continue;
                }

                for (int i = 0; i < N; i += 1) {
                    constraints.get(r).get(i).remove(value);
                    constraints.get(i).get(c).remove(value);
                }

                final int sqr = r / M, sqc = c / M;
                for (int i = sqr * M; i < (sqr + 1) * M; i += 1) {
                    for (int j = sqc * M; j < (sqc + 1) * M; j += 1) {
                        constraints.get(i).get(j).remove(value);
                    }
                }
            }
        }
    }

    private boolean backtrack(final int depth) {
        final Pos pos = board.findEmptyPos();
        if (pos.noPos) {
            // All cells are filled, a solution is found
            return true;
        }

        for (final short value : constraints.get(pos.r).get(pos.c)) {
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
