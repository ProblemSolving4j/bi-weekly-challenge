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
        // allocate and init two arrays, their roles (source and target) will rotate
        int[][] p = new int[2][row + 1];
        Arrays.fill(p[0], 0);
        Arrays.fill(p[1], 0);
        p[0][0] = 1;
        p[1][0] = 1;
        for (int i = 1; i < row + 1; i++) {
            int s = (i + 1) % 2, t = i % 2; // index of source and target arrays for this row
            for (int j = 1; j < i + 1; j++) { // row i has (i + 1) elements
                p[t][j] = p[s][j] + p[s][j - 1]; // read data from source and set to target
            }
        }
        return p[row % 2]; // return target array
    }

}
