package com.softserveinc.ps4j.challenge.round003;

import java.util.*;
import java.util.stream.Collectors;

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
        if (set.isEmpty()) return Set.of(Set.of());
        if (set.size() == 1) return Set.of(Set.of(), set);
        List<Set<Set<T>>> list = new ArrayList<>(set.size()); // use list to group sub-sets by level
        list.add(0, Set.of(Set.of())); // level 0 is a set of a single empty set
        for (int i = 1; i < set.size(); i++) { // calculate all sub-sets for levels 1 to (size - 1)
            Set<Set<T>> levelSets = new HashSet<>(); // stores all sets of the current level
            for (Set<T> prevSet : list.get(i - 1)) { // process all sets from the prev level
                for (T elem : set) {
                    Set<T> levelSet = new HashSet<T>(prevSet);
                    levelSet.add(elem);
                    levelSets.add(levelSet);
                }
            }
            list.add(i, levelSets);
        }
        Set<Set<T>> power = list.stream().flatMap(setOfSets -> setOfSets.stream()).collect(Collectors.toSet());
        power.add(set);
        return power;
    }

}
