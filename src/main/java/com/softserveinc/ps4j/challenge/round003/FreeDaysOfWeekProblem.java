package com.softserveinc.ps4j.challenge.round003;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.ZonedDateTime;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Given a schedule consisting of entries represented as {@link TimeWindow} objects,
 * find all the days of week that are free (as in, no time is taken on these days by any of the entries).
 *
 * <p>P.S. Try solving this using the Stream API!
 *
 * <p>Input restrictions:
 * <li>schedule != null
 */
class FreeDaysOfWeekProblem {
    private final Duration sixDays = Duration.ofDays(6);

    Set<DayOfWeek> solve(List<TimeWindow> schedule) {
        EnumSet<DayOfWeek> booked = schedule.stream()
                .flatMap(window -> getSpan(window).stream())
                .collect(Collectors.toCollection(() -> EnumSet.noneOf(DayOfWeek.class)));
        if (booked.size() == 7) {
            return Set.of();
        }
        return EnumSet.complementOf(booked);
    }

    private EnumSet<DayOfWeek> getSpan(TimeWindow window) {
        if (window.duration().equals(Duration.ZERO)) {
            return EnumSet.noneOf(DayOfWeek.class); // treat this as no time booked
        } else if (window.duration().compareTo(sixDays) > 0) {
            return EnumSet.allOf(DayOfWeek.class); // a large time frame that definitely spans the whole week
        }
        DayOfWeek first = window.start().getDayOfWeek();
        DayOfWeek last = window.start().plus(window.duration()).getDayOfWeek();
        if (first.compareTo(last) > 0) {
            var span = EnumSet.range(first, DayOfWeek.SUNDAY);
            span.addAll(EnumSet.range(DayOfWeek.MONDAY, last));
            return span;
        } else {
            return EnumSet.range(first, last);
        }
    }

}

/**
 * A time window with the start time and duration
 */
record TimeWindow(ZonedDateTime start, Duration duration) {
}