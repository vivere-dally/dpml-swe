package sbuciu.sudoku;

import static sbuciu.sudoku.model.Board.EMPTY;

// https://sandiway.arizona.edu/sudoku/examples.html
public class SudokuExamples {

    // Arizona Daily Wildcat: Tuesday, Jan 17th 2006
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

    public static short[][] simpleSolution() {
        return new short[][]{
                {4, 3, 5, 2, 6, 9, 7, 8, 1},
                {6, 8, 2, 5, 7, 1, 4, 9, 3},
                {1, 9, 7, 8, 3, 4, 5, 6, 2},
                {8, 2, 6, 1, 9, 5, 3, 4, 7},
                {3, 7, 4, 6, 8, 2, 9, 1, 5},
                {9, 5, 1, 7, 4, 3, 6, 2, 8},
                {5, 1, 9, 3, 2, 6, 8, 7, 4},
                {2, 4, 8, 9, 5, 7, 1, 3, 6},
                {7, 6, 3, 4, 1, 8, 2, 5, 9}
        };
    }

    // Daily Telegraph January 19th "Diabolical"
    public static short[][] medium() {
        return new short[][]{
                {EMPTY, 2, EMPTY, 6, EMPTY, 8, EMPTY, EMPTY, EMPTY},
                {5, 8, EMPTY, EMPTY, EMPTY, 9, 7, EMPTY, EMPTY},
                {EMPTY, EMPTY, EMPTY, EMPTY, 4, EMPTY, EMPTY, EMPTY, EMPTY},
                {3, 7, EMPTY, EMPTY, EMPTY, EMPTY, 5, EMPTY, EMPTY},
                {6, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, 4},
                {EMPTY, EMPTY, 8, EMPTY, EMPTY, EMPTY, EMPTY, 1, 3},
                {EMPTY, EMPTY, EMPTY, EMPTY, 2, EMPTY, EMPTY, EMPTY, EMPTY},
                {EMPTY, EMPTY, 9, 8, EMPTY, EMPTY, EMPTY, 3, 6},
                {EMPTY, EMPTY, EMPTY, 3, EMPTY, 6, EMPTY, 9, EMPTY}
        };
    }

    public static short[][] mediumSolution() {
        return new short[][]{
                {1, 2, 3, 6, 7, 8, 9, 4, 5},
                {5, 8, 4, 2, 3, 9, 7, 6, 1},
                {9, 6, 7, 1, 4, 5, 3, 2, 8},
                {3, 7, 2, 4, 6, 1, 5, 8, 9},
                {6, 9, 1, 5, 8, 3, 2, 7, 4},
                {4, 5, 8, 7, 9, 2, 6, 1, 3},
                {8, 3, 6, 9, 2, 4, 1, 5, 7},
                {2, 1, 9, 8, 5, 7, 4, 3, 6},
                {7, 4, 5, 3, 1, 6, 8, 9, 2}
        };
    }

    public static short[][] hard() {
        return new short[][]{
                {EMPTY, EMPTY, EMPTY, 6, EMPTY, EMPTY, 4, EMPTY, EMPTY},
                {7, EMPTY, EMPTY, EMPTY, EMPTY, 3, 6, EMPTY, EMPTY},
                {EMPTY, EMPTY, EMPTY, EMPTY, 9, 1, EMPTY, 8, EMPTY},
                {EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY},
                {EMPTY, 5, EMPTY, 1, 8, EMPTY, EMPTY, EMPTY, 3},
                {EMPTY, EMPTY, EMPTY, 3, EMPTY, 6, EMPTY, 4, 5},
                {EMPTY, 4, EMPTY, 2, EMPTY, EMPTY, EMPTY, 6, EMPTY},
                {9, EMPTY, 3, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY},
                {EMPTY, 2, EMPTY, EMPTY, EMPTY, EMPTY, 1, EMPTY, EMPTY}
        };
    }

    public static short[][] hardSolution() {
        return new short[][]{
                {5, 8, 1, 6, 7, 2, 4, 3, 9},
                {7, 9, 2, 8, 4, 3, 6, 5, 1},
                {3, 6, 4, 5, 9, 1, 7, 8, 2},
                {4, 3, 8, 9, 5, 7, 2, 1, 6},
                {2, 5, 6, 1, 8, 4, 9, 7, 3},
                {1, 7, 9, 3, 2, 6, 8, 4, 5},
                {8, 4, 5, 2, 1, 9, 3, 6, 7},
                {9, 1, 3, 7, 6, 8, 5, 2, 4},
                {6, 2, 7, 4, 3, 5, 1, 9, 8}
        };
    }
}
