package com.softserveinc.ps4j.challenge.round003;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;

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
        var daysOfWeek = EnumSet.allOf(DayOfWeek.class);

        schedule.forEach(s -> {
            s.getDaysOfWeek().forEach(daysOfWeek::remove);
        });

        return daysOfWeek;
    }

}

/**
 * A time window with the start time and duration
 */
record TimeWindow(ZonedDateTime start, Duration duration) {

    List<DayOfWeek> getDaysOfWeek() {
        List<DayOfWeek> scheduledDaysOfWeek = new ArrayList<>();

        if (duration.toDays() > 7) {
            scheduledDaysOfWeek.addAll(EnumSet.allOf(DayOfWeek.class));
            return scheduledDaysOfWeek;
        }

        DayOfWeek startDayOfWeek = start.getDayOfWeek();
        scheduledDaysOfWeek.add(startDayOfWeek);
        int s = startDayOfWeek.getValue();
        var end = start.plus(duration);
        int e = end.getDayOfWeek().getValue();
        int d = (e >= s) ? (e - s) : (e + 7 - s);
        for (int i = 1; i <= d; i++) {
            if ((s + i) <= 7) {
                scheduledDaysOfWeek.add(DayOfWeek.of(s + i));
            } else {
                scheduledDaysOfWeek.add(DayOfWeek.of(s + i - 7));
            }
        }

        return scheduledDaysOfWeek;
    }
}