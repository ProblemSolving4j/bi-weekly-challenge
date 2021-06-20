package com.softserveinc.ps4j.challenge.round001;

import java.util.Set;
import java.util.Stack;

/**
 * Given an expression string, examine whether the pairs and the orders of
 * “{“, “}”, “(“, “)”, “[“, “]” are correct.
 * Example: “[()]{}{[()()]()}” is balanced, "[(])" is not.
 * Any non-paren symbols should be ignored.
 */
class MatchingParenExpressionProblem {

    boolean solve(String s) {
        Stack<Character> stack = new Stack<>();
        Set<Character> brackets = Set.of('(', ')', '{', '}', '[', ']');
        for (int i = 0; i < s.length(); i++) {
            char x = s.charAt(i);
            if (!brackets.contains(x)) continue;
            if (x == '(' || x == '[' || x == '{') {
                stack.push(x);
                continue;
            }
            if (stack.isEmpty()) return false;
            char check = stack.pop();
            if (!bracketsMatch(x, check)) return false;
        }
        return (stack.isEmpty());
    }

    private boolean bracketsMatch(char x, char check) {
        return (x == ')' && check == '(') || (x == '}' && check == '{') || (x == ']' && check == '[');
    }
}
