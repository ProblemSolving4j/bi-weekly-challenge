package com.softserveinc.ps4j.challenge.round005;

import java.util.Arrays;
import java.util.List;

/**
 * Solve a Sudoku puzzle by filling the empty cells.
 *
 * <p>A sudoku solution must satisfy all the following rules:
 *
 * <p>Each of the digits 1-9 must occur exactly once in each row.
 * <p>Each of the digits 1-9 must occur exactly once in each column.
 * <p>Each of the digits 1-9 must occur exactly once in each of the 9 3x3 sub-boxes of the grid.
 *
 * <p>Input restrictions:
 * All the puzzles are solvable and have only 1 valid solution.
 */
class SudokuSolverProblem {

    void solve(SudokuBoard board) {
        throw new UnsupportedOperationException("not yet implemented");
    }

}

enum SudokuDigit {

    ONE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE;

    private static final List<SudokuDigit> VALUES = List.of(values());

    static SudokuDigit valueOf(int value) {
        return VALUES.get(value - 1);
    }

    int toInt() {
        return ordinal() + 1;
    }
}

final class SudokuBoard {

    static final int BOARD_SIDE = 9;

    private final SudokuDigit[][] state = new SudokuDigit[BOARD_SIDE][BOARD_SIDE];

    SudokuDigit get(int row, int col) {
        return state[row][col];
    }

    void set(int row, int col, SudokuDigit digit) {
        state[row][col] = digit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SudokuBoard that = (SudokuBoard) o;
        return Arrays.deepEquals(state, that.state);
    }

    @Override
    public int hashCode() {
        return Arrays.deepHashCode(state);
    }

    @Override
    public String toString() {
        return Arrays.deepToString(state);
    }
}