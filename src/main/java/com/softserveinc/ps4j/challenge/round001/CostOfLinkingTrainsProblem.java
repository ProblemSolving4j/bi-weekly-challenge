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

        var cost = 0;
        var trainsList = new ArrayList<Integer>();
        for (int train : trains) {
            trainsList.add(train);
        }
        trainsList.sort((o1, o2) -> o1 - o2);

        while (trainsList.size() > 0) {
            if (trainsList.size() == 2) return cost + trainsList.stream().mapToInt(a -> a).sum();
            var newTrain = trainsList.get(0) + trainsList.get(1);
            cost += newTrain;
            trainsList.remove(trainsList.get(0));
            trainsList.remove(trainsList.get(0));

            if (trainsList.size() > 0 ) {
                var i = 0;
                while (trainsList.get(i) < newTrain) {
                    i++;
                    if (i == trainsList.size()) break;
                }

                if (trainsList.size() > i) {
                    trainsList.add(i, newTrain);
                } else {
                    trainsList.add(newTrain);
                }
            }
        }

        return cost;
    }
}
