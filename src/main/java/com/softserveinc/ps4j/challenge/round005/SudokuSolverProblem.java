package com.softserveinc.ps4j.challenge.round005;

import java.util.Arrays;
import java.util.List;

import static com.softserveinc.ps4j.challenge.round005.SudokuBoard.BOARD_SIDE;

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
        Cell firstEmpty = findFirstEmpty(board);
        if (firstEmpty == null) {
            return;
        }
        for (int n = 1; n <= BOARD_SIDE; n++) {
            SudokuDigit sudokuDigit = SudokuDigit.valueOf(n);
            if (isValidSoFar(board, firstEmpty, sudokuDigit)) {
                board.set(firstEmpty.i, firstEmpty.j, sudokuDigit);
                solve(board);
                if (isComplete(board)) {
                    return;
                }
            }
            board.set(firstEmpty.i, firstEmpty.j, null);
        }
    }

    private boolean isComplete(SudokuBoard board) {
        for (int i = 0; i < BOARD_SIDE; i++) {
            for (int j = 0; j < BOARD_SIDE; j++) {
                SudokuDigit sudokuDigit = board.get(i, j);
                if (sudokuDigit == null) {
                    return false;
                }
            }
        }
        return true;
    }

    private Cell findFirstEmpty(SudokuBoard board) {
        for (int i = 0; i < BOARD_SIDE; i++) {
            for (int j = 0; j < BOARD_SIDE; j++) {
                SudokuDigit sudokuDigit = board.get(i, j);
                if (sudokuDigit == null) {
                    return new Cell(i, j);
                }
            }
        }
        return null;
    }

    private boolean isValidSoFar(SudokuBoard board, Cell cell, SudokuDigit sd) {
        return rowValid(board, cell.i, sd) && colValid(board, cell.j, sd) && blockValid(board, cell.i, cell.j, sd);
    }

    private boolean rowValid(SudokuBoard board, int row, SudokuDigit sudokuDigit) {
        for (int j = 0; j < BOARD_SIDE; j++) {
            if (sudokuDigit.equals(board.get(row, j))) {
                return false;
            }
        }
        return true;
    }

    private boolean colValid(SudokuBoard board, int col, SudokuDigit sudokuDigit) {
        for (int i = 0; i < BOARD_SIDE; i++) {
            if (sudokuDigit.equals(board.get(i, col))) {
                return false;
            }
        }
        return true;
    }

    private boolean blockValid(SudokuBoard board, int row, int col, SudokuDigit sudokuDigit) {
        int startRow = row - row % 3;
        int startCol = col - col % 3;
        for (int k = 0; k < 3; k++) {
            for (int l = 0; l < 3; l++) {
                if (sudokuDigit.equals(board.get(k + startRow, l + startCol))) {
                    return false;
                }
            }
        }
        return true;
    }

    record Cell(int i, int j) {
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