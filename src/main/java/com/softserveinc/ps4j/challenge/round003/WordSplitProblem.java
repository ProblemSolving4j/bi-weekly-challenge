package com.softserveinc.ps4j.challenge.round003;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Given a string and a dictionary, find out whether you can split the string into dictionary words.
 * Every word can be used unlimited number of times.
 *
 * <p>Input restrictions:
 * <li>arguments are non-null
 * <li>dictionary does not contain empty strings.
 */
class WordSplitProblem {

    private record SplitOption(String str, String word) {}
    private Deque<SplitOption> stack;

    boolean solve(String str, Set<String> dic) {
        stack = new ArrayDeque<>();
        findSplitOptions(str, dic);
        return split(dic);
    }

    private boolean split(Set<String> dic) {
        if (stack.isEmpty()) {
            return false;
        }
        SplitOption splitOption = stack.pollLast();
        String str = splitOption.str.replaceFirst(splitOption.word, "");
        if (str.isEmpty()) {
            return true;
        }
        findSplitOptions(str, dic);
        return split(dic);
    }

    private void findSplitOptions(String str, Set<String> dic) {
        var words = dic.stream().filter(word -> str.startsWith(word)).collect(Collectors.toList());
        for (String word : words) {
            stack.offerLast(new SplitOption(str, word));
        }
    }

}
