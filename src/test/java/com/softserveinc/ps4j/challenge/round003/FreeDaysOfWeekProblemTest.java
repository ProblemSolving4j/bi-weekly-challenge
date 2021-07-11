package com.softserveinc.ps4j.challenge.round003;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.EnumSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

class FreeDaysOfWeekProblemTest {

    private FreeDaysOfWeekProblem problem;

    @BeforeEach
    void setUp() {
        problem = new FreeDaysOfWeekProblem();
    }

    @Test
    @DisplayName("find free days of week given a schedule")
    void testSolution() {
        assertAll(
                () -> test(EnumSet.allOf(DayOfWeek.class)),
                () -> test(
                        EnumSet.noneOf(DayOfWeek.class),
                        window("2021-07-12T12:00:00Z", Duration.ofDays(120))
                ),
                () -> test(
                        EnumSet.of(DayOfWeek.SATURDAY, DayOfWeek.SUNDAY),
                        window("2021-07-12T12:00:00Z", Duration.ofDays(4))),
                () -> test(
                        EnumSet.of(DayOfWeek.SATURDAY, DayOfWeek.SUNDAY),
                        window("2021-07-05T12:00:00Z", Duration.ofHours(1)),
                        window("2021-07-05T14:00:00Z", Duration.ofHours(1)),
                        window("2021-07-13T12:00:00Z", Duration.ofHours(1)),
                        window("2021-07-21T12:00:00Z", Duration.ofHours(1)),
                        window("2021-07-29T12:00:00Z", Duration.ofHours(1)),
                        window("2021-08-06T12:00:00Z", Duration.ofHours(1))),
                () -> test(
                        EnumSet.complementOf(EnumSet.of(DayOfWeek.MONDAY)),
                        window("2021-07-12T22:59:00Z", Duration.ofHours(1))
                ),
                () -> test(
                        EnumSet.complementOf(EnumSet.of(DayOfWeek.MONDAY, DayOfWeek.TUESDAY)),
                        window("2021-07-12T22:59:00Z", Duration.ofMinutes(61))
                )
        );
    }

    private void test(Set<DayOfWeek> expected, TimeWindow... schedule) {
        assertEquals(expected, problem.solve(Arrays.asList(schedule)));
    }

    private static TimeWindow window(String time, Duration duration) {
        return new TimeWindow(ZonedDateTime.parse(time), duration);
    }
}