package sbuciu.sudoku;

import org.junit.jupiter.api.Test;
import sbuciu.sudoku.model.Board;
import sbuciu.sudoku.model.SudokuSolution;

import static org.junit.jupiter.api.Assertions.*;

class SudokuSSForwardCheckTest {

    @Test
    public void solveSimple() {
        final Board board = new Board(SudokuExamples.simple());
        final short[][] expected = SudokuExamples.simpleSolution();

        final SudokuSolution sol = new SudokuSSForwardCheck(board).solve();

        assertTrue(sol.isSolved);
        for (int i = 0; i < Board.N; i += 1) {
            assertArrayEquals(expected[i], sol.board[i]);
        }
    }

    @Test
    public void solveMedium() {
        final Board board = new Board(SudokuExamples.medium());
        final short[][] expected = SudokuExamples.mediumSolution();

        final SudokuSolution sol = new SudokuSSForwardCheck(board).solve();

        assertTrue(sol.isSolved);
        for (int i = 0; i < Board.N; i += 1) {
            assertArrayEquals(expected[i], sol.board[i]);
        }
    }

    @Test
    public void solveHard() {
        final Board board = new Board(SudokuExamples.hard());
        final short[][] expected = SudokuExamples.hardSolution();

        final SudokuSolution sol = new SudokuSSForwardCheck(board).solve();

        assertTrue(sol.isSolved);
        for (int i = 0; i < Board.N; i += 1) {
            assertArrayEquals(expected[i], sol.board[i]);
        }
    }
}