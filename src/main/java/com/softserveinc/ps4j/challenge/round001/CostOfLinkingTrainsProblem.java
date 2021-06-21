package com.softserveinc.ps4j.challenge.round001;

import java.util.Arrays;

/**
 * We need to link the trains with different numbers of carts into one train.
 * The cost of linking two trains is equal to the sum of the amount of their carts.
 * Return the minimal cost of linking all the trains.
 */
class CostOfLinkingTrainsProblem {

    int solve(int[] trains) {
        int trainSize = 0;
        if (trains.length == 0) {
            return trainSize;
        } else if (trains.length == 1) {
            return trains[0];
        }
        Arrays.sort(trains);
        int lastCart = trainSize = trains[0] + trains[1];
        for (int i=2; i < trains.length; i++) {
            lastCart = lastCart + trains[i];
            trainSize += lastCart;
        }
        return trainSize;
    }

}
