package com.softserveinc.ps4j.challenge.round005;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SudokuSolverProblemTest {

    private SudokuSolverProblem problem;

    @BeforeEach
    void setUp() {
        problem = new SudokuSolverProblem();
    }

    @Test
    @DisplayName("solve Sudoku puzzle")
    void testSolution() {
        assertAll(
                () -> test("""
                                . . . 2 6 . 7 . 1
                                6 8 . . 7 . . 9 .
                                1 9 . . . 4 5 . .
                                8 2 . 1 . . . 4 .
                                . . 4 6 . 2 9 . .
                                . 5 . . . 3 . 2 8
                                . . 9 3 . . . 7 4
                                . 4 . . 5 . . 3 6
                                7 . 3 . 1 8 . . .
                                """,
                        """
                                4 3 5 2 6 9 7 8 1
                                6 8 2 5 7 1 4 9 3
                                1 9 7 8 3 4 5 6 2
                                8 2 6 1 9 5 3 4 7
                                3 7 4 6 8 2 9 1 5
                                9 5 1 7 4 3 6 2 8
                                5 1 9 3 2 6 8 7 4
                                2 4 8 9 5 7 1 3 6
                                7 6 3 4 1 8 2 5 9
                                """),
                () -> test("""
                                . 2 . 6 . 8 . . .
                                5 8 . . . 9 7 . .
                                . . . . 4 . . . .
                                3 7 . . . . 5 . .
                                6 . . . . . . . 4
                                . . 8 . . . . 1 3
                                . . . . 2 . . . .
                                . . 9 8 . . . 3 6
                                . . . 3 . 6 . 9 .
                                """,
                        """
                                1 2 3 6 7 8 9 4 5
                                5 8 4 2 3 9 7 6 1
                                9 6 7 1 4 5 3 2 8
                                3 7 2 4 6 1 5 8 9
                                6 9 1 5 8 3 2 7 4
                                4 5 8 7 9 2 6 1 3
                                8 3 6 9 2 4 1 5 7
                                2 1 9 8 5 7 4 3 6
                                7 4 5 3 1 6 8 9 2
                                """),
                () -> test("""
                                . . . 6 . . 4 . .
                                7 . . . . 3 6 . .
                                . . . . 9 1 . 8 .
                                . . . . . . . . .
                                . 5 . 1 8 . . . 3
                                . . . 3 . 6 . 4 5
                                . 4 . 2 . . . 6 .
                                9 . 3 . . . . . .
                                . 2 . . . . 1 . .
                                """,
                        """
                                5 8 1 6 7 2 4 3 9
                                7 9 2 8 4 3 6 5 1
                                3 6 4 5 9 1 7 8 2
                                4 3 8 9 5 7 2 1 6
                                2 5 6 1 8 4 9 7 3
                                1 7 9 3 2 6 8 4 5
                                8 4 5 2 1 9 3 6 7
                                9 1 3 7 6 8 5 2 4
                                6 2 7 4 3 5 1 9 8
                                """)
        );
    }

    private void test(String initial, String solved) {
        SudokuBoard board = compile(initial);
        problem.solve(board);
        assertEquals(compile(solved), board);
    }

    private static SudokuBoard compile(String text) {

        var board = new SudokuBoard();

        int[][] state = text.lines()
                .map(row -> row.chars()
                        .filter(c -> !Character.isWhitespace(c))
                        .map(c -> Character.digit(c, 10))
                        .toArray())
                .toArray(int[][]::new);

        for (int i = 0; i < SudokuBoard.BOARD_SIDE; i++) {
            int[] row = state[i];
            for (int j = 0; j < SudokuBoard.BOARD_SIDE; j++) {
                int digit = row[j];
                if (digit >= 1 && digit <= 9) {
                    board.set(i, j, SudokuDigit.valueOf(digit));
                }
            }
        }

        return board;
    }
}