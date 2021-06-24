package com.softserveinc.ps4j.challenge.round002;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PascalsTriangleProblemTest {

    private PascalsTriangleProblem problem;

    @BeforeEach
    void setUp() {
        problem = new PascalsTriangleProblem();
    }

    @Test
    @DisplayName("nth row of the Pascal's triangle")
    void testSolution() {
        assertAll(
                () -> nthRowShouldBe(0, 1),
                () -> nthRowShouldBe(1, 1, 1),
                () -> nthRowShouldBe(2, 1, 2, 1),
                () -> nthRowShouldBe(3, 1, 3, 3, 1),
                () -> nthRowShouldBe(4, 1, 4, 6, 4, 1),
                () -> nthRowShouldBe(5, 1, 5, 10, 10, 5, 1),
                () -> nthRowShouldBe(6, 1, 6, 15, 20, 15, 6, 1),
                () -> nthRowShouldBe(7, 1, 7, 21, 35, 35, 21, 7, 1),
                () -> nthRowShouldBe(8, 1, 8, 28, 56, 70, 56, 28, 8, 1),
                () -> nthRowShouldBe(9, 1, 9, 36, 84, 126, 126, 84, 36, 9, 1)
        );
    }

    private void nthRowShouldBe(int n, int... row) {
        int[] result = problem.solve(n);
        assertArrayEquals(row, result, () -> "row " + n + " invalid");
    }

}