package com.softserveinc.ps4j.challenge.round002;

import java.util.Arrays;

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

    int[] solve(int row) {
        // allocate and init result array
        int[] p = new int[row + 1];
        Arrays.fill(p, 0);
        p[0] = 1;
        for (int i = 1; i < row + 1; i++) {
            for (int j = i; j > 0; j--) {
                p[j] = p[j] + p[j - 1];
            }
        }
        return p;
    }

}
