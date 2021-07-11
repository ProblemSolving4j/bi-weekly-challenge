package com.softserveinc.ps4j.challenge.round003;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class PowerSetProblemTest {

    private PowerSetProblem problem;

    @BeforeEach
    void setUp() {
        problem = new PowerSetProblem();
    }

    @Test
    @DisplayName("find the power set of the set")
    void testSolution() {
        assertAll(
                () -> test(Set.of(Set.of()), Set.of()),

                () -> test(Set.of(Set.of(), Set.of(1)), Set.of(1)),

                () -> test(Set.of(Set.of(), Set.of(1, 2), Set.of(1), Set.of(2)), Set.of(1, 2)),

                () -> test(Set.of(
                        Set.of(),
                        Set.of(1, 2, 3),
                        Set.of(1),
                        Set.of(2),
                        Set.of(3),
                        Set.of(1, 2),
                        Set.of(1, 3),
                        Set.of(2, 3)
                ), Set.of(1, 2, 3)),

                () -> test(Set.of(
                        Set.of(),
                        Set.of(1, 2, 3, 4),
                        Set.of(1),
                        Set.of(2),
                        Set.of(3),
                        Set.of(4),
                        Set.of(1, 2),
                        Set.of(1, 3),
                        Set.of(1, 4),
                        Set.of(2, 3),
                        Set.of(2, 4),
                        Set.of(3, 4),
                        Set.of(1, 2, 3),
                        Set.of(1, 2, 4),
                        Set.of(1, 3, 4),
                        Set.of(2, 3, 4)
                ), Set.of(1, 2, 3, 4))
        );
    }

    private <T> void test(Set<Set<T>> expected, Set<T> set) {
        assertEquals(expected, problem.solve(set));
    }
}
