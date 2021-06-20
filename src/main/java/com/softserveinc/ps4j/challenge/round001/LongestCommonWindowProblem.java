package com.softserveinc.ps4j.challenge.round001;

import java.util.List;

/**
 * Find the longest common sublist of the two given list.
 * Modifications to the lists and re-ordering of the elements are forbidden.
 * Null elements are permitted.
 */
class LongestCommonWindowProblem {

    <T> List<T> solve(List<T> seq1, List<T> seq2) {
        if (seq1.size() == 0 || seq2.size() == 0) return List.of();
        int n = seq1.size();
        int m = seq2.size();
        int[][] dp = new int[n + 1][m + 1];
        for (int i = n - 1; i >= 0; i--) {
            for (int j = m - 1; j >= 0; j--) {
                if (equals(seq1.get(i), seq2.get(j))) {
                    dp[i][j] = dp[i + 1][j + 1] + 1;
                }
            }
        }
        int maxm = 0;
        List<T> commonList = List.of();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (dp[i][j] > maxm) {
                    maxm = dp[i][j];
                    commonList = seq1.subList(i, i + dp[i][j]);
                }
            }
        }
        return commonList;
    }

    private <T> boolean equals(T t1, T t2) {
        return (t1 == null && t2 == null) || (t1 != null && t1.equals(t2));
    }
}
