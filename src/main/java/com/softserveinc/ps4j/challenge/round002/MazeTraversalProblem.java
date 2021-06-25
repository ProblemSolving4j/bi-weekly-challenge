package com.softserveinc.ps4j.challenge.round002;

import java.util.*;
import java.util.function.Function;

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

    Optional<List<Cell>> solve(Maze maze) {
        Cell start = maze.getEntry();
        Cell finish = maze.getExit();

        if (isCellBlocked(start, maze) || isCellBlocked(finish, maze)) return Optional.empty();

        var directions = new ArrayList<Function<Cell, Cell>>(4);
        directions.add(Cell::up);
        directions.add(Cell::right);
        directions.add(Cell::down);
        directions.add(Cell::left);

        var root = new ArrayDeque<Cell>();
        root.add(start);

        if (explore(maze, directions, root)) {
            return Optional.of(new ArrayList<>(root));
        }

        return Optional.empty();
    }

    private boolean isCellBlocked(Cell cell, Maze maze) {
        return !maze.canTraverse(cell.up())
                && !maze.canTraverse(cell.right())
                && !maze.canTraverse(cell.down())
                && !maze.canTraverse(cell.left());
    }

    private boolean explore(Maze maze, ArrayList<Function<Cell, Cell>> directions, ArrayDeque<Cell> root) {
        for (Function<Cell, Cell> direction : directions) {
            var nextCell = direction.apply(root.getLast());
            if (nextCell.equals(maze.getExit())) {
                root.add(maze.getExit());
                return true;
            }
            if (maze.canTraverse(nextCell)
                    && !root.contains(nextCell)) {
                root.add(nextCell);
                if (explore(maze, directions, root)) {
                    return true;
                }
            }
        }

        root.removeLast();
        return false;
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

