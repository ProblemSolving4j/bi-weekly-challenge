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
        if (houses.length == 0) return 0;
        if (houses.length == 1) return houses[0];
        int[] aux = new int[houses.length + 1];
        aux[0] = 0; // 0 houses robbed
        aux[1] = houses[0]; // 1 house robbed
        for (int i = 2; i < aux.length; i++) {
            aux[i] = Math.max(aux[i - 1], houses[i - 1] + aux[i - 2]);
        }
        return aux[houses.length];
    }

}
