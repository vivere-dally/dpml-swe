package sbuciu.sudoku.model;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Board {
    public static final short EMPTY = 0;
    public static final short N = 9;
    public static final short NH = N / 2;

    private final short[][] board;
    private final List<List<Set<Short>>> constraints = new ArrayList<>();
    private int constraintCount = N * N * N;

    private Board(short[][] board) {
        if (board.length != N || board[0].length != N) {
            throw new IllegalArgumentException(String.format("The board does not represent a %dx%d sudoku board", N, N));
        }

        for (final short[] row : board) {
            for (final short cell : row) {
                if (cell != EMPTY && (cell < 1 || cell > 9)) {
                    throw new IllegalArgumentException("Illegal value found on board");
                }
            }
        }

        this.board = board;
        initConstraints();
    }

    public static Board read(String path) {
        if (!new File(path).exists()) {
            throw new IllegalArgumentException(String.format("Path %s does not exist.", path));
        }

        List<String> lines;
        try {
            lines = Files.readAllLines(Paths.get(path));
        } catch (IOException e) {
            throw new IllegalArgumentException(String.format("Could not read content at path %s.", path));
        }

        if (lines.size() != N) {
            throw new IllegalArgumentException(String.format("Content at path %s needs to be %d rows long and represent a sudoku board.", path, N));
        }

        final short[][] board = new short[N][N];
        for (short row = 0; row < N; row += 1) {
            final String[] cells = lines.get(row).split(",");
            if (cells.length != N) {
                throw new IllegalArgumentException(String.format("Content at path %s needs to be %d columns long and represent a sudoku board.", path, N));
            }

            for (short col = 0; col < N; col += 1) {
                if (cells[col].isEmpty()) {
                    board[row][col] = EMPTY;
                    continue;
                }

                board[row][col] = Short.parseShort(cells[col]);
            }
        }

        return new Board(board);
    }

    public String encode() {
        final StringBuilder bob = new StringBuilder();
        for (int i = 0; i < N; i += 1) {
            for (int j = 0; j < N; j += 1) {
                bob.append(board[i][j]);
                if (j < N - 1) {
                    bob.append(',');
                }
            }

            bob.append(System.lineSeparator());
        }

        return bob.toString();
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
    }

    public short at(final Pos pos) {
        if (pos.noPos || pos.r < 0 || pos.r >= N || pos.c < 0 || pos.c >= N) {
            return EMPTY;
        }

        return board[pos.r][pos.c];
    }

    public Pos findEmptyPos() {
        for (short r = 0; r < N; r += 1) {
            for (short c = 0; c < N; c += 1) {
                if (board[r][c] == EMPTY) {
                    return new Pos(r, c);
                }
            }
        }

        return Pos.noPos();
    }

    public boolean commit(final Pos pos, short value) {
        // Check if position is and the board is empty
        if (pos.noPos || at(pos) != EMPTY) {
            return false;
        }

//        if (!constraints.get(pos.r).get(pos.c).contains(value)) {
//            return false;
//        }

        // Check if valid row-wise and col-wise
        for (int i = 0; i < N; i += 1) {
            if (board[pos.r][i] == value || board[i][pos.c] == value) {
                return false;
            }
        }

        // Check if valid in the correct (NHxNH) square
        for (int r = 0; r < pos.r / NH; r += 1) {
            for (int c = 0; c < pos.c / NH; c += 1) {
                if (board[r][c] == value) {
                    return false;
                }
            }
        }

        // Commit
        board[pos.r][pos.c] = value;
        return true;
    }
}
