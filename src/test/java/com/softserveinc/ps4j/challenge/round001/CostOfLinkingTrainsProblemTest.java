package com.softserveinc.ps4j.challenge.round001;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CostOfLinkingTrainsProblemTest {

    private CostOfLinkingTrainsProblem problem;

    @BeforeEach
    void setUp() {
        problem = new CostOfLinkingTrainsProblem();
    }

    @Test
    @DisplayName("min cost of linking trains")
    void testSolution() {
        assertAll(
                () -> minCostShouldBe(0),
                () -> minCostShouldBe(1, 1),
                () -> minCostShouldBe(3, 1, 2),
                () -> minCostShouldBe(6, 1, 2, 1),    // (1 + 1) -> (2 + 2) = 2 + 4 = 6
                () -> minCostShouldBe(9, 1, 2, 3),    // (1 + 2) -> (3 + 3) = 3 + 6 = 9
                () -> minCostShouldBe(19, 1, 2, 3, 4),// (1 + 2) -> (3 + 3) -> (6 + 4) = 3 + 6 + 10 = 19
                () -> minCostShouldBe(29, 6, 3, 4, 2) // (2 + 3) -> (5 + 4) -> (9 + 6) = 5 + 9 + 15 = 29
        );
    }

    void minCostShouldBe(int cost, int... trains) {
        assertEquals(cost, problem.solve(trains));
    }
}