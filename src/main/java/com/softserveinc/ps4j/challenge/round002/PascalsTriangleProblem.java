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
        if (row < 0) throw new IllegalArgumentException("row >=0");
        int[] result = row == 0 ? new int[]{1} : row == 1 ? new int[]{1, 1} : null;
        int[] base = {1, 1};
        if (row > 1) {
            for (int r = 1; r < row; r++) {
                result = new int[base.length + 1];
                result[0] = result[result.length - 1] = 1;
                for (int c = 1; c < result.length - 1; c++) {
                    result[c] = base[c - 1] + base[c];
                }
                base = new int[result.length];
                System.arraycopy(result, 0, base, 0, base.length);
            }
        }
        return result;
    }
}
