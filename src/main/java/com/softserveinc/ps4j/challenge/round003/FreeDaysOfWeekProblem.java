package com.softserveinc.ps4j.challenge.round003;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.Period;
import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
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

    Set<DayOfWeek> solve(List<TimeWindow> schedule) {
        EnumSet<DayOfWeek> busyDays = schedule.stream()
                .map(timeWindow -> {
                    ZonedDateTime start = timeWindow.start();
                    ZonedDateTime end = timeWindow.start().plusNanos(timeWindow.duration().toNanos());
                    int countOfDays = Period.between(start.toLocalDate(), end.toLocalDate()).getDays();
                    if (countOfDays >= 7) return EnumSet.allOf(DayOfWeek.class);
                    return getDaysOfWeek(start, countOfDays);
                })
                .flatMap(Set::stream)
                .collect(Collectors.toCollection(() -> EnumSet.noneOf(DayOfWeek.class)));
        return EnumSet.complementOf(busyDays);
    }

    private EnumSet<DayOfWeek> getDaysOfWeek(ZonedDateTime start, int countOfDays) {
        Set<DayOfWeek> dayOfWeeks = new HashSet<>();
        for (int i = 0; i <= countOfDays; i++) {
            dayOfWeeks.add(start.plusDays(i).getDayOfWeek());
        }
        return EnumSet.copyOf(dayOfWeeks.stream().toList());
    }
}

/**
 * A time window with the start time and duration
 */
record TimeWindow(ZonedDateTime start, Duration duration) {
}