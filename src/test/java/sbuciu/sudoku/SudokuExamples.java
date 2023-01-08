package sbuciu.sudoku;

import static sbuciu.sudoku.model.Board.EMPTY;

// https://sandiway.arizona.edu/sudoku/examples.html
public class SudokuExamples {

    public static short[][] simple() {
        return new short[][]{
                {EMPTY, EMPTY, EMPTY, 2, 6, EMPTY, 7, EMPTY, 1},
                {6, 8, EMPTY, EMPTY, 7, EMPTY, EMPTY, 9, EMPTY},
                {1, 9, EMPTY, EMPTY, EMPTY, 4, 5, EMPTY, EMPTY},
                {8, 2, EMPTY, 1, EMPTY, EMPTY, EMPTY, 4, EMPTY},
                {EMPTY, EMPTY, 4, 6, EMPTY, 2, 9, EMPTY, EMPTY},
                {EMPTY, 5, EMPTY, EMPTY, EMPTY, 3, EMPTY, 2, 8},
                {EMPTY, EMPTY, 9, 3, EMPTY, EMPTY, EMPTY, 7, 4},
                {EMPTY, 4, EMPTY, EMPTY, 5, EMPTY, EMPTY, 3, 6},
                {7, EMPTY, 3, EMPTY, 1, 8, EMPTY, EMPTY, EMPTY}
        };
    }

    public static short[][] medium() {
        return new short[][]{
                {},
        };
    }

    public static short[][] hard() {
        return new short[][]{
                {},
        };
    }

}
