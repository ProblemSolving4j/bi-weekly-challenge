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

    <T> List<T> solve(List<T> x, List<T> y) {
        if (x.isEmpty() || y.isEmpty()) {
            return Collections.emptyList();
        }

        // xLen >= yLen
        int xLen = x.size();
        int yLen = y.size();

        int maxLen = 0;
        int xEndIndex = -1;

        int[] dp = new int[yLen];

        T xFirst = x.get(0);
        for (int j = 0; j < yLen; j++) {
            if (Objects.equals(xFirst, y.get(j))) {
                dp[j] = 1;

                if (maxLen < 1) {
                    maxLen = 1;
                    xEndIndex = 0;
                }
            }
        }

        for (int i = 1; i < xLen; i++) {
            T xElem = x.get(i);

            for (int j = yLen - 1; j >= 0; j--) {
                if (Objects.equals(xElem, y.get(j))) {
                    dp[j] = (j == 0 ? 0 : dp[j - 1]) + 1;

                    if (maxLen < dp[j]) {
                        maxLen = dp[j];
                        xEndIndex = i;
                    }
                } else {
                    dp[j] = 0;
                }
            }
        }

        if (maxLen == 0) {
            return Collections.emptyList();
        }

        return x.subList(xEndIndex + 1 - maxLen, xEndIndex + 1);
    }

}
