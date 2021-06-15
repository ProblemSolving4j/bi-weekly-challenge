package com.softserveinc.ps4j.challenge.round001;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;

/**
 * For a given list, find its smallest possible sublist that contains all of its unique elements.
 * If several subsists satisfy the requirement, return the first.
 * Modifications to the list and re-ordering of the elements are forbidden.
 * Null elements are permitted.
 */
class MinWindowWithAllUniqueElementsProblem {

    static final BiFunction<Object, Integer, Integer> INC = (k, old) -> 1 + (old == null ? 0 : old);
    static final BiFunction<Object, Integer, Integer> DEC = (k, old) -> old - 1;

    <T> List<T> solve(List<T> list) {
        if (list.isEmpty()) {
            return Collections.emptyList();
        }

        int distinctCount = (int) list.stream().distinct().count();
        if (distinctCount == 1) {
            return list.subList(0, 1);
        }

        int minWindowStart = 0;
        int minWindowSize = list.size();

        Map<T, Integer> elemCounts = new HashMap<>();
        int windowStart = 0;

        for (int i = 0; i < list.size(); i++) {
            T elem = list.get(i);
            elemCounts.compute(elem, INC);

            if (elemCounts.size() == distinctCount) {
                // We've found a solution.
                int windowSize = i - windowStart + 1;
                if (windowSize < minWindowSize) {
                    minWindowStart = windowStart;
                    minWindowSize = windowSize;
                }

                // Move to the next solution candidate.
                elemCounts.remove(list.get(windowStart));
                windowStart++;
            }

            while (elemCounts.get(list.get(windowStart)) > 1) {
                elemCounts.compute(list.get(windowStart), DEC);
                windowStart++;
            }
        }

        return list.subList(minWindowStart, minWindowStart + minWindowSize);
    }

}
