package com.softserveinc.ps4j.challenge.round001;

import java.util.ArrayList;
import java.util.HashSet;
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
        Set<T> unique = new HashSet<>(list);
        switch (unique.size()) {
            case 0: return List.of();
            case 1: return List.of(list.get(0));
        }
        if (unique.size() == list.size()) return list;
        List<T> window = new ArrayList<>();
        List<T> minList = list;
        for (T t : list) {
            window.add(t);
            if (window.containsAll(unique)) {
                while (window.subList(1, window.size()).containsAll(unique)) {
                    window.remove(0);
                }
                if (window.size() < minList.size()) {
                    minList = new ArrayList<>(window);
                    if (minList.size() == unique.size()) return minList;
                }
                window.remove(0);
            }
        }
        return minList;
    }

}
