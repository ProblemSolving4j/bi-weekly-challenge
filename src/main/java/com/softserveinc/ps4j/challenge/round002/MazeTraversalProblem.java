package com.softserveinc.ps4j.challenge.round002;

import java.util.*;

/**
 * Given a two-dimensional {@link Maze}, return a path
 * from the {@link Maze#getEntry()} to the {@link Maze#getExit()},
 * or {@link Optional#empty()} if it doesn't exist.
 *
 * <p>The maze consists of {@link Cell} objects representing 2d coordinates.
 * <p>To check if the coordinate is traversable, use the {@link Maze#canTraverse(Cell)} method.
 *
 * <p>In this problem, a maze can have AT MOST ONE valid path,
 * but it can also have multiple dead-ends.
 */
class MazeTraversalProblem {

    private Set<Cell> noGo;

    Optional<List<Cell>> solve(Maze maze) {
        noGo = new HashSet<>();
        Deque<Cell> path = new LinkedList<>();
        Cell prev, curr, next;
        curr = maze.getEntry();// track current position separately, so thee full path will be path + curr

        while (true) {
            prev = path.peekLast();
            next = getNext(maze, curr, prev); // curr is never null, prev and next might be null here
            // prev is null - we are on the entry cell
            // next is null - we have to make a step back from the entry cell
            if (next != null && !next.equals(prev)) {
                // step forward
                path.offerLast(curr);
                curr = next;
            } else {
                // step back
                noGo.add(curr);
                curr = path.pollLast();
            }
            // curr might be null here meaning we made a step back from the entry cell
            if (curr == null || curr.equals(maze.getEntry())) {
                // no path
                return Optional.empty();
            } else if (curr.equals(maze.getExit())) {
                // path found
                path.offerLast(curr);
                return Optional.of((List<Cell>) path);
            }
        }
    }

    private Cell getNext(Maze maze, Cell curr, Cell prev) {
        // next = prev only if no other moves are available
        for (int d = 0; d < 4; d++) {
            Cell next = switch (d) {
                case 0 -> curr.up();
                case 1 -> curr.right();
                case 2 -> curr.down();
                case 3 -> curr.left();
                default -> throw new IllegalStateException("Unsupported step direction");
            };
            if (maze.canTraverse(next) && !noGo.contains(next) && !next.equals(prev)) return next;
        }
        return prev;
    }

}

record Cell(int x, int y) {

    Cell up() {
        return new Cell(x - 1, y);
    }

    Cell down() {
        return new Cell(x + 1, y);
    }

    Cell left() {
        return new Cell(x, y - 1);
    }

    Cell right() {
        return new Cell(x, y + 1);
    }

}

final class Maze {

    private final Cell entry;

    private final Cell exit;

    private final Set<Cell> traversableCells;

    Maze(Cell entry, Cell exit, Collection<Cell> traversableCells) {
        this.entry = Objects.requireNonNull(entry);
        this.exit = Objects.requireNonNull(exit);
        this.traversableCells = new HashSet<>(traversableCells.size() + 2);
        this.traversableCells.add(entry);
        this.traversableCells.add(exit);
        this.traversableCells.addAll(traversableCells);
    }

    boolean canTraverse(Cell cell) {
        return traversableCells.contains(cell);
    }

    Cell getEntry() {
        return entry;
    }

    Cell getExit() {
        return exit;
    }

}

