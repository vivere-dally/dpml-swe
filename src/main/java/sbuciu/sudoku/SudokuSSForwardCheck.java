package sbuciu.sudoku;

import sbuciu.sudoku.model.Board;
import sbuciu.sudoku.model.Pos;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static sbuciu.sudoku.model.Board.*;
import static sbuciu.sudoku.model.Board.M;

public class SudokuSSForwardCheck extends Sudoku {

    public SudokuSSForwardCheck(Board board) {
        super(board);
        initConstraints();
    }

    private void commit(final List<List<Set<Short>>> constraints, final int r, final int c, final short value) {
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

    private List<List<Set<Short>>> initConstraints() {
        final List<List<Set<Short>>> constraints = new ArrayList<>(N);

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

                commit(constraints, r, c, value);
            }
        }

        return constraints;
    }

    private List<List<Set<Short>>> clone(final List<List<Set<Short>>> constraints) {
        final List<List<Set<Short>>> cc = new ArrayList<>(N);
        for (final List<Set<Short>> row : constraints) {
            final List<Set<Short>> rowClone = new ArrayList<>(N);
            for (final Set<Short> s : row) {
                rowClone.add(new HashSet<>(s));
            }

            cc.add(rowClone);
        }

        return cc;
    }

    private boolean backtrack(final int depth, final List<List<Set<Short>>> constraints) {
        final Pos pos = board.findEmptyPos();
        if (pos.noPos) {
            // All cells are filled, a solution is found
            return true;
        }

        for (final short value : constraints.get(pos.r).get(pos.c)) {
            if (board.commit(pos, value)) {
                lbt(depth, pos, board.getBoard()[pos.r]);

                final List<List<Set<Short>>> cConstraints = clone(constraints);
                commit(cConstraints, pos.r, pos.c, value);

                if (backtrack(depth + 1, cConstraints)) {
                    return true;
                }

                assert board.clear(pos);
            }
        }

        return false;
    }

    @Override
    protected boolean internalSolve() {
        return backtrack(0, initConstraints());
    }
}