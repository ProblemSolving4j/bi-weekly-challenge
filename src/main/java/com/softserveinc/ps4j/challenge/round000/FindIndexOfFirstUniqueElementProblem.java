package com.softserveinc.ps4j.challenge.round000;

import java.util.*;

/**
 * Given a list of objects, return an index of its first unique element if one exists.
 * Modifications to the list are forbidden.
 * Null elements in the list are permitted.
 */
class FindIndexOfFirstUniqueElementProblem {

    OptionalInt solve(List<?> list) {
        int size = list.size();
        switch (size) {
            case 0: return OptionalInt.empty();
            case 1: return OptionalInt.of(0);
        }

        var occurrences = new HashMap<Object, Integer>();
        var indices = new LinkedHashMap<Object, Integer>();

        for (int i = 0; i < size; i++) {
            var element = list.get(i);
            switch (occurrences.merge(element, 1, Integer::sum)) {
                case 1 -> indices.put(element, i);
                case 2 -> indices.remove(element);
            }
        }

        return indices.values().stream()
                .mapToInt(Integer::intValue)
                .findFirst();
    }

}
