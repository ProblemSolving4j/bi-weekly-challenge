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

    int[] solve(int row) {
        int len = row + 1;

        var pascal = new int[len];
        int prev = pascal[0] = pascal[row] = 1;

        for (int mid = row / 2, i = 1; i <= mid; i++) {
            // Cast operand to long to avoid overflow on multiplication
            prev = pascal[i] = pascal[row - i] = (int) ((long) prev * (len - i) / i);
        }

        return pascal;
    }

}
