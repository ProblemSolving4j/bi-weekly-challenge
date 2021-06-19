package com.softserveinc.ps4j.challenge.round001;

import java.util.LinkedList;

/**
 * Given an expression string, examine whether the pairs and the orders of
 * “{“, “}”, “(“, “)”, “[“, “]” are correct.
 * Example: “[()]{}{[()()]()}” is balanced, "[(])" is not.
 * Any non-paren symbols should be ignored.
 */
class MatchingParenExpressionProblem {

    boolean solve(String s) {

        var ch = (char) 0;
        var expressions = new LinkedList<Expression>();

        for (var i = 0; i < s.length(); i++) {
            ch = s.charAt(i);
            switch (ch) {
                case '[':
                case '{':
                case '(':
                    expressions.add(new Expression(ch));
                    break;
                case ']':
                case '}':
                case ')':
                    if (expressions.size() == 0
                            || !expressions.getLast().closeWith(ch)) {
                        return false;
                    } else {
                        expressions.removeLast();
                    }
                    break;
            }
        }

        return expressions.size() == 0;
    }

    private class Expression {
        char openingSymbol;
        char closingSymbolExpected;

        private Expression (char openingSymbol) {
            this.openingSymbol = openingSymbol;
            switch (openingSymbol) {
                case '[' -> closingSymbolExpected = ']';
                case '{' -> closingSymbolExpected = '}';
                case '(' -> closingSymbolExpected = ')';
            }
        }

        private boolean closeWith (char closingSymbol) {
            return closingSymbol == closingSymbolExpected;
        }
    }
}
