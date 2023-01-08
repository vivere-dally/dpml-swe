package sbuciu.sudoku;

import org.junit.jupiter.api.Test;
import sbuciu.sudoku.model.Board;
import sbuciu.sudoku.model.SudokuSolution;

import static org.junit.jupiter.api.Assertions.assertTrue;

class SudokuBacktrackTest {

    @Test
    public void solve() {
        final Board board = new Board(SudokuExamples.simple());

        final SudokuSolution sol = new SudokuBacktrack(board).solve();

        assertTrue(sol.isSolved);
    }
}