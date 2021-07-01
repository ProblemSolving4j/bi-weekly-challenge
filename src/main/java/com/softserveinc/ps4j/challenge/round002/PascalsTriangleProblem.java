package com.softserveinc.ps4j.challenge.round002;

/**
 * Pascal's triangle is triangular matrix that looks like this:
 * <pre>
 * row 0:        1
 * row 1:       1 1
 * row 2:      1 2 1
 * row 3:     1 3 3 1
 * row 4:    1 4 6 4 1
 * ...
 * </pre>
 * <p>Each value in a row is a sum of two values from the previous row.
 * <p>Task: Calculate the row with a given index.
 * <p>Input restrictions: {@code row >= 0}
 */
class PascalsTriangleProblem {

    int[] solve(int n) {
        // Calculating all binomial coefficients C(k,n) for the fixed n.

        int[] c = new int[n + 1];
        c[0] = 1;

        for (int k = 1; k <= n; k++) {
            c[k] = (c[k - 1] * (n - k + 1)) / k;
        }

        return c;
    }

}
