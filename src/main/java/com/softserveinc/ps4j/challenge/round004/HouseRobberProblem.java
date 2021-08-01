package com.softserveinc.ps4j.challenge.round004;

/**
 * You are a professional robber planning to rob houses along a street.
 * <p>Each house has a certain amount of money stashed,
 * the only constraint stopping you from robbing each of them is that
 * adjacent houses have security systems connected and it will
 * automatically contact the police if two adjacent houses were broken into on the same night.
 *
 * <p>Given an integer array representing the amount of money of each house,
 * return the maximum amount of money you can rob tonight without alerting the police.
 */
class HouseRobberProblem {

    int solve(int... houses) {
        int n = houses.length;

        if (n == 0) {
            return 0;
        }

        int value1 = houses[0];
        if (n == 1) {
            return value1;
        }

        int value2 = Math.max(houses[0], houses[1]);
        if (n == 2) {
            return value2;
        }

        // contains maximum stolen value at the end
        int max_val = 0;

        // Fill remaining positions
        for (int i = 2; i < n; i++)
        {
            max_val = Math.max(houses[i] + value1, value2);
            value1 = value2;
            value2 = max_val;
        }

        return max_val;
    }

}
