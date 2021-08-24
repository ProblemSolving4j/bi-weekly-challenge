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
        var sequence = new SudokuSequence(board);

        if (!sequence.solve(0)) {
            throw new IllegalArgumentException("Unsolvable Sudoku!");
        }
    }

    private static final class SudokuSequence {

        private static final int[][] BOX_INDICES = {
                {0, 0, 0, 1, 1, 1, 2, 2, 2},
                {0, 0, 0, 1, 1, 1, 2, 2, 2},
                {0, 0, 0, 1, 1, 1, 2, 2, 2},
                {3, 3, 3, 4, 4, 4, 5, 5, 5},
                {3, 3, 3, 4, 4, 4, 5, 5, 5},
                {3, 3, 3, 4, 4, 4, 5, 5, 5},
                {6, 6, 6, 7, 7, 7, 8, 8, 8},
                {6, 6, 6, 7, 7, 7, 8, 8, 8},
                {6, 6, 6, 7, 7, 7, 8, 8, 8}
        };

        private static final int ALL_SET = 0b111_111_1110;

        final SudokuBoard board;

        final int[] rows = new int[9];

        final int[] cols = new int[9];

        final int[] boxes = new int[9];

        final int[] rowSeq = new int[81];

        final int[] colSeq = new int[81];

        final int size;

        SudokuSequence(SudokuBoard board) {
            this.board = board;
            int size = 0;
            for (int row = 0; row < 9; row++) {
                for (int col = 0; col < 9; col++) {
                    SudokuDigit digit = board.get(row, col);
                    if (digit == null) {
                        int idx = size++;
                        rowSeq[idx] = row;
                        colSeq[idx] = col;
                    } else {
                        flip(row, col, digit.toInt());
                    }
                }
            }
            this.size = size;
        }

        boolean solve(int position) {
            if (position == size) return true; // base case: we reached the end and all the cells can be filled

            int easiest = easiest(position);
            if (easiest < 0) {
                return false;
            }
            swap(position, easiest); // move the easiest cell to the current position

            int row = row(position);
            int col = col(position);
            int set = digits(position);

            for (int digit = nextDigit(set, 1); digit <= 9; digit = nextDigit(set, digit + 1)) {

                flip(row, col, digit); // place the digit

                // proceed with the DFS
                if (solve(position + 1)) {
                    board.set(row, col, SudokuDigit.valueOf(digit)); // fill the board as the recursion unwinds
                    return true;
                }
                // if a solution is not found, backtrack and try the next digit

                flip(row, col, digit);
            }
            swap(position, easiest); // revert modification to the sequence
            return false;
        }

        static int nextDigit(int set, int lowest) {
            return Integer.numberOfTrailingZeros(~set >>> lowest) + lowest;
        }

        static int box(int row, int col) {
            return BOX_INDICES[row][col];
        }

        int easiest(int from) {
            int easiest = -1, maxPopulation = 0;
            for (int i = from; i < size; i++) {
                int set = digits(i);
                if (set == ALL_SET) return -1; // if all digits are taken, then current solution is invalid
                int population = Integer.bitCount(set);
                if (population == 8) return i;
                if (population > maxPopulation) {
                    easiest = i;
                    maxPopulation = population;
                }
            }
            return easiest;
        }

        void swap(int p1, int p2) {
            int tmpR = rowSeq[p1], tmpC = colSeq[p1];
            rowSeq[p1] = rowSeq[p2];
            rowSeq[p2] = tmpR;
            colSeq[p1] = colSeq[p2];
            colSeq[p2] = tmpC;
        }

        int digits(int position) {
            int ri = rowSeq[position], ci = colSeq[position], bi = box(ri, ci);
            return rows[ri] | cols[ci] | boxes[bi];
        }

        int row(int position) {
            return rowSeq[position];
        }

        int col(int position) {
            return colSeq[position];
        }

        void flip(int row, int col, int val) {
            int bit = 1 << val;
            rows[row] ^= bit;
            cols[col] ^= bit;
            boxes[box(row, col)] ^= bit;
        }
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