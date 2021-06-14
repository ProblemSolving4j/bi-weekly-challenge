package com.softserveinc.ps4j.challenge.round001;

import java.util.Arrays;
import java.util.stream.IntStream;

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
        if (trains.length == 2) {
            return IntStream.of(trains).sum();
        }

        Arrays.sort(trains); // as far as sums are accumulating the least cost will be if we start connecting shortest trains first

        int newTrain = trains[0] + trains [1];
        int cost = newTrain;

        for (var i = 0; i < trains.length; i++) {
            newTrain += trains[i];
            cost += newTrain;
        }

        return cost;
    }

}
