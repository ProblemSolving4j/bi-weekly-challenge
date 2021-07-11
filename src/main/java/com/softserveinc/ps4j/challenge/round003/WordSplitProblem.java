package com.softserveinc.ps4j.challenge.round003;

import java.util.BitSet;
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
        if (s.isEmpty() || dictionary.isEmpty()) return false;

        int len = s.length();

        var dp = new BitSet(len + 1);
        dp.set(0);

        for (int hi = 1; hi <= len; hi++) {
            for (int lo = hi - 1; lo >= 0; lo--) {
                if (dp.get(lo) && dictionary.contains(s.substring(lo, hi))) {
                    dp.set(hi);
                    break;
                }
            }
        }
        return dp.get(len);
    }

}
