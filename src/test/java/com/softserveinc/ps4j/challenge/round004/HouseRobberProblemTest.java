package com.softserveinc.ps4j.challenge.round004;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class HouseRobberProblemTest {

    private HouseRobberProblem problem;

    @BeforeEach
    void setUp() {
        problem = new HouseRobberProblem();
    }

    @Test
    @DisplayName("find max amount of loot you can take from the adjacent houses")
    void testSolution() {
        assertAll(
                () -> test(0),
                () -> test(1, 1),
                () -> test(2, 2),
                () -> test(4, 3, 4),
                () -> test(5, 2, 5, 3),
                () -> test(5, 1, 5, 3),
                () -> test(5, 2, 4, 3),
                () -> test(4, 1, 2, 3, 1),
                () -> test(12, 2, 7, 9, 3, 1)
        );
    }

    private void test(int expected, int... houses) {
        int solution = problem.solve(houses);
        assertEquals(expected, solution,
                () -> "Expected result %s for input %s, got %s instead"
                        .formatted(expected, Arrays.toString(houses), solution));
    }
}
