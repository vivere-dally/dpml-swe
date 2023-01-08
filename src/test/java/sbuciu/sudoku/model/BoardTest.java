package sbuciu.sudoku.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static sbuciu.sudoku.model.Board.*;

class BoardTest {
    private static final int RATIO = N + N - 1 + (M - 1) * (M - 1);

    void assertTrueSquare(final Board board, final short value, final short row, final short col) {
        int sqr = row / M, sqc = col / M;
        for (int r = sqr * M; r < (sqr + 1) * M; r += 1) {
            for (int c = sqc * M; c < (sqc + 1) * M; c += 1) {
                assertTrue(board.getConstraints().get(r).get(c).contains(value));
            }
        }
    }

    void assertFalseSquare(final Board board, final short value, final short row, final short col) {
        int sqr = row / M, sqc = col / M;
        for (int r = sqr * M; r < (sqr + 1) * M; r += 1) {
            for (int c = sqc * M; c < (sqc + 1) * M; c += 1) {
                assertFalse(board.getConstraints().get(r).get(c).contains(value));
            }
        }
    }

    @Test
    void commit() {
        final Board board = new Board(new short[N][N]);
        final int expectedConstraintsCount = N * N * N - RATIO;
        final short value = 7, row = 0, col = 0;

        assertTrue(board.commit(new Pos(row, col), value));
        assertFalse(board.commit(new Pos(row, col), value));
        assertEquals(expectedConstraintsCount, board.getConstraintCount());

        for (int i = 0; i < N; i += 1) {
            assertFalse(board.getConstraints().get(row).get(i).contains(value));
            assertFalse(board.getConstraints().get(i).get(col).contains(value));
        }

        assertFalseSquare(board, value, row, col);
    }

    @Test
    void commitAndClear() {
        final Board board = new Board(new short[N][N]);
        final int expectedConstraintsCount = N * N * N - RATIO;
        final short value = 3, row = 5, col = 5;

        assertTrue(board.commit(new Pos(row, col), value));
        assertFalse(board.commit(new Pos(row, col), value));
        assertEquals(expectedConstraintsCount, board.getConstraintCount());

        for (int i = 0; i < N; i += 1) {
            assertFalse(board.getConstraints().get(row).get(i).contains(value));
            assertFalse(board.getConstraints().get(i).get(col).contains(value));
        }

        assertFalseSquare(board, value, row, col);

        assertTrue(board.clear(new Pos(row, col)));
        assertEquals(N * N * N, board.getConstraintCount());

        assertTrueSquare(board, value, row, col);
    }

    @Test
    void commitMultiple() {
        final Board board = new Board(new short[N][N]);
        final short[][] coords = new short[][]{
                {2, 7, 3},
                {3, 3, 3},
                {5, 5, 5},
                {6, 6, 6}
        };

        for (final short[] coord : coords) {
            final Pos pos = new Pos(coord[0], coord[1]);
            final short value = coord[2];
            assertTrue(board.commit(pos, value));
            assertFalseSquare(board, value, coord[0], coord[1]);
            assertTrue(board.clear(pos));
            assertTrueSquare(board, value, coord[0], coord[1]);
        }
    }
}