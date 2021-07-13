package com.softserveinc.ps4j.challenge.round003;

import java.util.HashSet;
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
        if (set == null) throw new IllegalArgumentException("Set cannot be null");
        if (set.size() > 16) throw new IllegalArgumentException("Set size cannot be greater than 16");
        if (set.size() == 0) return Set.of(Set.of());

        int n = set.size();
        Set<Set<T>> powerSet = new HashSet<>(1 << n);
        T[] a = (T[]) set.toArray();

        for (int i = 0; i < (1 << n); i++) {
            Set<T> subset = new HashSet<>(n);

            for (int j = 0; j < n; j++)
                if (((1 << j) & i) > 0) {
                    subset.add(a[j]);
                }

            powerSet.add(subset);
        }

        return powerSet;
    }
}
