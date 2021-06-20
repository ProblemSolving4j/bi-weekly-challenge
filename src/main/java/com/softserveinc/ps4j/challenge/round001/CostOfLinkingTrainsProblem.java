package com.softserveinc.ps4j.challenge.round001;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * We need to link the trains with different numbers of carts into one train.
 * The cost of linking two trains is equal to the sum of the amount of their carts.
 * Return the minimal cost of linking all the trains.
 */
class CostOfLinkingTrainsProblem {

    int solve(int[] trains) {
        switch (trains.length) {
            case 0: return 0;
            case 1: return trains[0];
            case 2: return trains[0] + trains[1];
        }
        PriorityQueue<Integer> pq = new PriorityQueue();
        Arrays.stream(trains).forEach(pq::add);
        return linkTrains(pq, 0);
    }

    private int linkTrains(PriorityQueue<Integer> trains, Integer cost) {
        switch (trains.size()) {
            case 1: return cost + trains.poll();
            case 2: return cost + trains.poll() + trains.poll();
        }
        int joinedTrain = trains.poll() + trains.poll();
        trains.add(joinedTrain);
        return linkTrains(trains, cost + joinedTrain);
    }
}
