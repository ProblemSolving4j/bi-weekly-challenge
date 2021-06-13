package com.softserveinc.ps4j.challenge.round001;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

class MinWindowWithAllUniqueElementsProblemTest {

    private MinWindowWithAllUniqueElementsProblem problem;

    @BeforeEach
    void setUp() {
        problem = new MinWindowWithAllUniqueElementsProblem();
    }

    @Test
    @DisplayName("find the min window with all unique elements")
    void testSolution() {
        assertAll(
                () -> shouldFind(List.of(), List.of()),
                () -> shouldFind(List.of(1), List.of(1)),
                () -> shouldFind(List.of(1, 2), List.of(1, 2)),
                () -> shouldFind(List.of(1, 2), List.of(1, 1, 2)),
                () -> shouldFind(List.of(1, 2), List.of(1, 1, 1, 2)),
                () -> shouldFind(Arrays.asList(null, 1, 2), Arrays.asList(1, null, 1, 2)),
                () -> shouldFind(List.of(1, 2), List.of(1, 1, 1, 2, 2, 2, 1, 2, 1, 2)),
                () -> shouldFind(List.of(1, 2, 3), List.of(1, 2, 3)),
                () -> shouldFind(List.of(2, 3, 4, 5, 1), List.of(1, 1, 2, /**/ 2, 3, 4, 5, 1, /**/ 1, 2, 3, 4, 5, 1, 2, 4, 3, 5)),
                () -> shouldFind(List.of(1, 2, 2, 3), List.of(1, 1, 1, 1, /**/ 1, 2, 2, 3, /**/ 3, 1, 1, 2, 1, 1, 3, 3, 2))
        );
    }

    private <T> void shouldFind(List<T> window, List<T> list) {
        assertEquals(window, problem.solve(list));
    }
}