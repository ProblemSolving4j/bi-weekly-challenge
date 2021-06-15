package com.softserveinc.ps4j.challenge.round001;

import java.util.Arrays;

/**
 * We need to link the trains with different numbers of carts into one train.
 * The cost of linking two trains is equal to the sum of the amount of their carts.
 * Return the minimal cost of linking all the trains.
 */
class CostOfLinkingTrainsProblem {

    int solve(int[] trains) {
        if (trains.length == 0) {
            return 0;
        }

        if (trains.length == 1) {
            return trains[0];
        }

        Arrays.sort(trains);

        int k = trains.length - 1;
        int cost = k * (trains[0] + trains[1]);

        for (int i = 2; i < trains.length; i++) {
            cost += (--k) * trains[i];
        }

        return cost;
    }

}
