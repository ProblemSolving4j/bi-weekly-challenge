package com.softserveinc.ps4j.challenge.round003;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class WordSplitProblemTest {

    private WordSplitProblem problem;

    @BeforeEach
    void setUp() {
        problem = new WordSplitProblem();
    }

    @Test
    @DisplayName("can the string be split into dictionary words")
    void testSolution() {
        assertAll(
                () -> shouldNotBeSplittable("", Set.of()),
                () -> shouldNotBeSplittable("", Set.of("1")),
                () -> shouldNotBeSplittable("some string", Set.of()),
                () -> shouldBeSplittable("one", Set.of("one")),
                () -> shouldBeSplittable("oneoneone", Set.of("one")),
                () -> shouldNotBeSplittable("onetwo", Set.of("one")),
                () -> shouldBeSplittable("oneonetwo", Set.of("one", "two")),
                () -> shouldBeSplittable("threetwotwoonethreeonetwo", Set.of("one", "two", "three")),
                () -> shouldNotBeSplittable("threetwotwoonethreeonetwot", Set.of("one", "two", "three")),
                () -> shouldNotBeSplittable("abbc", Set.of("abb", "bbc")),
                () -> shouldNotBeSplittable("abbbc", Set.of("abb", "bbc")),
                () -> shouldBeSplittable("abbbbc", Set.of("abb", "bbc"))
        );
    }

    private void shouldBeSplittable(String s, Set<String> dict) {
        assertTrue(problem.solve(s, dict), () -> s + ": " + dict);
    }

    private void shouldNotBeSplittable(String s, Set<String> dict) {
        assertFalse(problem.solve(s, dict), () -> s + ": " + dict);
    }
}
