package com.softserveinc.ps4j.challenge.round001;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * Find the longest common sublist of the two given list.
 * Modifications to the lists and re-ordering of the elements are forbidden.
 * Null elements are permitted.
 */
class LongestCommonWindowProblem {

    <T> List<T> solve(List<T> seq1, List<T> seq2) {
        int m = seq1.size();
        int n = seq2.size();

        return m > n ? lcw(seq1, seq2, m, n) : lcw(seq2, seq1, n, m);
    }

    private static <T> List<T> lcw(List<T> seq1, List<T> seq2, int m, int n) {
        int maxLen = 0;
        int endIdx = -1;

        int[] dp = new int[n];

        for (int i = 0; i < m; i++) {

            T e1 = seq1.get(i);

            for (int prevLen = 0, j = 0; j < n; j++) {

                int tmp = dp[j];

                T e2 = seq2.get(j);

                if (Objects.equals(e1, e2)) {
                    int len = dp[j] = prevLen + 1;
                    if (maxLen < len) {
                        if ((maxLen = len) == n) return seq2;
                        endIdx = i;
                    }
                } else {
                    dp[j] = 0;
                }
                prevLen = tmp;
            }
        }

        return switch (maxLen) {
            case 0 -> Collections.emptyList();
            case 1 -> Collections.singletonList(seq1.get(endIdx));
            default -> seq1.subList(endIdx + 1 - maxLen, endIdx + 1);
        };
    }

}
