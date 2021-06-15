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

        if (list.size() == 0) return List.of();
        if (list.size() == 1) return list;

        var uniqueElements = new HashSet<>(list);
        if (uniqueElements.size() == 1) {
            return (List<T>) Arrays.asList(uniqueElements.toArray());
        }
        var minWindow = list;
        var uniqueElementsEncountered = new HashSet<T>();

        for (var i = 1; i < list.size(); i++) {

            if ((list.get(i) == null && list.get(i - 1) != null)
                    || (list.get(i) != null && list.get(i - 1) == null)
                    || !list.get(i).equals(list.get(i - 1))) {

                var j = i;
                var window = new ArrayList<T>();

                window.add(list.get(i - 1));
                uniqueElementsEncountered.add(list.get(i - 1));

                while (uniqueElementsEncountered.size() < uniqueElements.size()) {

                    window.add(list.get(j));
                    uniqueElementsEncountered.add(list.get(j));
                    j++;
                    if (j >= list.size()) break;
                }

                if (uniqueElementsEncountered.size() == uniqueElements.size() && window.size() < minWindow.size()) minWindow = window;
            }

            uniqueElementsEncountered.clear();
        }

        return minWindow;
    }
}
