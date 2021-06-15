package com.softserveinc.ps4j.challenge.round001;

/**
 * Given an expression string, examine whether the pairs and the orders of
 * “{“, “}”, “(“, “)”, “[“, “]” are correct.
 * Example: “[()]{}{[()()]()}” is balanced, "[(])" is not.
 * Any non-paren symbols should be ignored.
 */
class MatchingParenExpressionProblem {

    boolean solve(String s) {
        int len = s.length();

        int stackSize = len / 2;
        char[] stack = new char[stackSize];
        int top = -1;

        for (int i = 0; i < len; i++) {
            char c = s.charAt(i);

            if (isOpeningParen(c)) {
                if (top == stackSize - 1) {
                    return false;
                }

                stack[++top] = c;
                continue;
            }

            if (isClosingParen(c)) {
                if (top == -1) {
                    return false;
                }

                if (!parensMatchEachOther(stack[top], c)) {
                    return false;
                }

                top--;
            }

            // Else - ignore.
        }

        return top == -1;
    }

    private boolean parensMatchEachOther(char left, char right) {
        return (left == '[' && right == ']') ||
                (left == '{' && right == '}') ||
                (left == '(' && right == ')');
    }

    private boolean isOpeningParen(char c) {
        switch (c) {
            case '[':
            case '{':
            case '(':
                return true;
            default:
                return false;
        }
    }

    private boolean isClosingParen(char c) {
        switch (c) {
            case ']':
            case '}':
            case ')':
                return true;
            default:
                return false;
        }
    }

}
