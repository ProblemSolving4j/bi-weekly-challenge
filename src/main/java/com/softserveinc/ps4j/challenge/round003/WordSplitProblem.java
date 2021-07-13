package com.softserveinc.ps4j.challenge.round003;

import java.util.Set;

/**
 * Given a string and a dictionary, find out whether you can split the string into dictionary words.
 * Every word can be used unlimited number of times.
 *
 * <p>Input restrictions:
 * <li>arguments are non-null
 * <li>dictionary does not contain empty strings.
 */
class WordSplitProblem {

    boolean solve(String s, Set<String> dictionary) {

        return split(s, dictionary);
    }

    private boolean split(String s, Set<String> dictionary) {
        boolean canSplit = false;

        for (String entry : dictionary) {
            if (s.length() < entry.length()) continue;
            String word = s.substring(0, entry.length());
            if (word.equals(entry)) {
                String newString = s.substring(entry.length());
                if (newString.length() > 0) {
                    canSplit = split(newString, dictionary);
                } else {
                    canSplit = true;
                }
                break;
            }
        }
        
        return canSplit;
    }
}
