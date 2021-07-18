package com.softserveinc.ps4j.challenge.round003;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * In Set Theory, a power set P(S) of a set S
 * is a set of all of the subsets of S, including empty set and S itself.
 *
 * <p>Calculate P(S).
 *
 * <p>Input restrictions:
 * <li>S != null
 * <li>size(S) <= 16.
 */
class PowerSetProblem {

    <T> Set<Set<T>> solve(Set<T> set) {
        List<T> givenSet = set.stream().toList();
        List<T> subset = createSubset(givenSet);
        Set<Set<T>> result = new HashSet<>();
        if (set.size() == 0) return Set.of(Set.of());
        helper(subset, givenSet, result, 0);
        return result;
    }

    private <T> void helper(List<T> subset, List<T> givenSet, Set<Set<T>> result, int i) {
        if (i == givenSet.size()) {
            result.add(createResultSet(subset));
        }
        if (i < givenSet.size()) {
            subset.set(i, null);
            helper(subset, givenSet, result, i + 1);
            subset.set(i, givenSet.get(i));
            helper(subset, givenSet, result, i + 1);
        }
    }

    private <T> List<T> createSubset(List<T> givenSet) {
        List<T> subset = new ArrayList<>(givenSet.size());
        for (int i = 0; i < givenSet.size(); i++) {
            subset.add(null);
        }
        return subset;
    }

    private <T> Set<T> createResultSet(List<T> subset) {
        Set<T> resultSet = new HashSet<T>(subset);
        resultSet.removeAll(Collections.singleton(null));
        return resultSet;
    }
}
