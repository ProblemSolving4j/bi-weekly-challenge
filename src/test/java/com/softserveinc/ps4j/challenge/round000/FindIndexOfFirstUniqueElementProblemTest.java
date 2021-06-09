package com.softserveinc.ps4j.challenge.round000;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.OptionalInt;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class FindIndexOfFirstUniqueElementProblemTest {

    private FindIndexOfFirstUniqueElementProblem problem;

    @BeforeEach
    void setUp() {
        problem = new FindIndexOfFirstUniqueElementProblem();
    }

    @Test
    @DisplayName("find the index of the first unique element")
    void testSolution() {
        assertAll(
                this::shouldNotFind,
                () -> shouldFind(0, "A"),
                () -> shouldNotFind("A", "A"),
                () -> shouldFind(1, "A", "B", "A"),
                () -> shouldFind(2, "A", "A", "B"),
                () -> shouldNotFind("A", "B", "A", "B", "A"),
                () -> shouldFind(0, "A", "B", "C", "C"),
                () -> shouldFind(1, "A", "B", "C", "C", "A"),
                () -> shouldFind(2, "A", "A", null, "B"),
                () -> shouldFind(0, "A", "B", null, "C"),
                () -> shouldNotFind("A", null, null, "A"),
                () -> shouldFind(5, 5, 4, 3, 2, 1, 0, 1, 2, 3, 4, 5)
        );
    }

    private void shouldFind(int index, Object... elements) {
        OptionalInt solution = problem.solve(Arrays.asList(elements));
        assertThat(solution).hasValue(index);
    }

    private void shouldNotFind(Object... elements) {
        OptionalInt solution = problem.solve(Arrays.asList(elements));
        assertThat(solution).isEmpty();
    }
}