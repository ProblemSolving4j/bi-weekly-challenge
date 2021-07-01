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

    Optional<List<Cell>> solve(Maze maze) {
        Cell entry = maze.getEntry();
        Cell exit = maze.getExit();

        Set<Cell> visitedCells = new HashSet<>();

        LinkedList<Cell> path = new LinkedList<>();
        visitedCells.add(entry);
        path.add(entry);

        if (dfs(entry, exit, path, visitedCells, maze)) {
            return Optional.of(path);
        }

        return Optional.empty();
    }

    private boolean dfs(Cell current, Cell target, LinkedList<Cell> path, Set<Cell> visited, Maze maze) {
        if (current.equals(target)) {
            // Don't need to add it to the list, it's already there.
            return true;
        }

        return tryDirection(current.down(), target, path, visited, maze)
                || tryDirection(current.up(), target, path, visited, maze)
                || tryDirection(current.left(), target, path, visited, maze)
                || tryDirection(current.right(), target, path, visited, maze);
    }

    private boolean tryDirection(Cell cell, Cell target, LinkedList<Cell> path, Set<Cell> visited, Maze maze) {
        if (maze.canTraverse(cell) && !visited.contains(cell)) {
            visited.add(cell);
            path.addLast(cell);

            if (dfs(cell, target, path, visited, maze)) {
                return true;
            }

            path.removeLast();
        }

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

