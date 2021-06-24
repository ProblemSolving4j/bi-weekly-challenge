package com.softserveinc.ps4j.challenge.round002;

/**
 * Pascal's triangle is triangular matrix that looks like this:
 * <pre>
 * row 0:        1
 * row 1:      1 2 1
 * row 2:     1 3 3 1
 * row 3:    1 4 6 4 1
 * ...
 * </pre>
 * <p>Each value in a row is a sum of two values from the previous row.
 * <p>Task: Calculate the row with a given index.
 * <p>Input restrictions: {@code row >= 0}
 */
class PascalsTriangleProblem {

    int[] solve(int row) {
        if (row == 0) return new int[] {1};
        if (row == 1) return new int[] {1, 1};

        var pt = new int[row + 1];
        pt[0] = pt[row] = 1;
        var k = row / 2;

        for (var i = 1; i <= k; i++) {
            int n = pt[i - 1] * (row + 1 - i) / i;
            pt[i] = pt[row - i] = n;
        }

        return pt;
    }

}
