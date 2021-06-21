package com.softserveinc.ps4j.challenge.round001;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * For a given list, find its smallest possible sublist that contains all of its unique elements.
 * If several subsists satisfy the requirement, return the first.
 * Modifications to the list and re-ordering of the elements are forbidden.
 * Null elements are permitted.
 */
class MinWindowWithAllUniqueElementsProblem {

    <T> List<T> solve(List<T> list) {
        if (list.size() <= 1) {
            return list;
        }

        Set<T> uniqueElements = new HashSet<>(list);

        if (uniqueElements.size() == list.size()) {
            return list;
        }

        List<List<T>> uniqueElementsLists = new LinkedList<>();
        List<T> uniqueWindowElements = new ArrayList<>();
        for (T t : list) {
            if (!uniqueWindowElements.contains(t)) {
                uniqueWindowElements.add(t);
            } else if (uniqueWindowElements.containsAll(uniqueElements)) {
                removeFirstDuplicatedItems(uniqueWindowElements);
                int i = uniqueWindowElements.indexOf(t);
                uniqueElementsLists.add(uniqueWindowElements);
                uniqueWindowElements = new ArrayList<>(uniqueWindowElements.subList(i + 1, uniqueWindowElements.size()));
                uniqueWindowElements.add(t);
            } else if (uniqueWindowElements.get(0).equals(t)) {
                uniqueWindowElements.remove(0);
                uniqueWindowElements.add(t);
            } else {
                uniqueWindowElements.add(t);
            }
        }
        uniqueElementsLists.add(uniqueWindowElements);

        return uniqueElementsLists.stream()
                .filter(l -> l.containsAll(uniqueElements))
                .min(Comparator.comparingInt(List::size))
                .orElse(uniqueWindowElements);
    }

    /**
     * Removes duplicates in the beginning of the list
     * @param list
     * @param <T>
     */
    <T>void removeFirstDuplicatedItems(List<T> list) {
        T firstElement = list.get(0);
        for (int j = 1; j<list.size(); j++) {
            if (firstElement == list.get(j)) {
                list.remove(j);
            } else {
                break;
            }
        }
    }

}
