package com.softserveinc.ps4j.challenge.round001;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Given an expression string, examine whether the pairs and the orders of
 * “{“, “}”, “(“, “)”, “[“, “]” are correct.
 * Example: “[()]{}{[()()]()}” is balanced, "[(])" is not.
 * Any non-paren symbols should be ignored.
 */
class MatchingParenExpressionProblem {

    boolean solve(String s) {
        var validator = new BracketValidator(s);
        while (validator.hasNext()) {
            if (!validator.proceed()) return false;
        }
        return validator.isValid();
    }

    private static final class BracketValidator {

        final String expression;

        final int len;

        final Deque<Character> stack;

        int idx;

        BracketValidator(String expression) {
            this.expression = expression;
            this.len = expression.length();
            this.stack = new ArrayDeque<>(len);
        }

        boolean hasNext() {
            return idx < len;
        }

        boolean proceed() {
            char c = expression.charAt(idx++);
            return switch (c) {
                case '(', '{', '[' -> open(c);
                case ')' -> close('(');
                case '}' -> close('{');
                case ']' -> close('[');
                default -> true;
            };
        }

        boolean isValid() {
            return stack.isEmpty();
        }

        private boolean open(char bracket) {
            stack.push(bracket);
            return true;
        }

        private boolean close(char bracket) {
            return !stack.isEmpty() && stack.pop() == bracket;
        }
    }

}
