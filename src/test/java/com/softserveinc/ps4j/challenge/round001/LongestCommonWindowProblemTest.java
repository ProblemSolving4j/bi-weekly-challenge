package com.softserveinc.ps4j.challenge.round001;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LongestCommonWindowProblemTest {

    private LongestCommonWindowProblem problem;

    @BeforeEach
    void setUp() {
        problem = new LongestCommonWindowProblem();
    }

    @Test
    @DisplayName("longest common window")
    void testSolution() {
        assertAll(
                () -> shouldFind(List.of(), List.of(), List.of()),
                () -> shouldFind(List.of(), List.of(), Arrays.asList(null, null)),
                () -> shouldFind(List.of(), List.of(1), List.of()),
                () -> shouldFind(List.of(), List.of(1), List.of(2)),
                () -> shouldFind(List.of(1), List.of(1), List.of(1)),
                () -> shouldFind(List.of(1), List.of(1), List.of(1, 2)),
                () -> shouldFind(Arrays.asList(null, 1), Arrays.asList(null, 1), Arrays.asList(null, 1, null)),
                () -> shouldFind(List.of(1), List.of(1, 2), List.of(1)),
                () -> shouldFind(List.of(1, 2, 3, 4), List.of(0, 1, 2, 3, 4, 8, 9), List.of(1, 2, 3, 4, 5, 6, 7)),
                () -> shouldFind(List.of(1, 2, 3, 4), List.of(0, 1, 2, 3, 4, 8, 9), List.of(1, 2, 3, 4, 5, 6, 8, 9)),
                () -> shouldFind(List.of(1, 2, 3, 4), List.of(0, 1, 2, 3, 0, 1, 2, 3, 4, 5, 6, 7), List.of(0, 1, 2, 1, 2, 3, 4, 0, 1, 2))
        );
    }

    private <T> void shouldFind(List<T> common, List<T> first, List<T> second) {
        assertEquals(common, problem.solve(first, second));
    }
}