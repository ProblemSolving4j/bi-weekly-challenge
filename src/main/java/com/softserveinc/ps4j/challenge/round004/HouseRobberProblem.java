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
        int prev1 = 0;
        int prev2 = 0;
        for (int house : houses) {
            int tmp = prev1;
            prev1 = Math.max(prev2 + house, prev1);
            prev2 = tmp;
        }
        return prev1;
    }

}
