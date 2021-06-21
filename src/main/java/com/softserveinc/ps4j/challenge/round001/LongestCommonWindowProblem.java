package com.softserveinc.ps4j.challenge.round001;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Find the longest common sublist of the two given list.
 * Modifications to the lists and re-ordering of the elements are forbidden.
 * Null elements are permitted.
 */
class LongestCommonWindowProblem {

    <T> List<T> solve(List<T> seq1, List<T> seq2) {
        if (seq1.isEmpty() && seq2.isEmpty()
        || seq1.isEmpty()
        || seq2.isEmpty()) {
            return Collections.emptyList();
        }
        Map<List<T>, Integer> commonWindows = new HashMap<>();
        List<T> commonWindow = new ArrayList<>();
        List<T> shortestList = seq1.size() > seq2.size() ? seq2 : seq1;
        List<T> longestList = seq1.size() > seq2.size() ? seq1 : seq2;
        int commonWindowCurrentIndex = 0;
        for (int i=0; i < shortestList.size(); i++) {
            T el1 = shortestList.get(i);
            for (int j=commonWindowCurrentIndex; j < longestList.size(); j++) {
                T el2 = longestList.get(j);
                if (Objects.equals(el1, el2)) {
                    commonWindow.add(el1);
                    if (i == shortestList.size() - 1) {
                        commonWindows.put(commonWindow, commonWindow.size());
                    }
                    commonWindowCurrentIndex = j + 1; //if we found the same numbers we need to continue checking next number instead of looking from the beginning
                    break;
                } else if (!commonWindow.isEmpty()) {
                    commonWindows.put(commonWindow, commonWindow.size());
                    commonWindow = new ArrayList<>();
                    commonWindowCurrentIndex = 0;
                }
            }
        }

        if (commonWindows.isEmpty()) {
            return List.of();
        }

        return commonWindows.entrySet()
                .stream()
                .max(Map.Entry.comparingByValue())
                .get()
                .getKey();
    }

}
