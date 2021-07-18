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
        if (s.length() == 0) return false;
        return solveHelper(s, dictionary);
    }

    boolean solveHelper(String s, Set<String> dictionary) {
        boolean[] wb = new boolean[s.length() + 1];
        for (int i = 1; i <= s.length(); i++) {
            if (!wb[i] && dictionary.contains(s.substring(0, i))) {
                wb[i] = true;
            }
            if (wb[i]) {
                if (i == s.length()) {
                    return true;
                }
                for (int j = i + 1; j <= s.length(); j++) {
                    if (!wb[j] && dictionary.contains(s.substring(i, j))) {
                        wb[j] = true;
                    }
                    if (j == s.length() && wb[j]) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
