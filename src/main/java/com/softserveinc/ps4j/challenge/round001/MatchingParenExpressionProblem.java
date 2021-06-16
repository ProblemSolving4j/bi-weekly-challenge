package com.softserveinc.ps4j.challenge.round001;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Given an expression string, examine whether the pairs and the orders of
 * “{“, “}”, “(“, “)”, “[“, “]” are correct.
 * Example: “[()]{}{[()()]()}” is balanced, "[(])" is not.
 * Any non-paren symbols should be ignored.
 */
class MatchingParenExpressionProblem {
    private static final Map<Character, Character> SYMBOL_MATCHING = new HashMap<>();
    static {
        SYMBOL_MATCHING.put('{', '}');
        SYMBOL_MATCHING.put('[', ']');
        SYMBOL_MATCHING.put('(', ')');
    }

    boolean solve(String s) {

        var ch = (char) 0;
        var expressions = new ArrayList<Expression>();

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
                            || !expressions.get(expressions.size() - 1).closeWith(ch)) {
                        return false;
                    } else {
                        expressions.remove(expressions.size() - 1);
                    }
                    break;
            }
        }

        return expressions.size() == 0;
    }

    private class Expression {
        char openingSymbol;

        private Expression (char openingSymbol) {
            this.openingSymbol = openingSymbol;
        }

        private boolean closeWith (char closingSymbol) {
            return closingSymbol == SYMBOL_MATCHING.get(openingSymbol);
        }
    }
}
