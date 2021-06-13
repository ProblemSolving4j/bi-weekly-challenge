package com.softserveinc.ps4j.challenge.round001;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MatchingParenExpressionProblemTest {

    private MatchingParenExpressionProblem problem;

    @BeforeEach
    void setUp() {
        problem = new MatchingParenExpressionProblem();
    }

    @Test
    @DisplayName("matching parens in an expression")
    void testSolution() {
        assertAll(
                () -> shouldBeValid(""),
                () -> shouldBeValid("()"),
                () -> shouldBeValid("(a)"),
                () -> shouldBeValid("(ab)"),
                () -> shouldBeValid("{}"),
                () -> shouldBeValid("[]"),
                () -> shouldBeValid("a{ab}a(ab)a{ab}a"),
                () -> shouldBeValid("[()]{}{[()()]()}"),
                () -> shouldBeValid("{{}{}}{}([()])[][[]][]"),

                () -> shouldBeInvalid("("),
                () -> shouldBeInvalid(")"),
                () -> shouldBeInvalid("{"),
                () -> shouldBeInvalid("}"),
                () -> shouldBeInvalid("["),
                () -> shouldBeInvalid("]"),
                () -> shouldBeInvalid("(()"),
                () -> shouldBeInvalid("())"),
                () -> shouldBeInvalid("{{}"),
                () -> shouldBeInvalid("{}}"),
                () -> shouldBeInvalid("[[]"),
                () -> shouldBeInvalid("[]]"),
                () -> shouldBeInvalid("[(])"),
                () -> shouldBeInvalid("{(})"),
                () -> shouldBeInvalid("{{)]"),
                () -> shouldBeInvalid("{{}{}}{}[([()])[][[]][]")
        );
    }

    private void shouldBeValid(String expression) {
        assertTrue(problem.solve(expression));
    }

    private void shouldBeInvalid(String expression) {
        assertFalse(problem.solve(expression));
    }

}