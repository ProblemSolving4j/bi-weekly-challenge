package com.softserveinc.ps4j.challenge.round001;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Given an expression string, examine whether the pairs and the orders of
 * “{“, “}”, “(“, “)”, “[“, “]” are correct.
 * Example: “[()]{}{[()()]()}” is balanced, "[(])" is not.
 * Any non-paren symbols should be ignored.
 */
class MatchingParenExpressionProblem {

    private static final List<Character> openTags = List.of('{','(','[');
    private static final List<Character> allTags = List.of('{','}','(',')','[',']');

    /**
     * main idea - we can add all open tags without validation, but each time when close-tag is comes
     * we need to check that it has correct open-part.
     * To achieve this I'm checking previous tag - if it's pair, than it's correct and I'm adding it to
     * validated part. If not, than I starting reversal cycle and trying to find the same type of tag,
     * which hasn't it's close-pair.
     *
     * @param s
     * @return
     */
    boolean solve(String s) {
        if (s.isEmpty()) {
            return true;
        } else if (s.length() == 1) {
            return false;
        }
        char[] chars = s.toCharArray();
        int amountOfOpenTags = 0, amountOfClosedTags = 0;
        for (int i=0; i < chars.length; i++) {
            char currentChar = chars[i];
            if (!allTags.contains(currentChar)) {
                continue;
            }

            if (openTags.contains(currentChar)) {
                amountOfOpenTags++;
            } else {
                amountOfClosedTags++;
                if (!isPair(chars[i-1], currentChar)) {
                    boolean isCurrentCharValid = false;
                    for (int j = chars.length - 1; j >= 0; j--) {
                        if (isPair(chars[j], currentChar) && (j == 0 || !isPair(chars[j], chars[j+1]))) {
                            isCurrentCharValid = containsOnlyPairs(chars, j, i);
                            break;
                        }
                    }
                    if (!isCurrentCharValid) {
                        return false;
                    }
                }
            }
        }

        return amountOfOpenTags == amountOfClosedTags;
    }

    boolean isPair(char first, char second) {
        return first == '{' && second == '}'
                || first == '(' && second == ')'
                || first == '[' && second == ']';
    }

    boolean containsOnlyPairs(char[] chars, int startIndex, int endIndex) {
        Map<Character, Integer> charCounter = allTags.stream()
                .collect(Collectors.toMap(Function.identity(), character -> 0));
        for (int i = startIndex; i <= endIndex; i++) {
            char c = chars[i];
            if (allTags.contains(c)) {
                charCounter.compute(c, (character, counter) -> counter+1);
            }
        }
        for (int i = 0; i < allTags.size(); i+=2) {
            Integer openCount = charCounter.get(allTags.get(i));
            Integer closeCount = charCounter.get(allTags.get(i+1));
            if (openCount.compareTo(closeCount) != 0) {
                return false;
            }
        }
        return true;
    }



}
