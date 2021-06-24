package com.softserveinc.ps4j.challenge.round002;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class MazeTraversalProblemTest {

    private static final Duration SOLUTION_TIME_THRESHOLD = Duration.ofSeconds(3);

    private MazeTraversalProblem problem;

    @BeforeEach
    void setUp() {
        problem = new MazeTraversalProblem();
    }

    @Test
    @DisplayName("traverse a 2d maze")
    void testSolution() {

        assertAll(
                () -> shouldBeTraversable("SF", c(0, 0), c(0, 1)),
                () -> shouldNotBeTraversable("S█F"),
                () -> shouldBeTraversable("""
                                S···
                                ███F
                                """,
                        c(0, 0),
                        c(0, 1),
                        c(0, 2),
                        c(0, 3),
                        c(1, 3)),
                () -> shouldNotBeTraversable("""
                        S███
                        █·██
                        ██·█
                        ███F
                        """),
                () -> shouldBeTraversable("""
                                ███·███
                                S·····F
                                ███·███
                                """,
                        c(1, 0),
                        c(1, 1),
                        c(1, 2),
                        c(1, 3),
                        c(1, 4),
                        c(1, 5),
                        c(1, 6)),
                () -> shouldBeTraversable("""
                                S█···██
                                ·█·█··F
                                ·█··███
                                ·██···█
                                ····█·█
                                """,
                        c(0, 0), c(1, 0), c(2, 0), c(3, 0),
                        c(4, 0), c(4, 1), c(4, 2), c(4, 3),
                        c(3, 3), c(2, 3),
                        c(2, 2), c(1, 2), c(0, 2),
                        c(0, 3), c(0, 4),
                        c(1, 4), c(1, 5), c(1, 6)),
                () -> shouldNotBeTraversable("""
                        S█···██
                        ·█·██·F
                        ·█··███
                        ·██···█
                        ····█·█
                        """)
        );
    }

    private void shouldNotBeTraversable(String maze) {
        Optional<List<Cell>> solution = solve(maze);
        assertThat(solution)
                .withFailMessage(() -> "There should be no path through the maze:%n%s".formatted(maze))
                .isEmpty();
    }

    private void shouldBeTraversable(String maze, Cell... path) {
        Optional<List<Cell>> solution = solve(maze);
        assertThat(solution)
                .withFailMessage(() -> "Invalid path through the maze:%n%s".formatted(maze))
                .hasValue(Arrays.asList(path));
    }

    private Optional<List<Cell>> solve(String maze) {
        Maze m = maze(maze);
        return assertTimeoutPreemptively(
                SOLUTION_TIME_THRESHOLD,
                () -> problem.solve(m),
                () -> "Maze:%n%s".formatted(maze));
    }

    private static Maze maze(String maze) {
        char[][] rows = maze.lines()
                .map(String::toCharArray)
                .toArray(char[][]::new);
        Cell entry = null;
        Cell exit = null;
        List<Cell> traversable = new ArrayList<>();
        for (int i = 0; i < rows.length; i++) {
            char[] row = rows[i];
            for (int j = 0; j < row.length; j++) {
                char mark = row[j];

                switch (mark) {
                    case 'S' -> entry = c(i, j);
                    case 'F' -> exit = c(i, j);
                    case '·' -> traversable.add(c(i, j));
                }
            }
        }
        return new Maze(entry, exit, traversable);
    }

    private static Cell c(int x, int y) {
        return new Cell(x, y);
    }
}