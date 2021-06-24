package com.softserveinc.ps4j.challenge.round001;

import java.util.*;

/**
 * For a given list, find its smallest possible sublist that contains all of its unique elements.
 * If several subsists satisfy the requirement, return the first.
 * Modifications to the list and re-ordering of the elements are forbidden.
 * Null elements are permitted.
 */
class MinWindowWithAllUniqueElementsProblem {

    <T> List<T> solve(List<T> list) {
        int len = list.size();
        if (len < 2) return list;

        // enumerates the unique elements
        var dictionary = new LinkedHashMap<T, Integer>(len);
        // maps the position of the element to its id from the dictionary
        var ids = new int[len];

        for (int i = 0; i < len; i++) {
            ids[i] = dictionary.computeIfAbsent(list.get(i), e -> dictionary.size());
        }

        int distinct = dictionary.size();

        // @formatter:off special cases
        if (distinct == len) return list;
        if (distinct ==   1) return Collections.singletonList(list.get(0));
        if (distinct ==   2) return dictionary.keySet().stream().toList();
        // @formatter:on

        var occurrences = new int[distinct];

        int minLen = len;
        int start = 0;

        for (int matches = 0, lo = 0, hi = 0; hi < len; hi++) {
            if (++occurrences[ids[hi]] == 1) {
                matches++;
            }
            if (matches < distinct) {
                continue;
            }
            // try to minimize the window
            int element;
            while (occurrences[element = ids[lo]] > 1) {
                lo++;
                occurrences[element]--;
            }
            // update the min length, break the loop early if it's equal to the amount of distinct elements
            int windowLen = hi - lo + 1;
            if (windowLen < minLen) {
                minLen = windowLen;
                start = lo;
                if (minLen == distinct) break;
            }
        }
        return list.subList(start, start + minLen);
    }

}
