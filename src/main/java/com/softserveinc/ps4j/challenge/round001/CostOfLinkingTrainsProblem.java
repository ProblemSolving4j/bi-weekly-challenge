package com.softserveinc.ps4j.challenge.round001;

import java.util.PriorityQueue;

/**
 * We need to link the trains with different numbers of carts into one train.
 * The cost of linking two trains is equal to the sum of the amount of their carts.
 * Return the minimal cost of linking all the trains.
 */
class CostOfLinkingTrainsProblem {

    int solve(int[] trains) {
        int len = trains.length;
        // @formatter:off special cases
        switch (len) {
            case 0: return 0;
            case 1: return trains[0];
            case 2: return trains[0] + trains[1];
        }
        // @formatter:on
        var pq = new PriorityQueue<Integer>(len);
        for (int train : trains) {
            pq.add(train);
        }
        int cost = 0;
        while (true) {
            int next = pq.remove() + pq.remove();
            cost += next;
            if (pq.isEmpty()) break;
            pq.add(next);
        }
        return cost;
    }

}
