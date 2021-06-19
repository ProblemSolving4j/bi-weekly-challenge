package com.softserveinc.ps4j.challenge.round001;

import java.util.ArrayList;
import java.util.List;

/**
 * Find the longest common sublist of the two given list.
 * Modifications to the lists and re-ordering of the elements are forbidden.
 * Null elements are permitted.
 */
class LongestCommonWindowProblem {

    <T> List<T> solve(List<T> seq1, List<T> seq2) {

        if (seq1.size() == 0 || seq2.size() == 0) return List.of();

        var shortList = seq1;
        var longList = seq2;

        if (seq1.size() > seq2.size()) {
            shortList = seq2;
            longList = seq1;
        }

        var soughtListLength = shortList.size();
        var soughtLists = new ArrayList<List<T>>(soughtListLength - 1);

        for (var i = 0; i < soughtListLength; i++) {
            soughtLists = createSoughtLists(shortList, soughtListLength - i);
            for (var soughtList : soughtLists) {
                if (sequenceContainsSequence(longList, soughtList)) {
                    return soughtList;
                }
            }
        }

        return List.of();
    }

    private <T> ArrayList<List<T>> createSoughtLists(List<T> originalList, int length) {
        var numberOfLists = originalList.size() - length;
        var list = new ArrayList<List<T>>(numberOfLists);

        for (var i = 0; i <= numberOfLists; i++) {
            var newSoughtList = new ArrayList<T>(length);

            for (var j = 0; j < length; j++) {
                newSoughtList.add(originalList.get(j + i));
            }

            list.add(newSoughtList);
        }

        return list;
    }

    private <T> boolean sequenceContainsSequence (List<T> searchedSequence, List<T> soughtSequence) {
        var searchedSequencesList = createSoughtLists(searchedSequence, soughtSequence.size());

        for (var sequence : searchedSequencesList) {
            if (sequence.equals(soughtSequence)) return true;
        }

        return false;
    }
}
