package com.softserveinc.ps4j.challenge.round001;

import java.util.*;
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

        Arrays.sort(trains);

        var cost = 0;
        var trainsList = new LinkedList<Integer>();
        for (int train : trains) {
            trainsList.add(train);
        }

        while (trainsList.size() > 0) {
            if (trainsList.size() == 2) return cost + trainsList.getFirst() + trainsList.getLast();
            var newTrain = 0;

            for (var a = 0; a < 2; a++) {
                newTrain += trainsList.getFirst();
                trainsList.removeFirst();
            }

            cost += newTrain;

            var i = 0;
            while (trainsList.get(i) < newTrain) {
                i++;
                if (i == trainsList.size()) break;
            }

            if (i < trainsList.size()) {
                trainsList.add(i, newTrain);
            } else {
                trainsList.add(newTrain);
            }
        }

        return cost;
    }
}
