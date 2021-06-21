package com.softserveinc.ps4j.challenge.round001;

import java.util.PriorityQueue;

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

        PriorityQueue<Integer> queue = new PriorityQueue<>(trains.length);
        for (int train : trains) {
            queue.add(train);
        }

        int cost = 0;
        while (queue.size() > 1) {
            int sum = queue.poll() + queue.poll();
            queue.add(sum);

            cost += sum;
        }

        return cost;
    }

}
