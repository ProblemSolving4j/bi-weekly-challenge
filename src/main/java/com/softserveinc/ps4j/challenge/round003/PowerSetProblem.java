package com.softserveinc.ps4j.challenge.round003;

import java.util.Collections;
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

        Set<T> empty = Collections.emptySet();
        int setSize = set.size();
        // @formatter:off special cases
        switch (setSize) {
            case 0: return Set.of(empty);
            case 1: return Set.of(empty, set);
        }
        // @formatter:on
        int powerSetSize = 1 << setSize;
        var powerSet = new HashSet<Set<T>>(powerSetSize);
        powerSet.add(empty);
        powerSet.add(set);

        var indexedElements = set.stream().toList();

        for (int hi = powerSetSize - 1, bits = 1; bits < hi; bits++) {

            int subSetSize = Integer.bitCount(bits);
            var subSet = new HashSet<T>(subSetSize);

            // iterate over 1-bits of the current bit vector
            for (int idx = firstSetBit(bits); idx < setSize; idx = nextSetBit(bits, idx + 1)) {
                subSet.add(indexedElements.get(idx));
            }
            powerSet.add(subSet);
        }
        return powerSet;
    }

    private static int firstSetBit(int bits) {
        return Integer.numberOfTrailingZeros(bits);
    }

    private static int nextSetBit(int bits, int lo) {
        return lo + Integer.numberOfTrailingZeros(bits >>> lo);
    }

}
