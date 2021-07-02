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
        Optional<List<Cell>> path = Optional.of(new ArrayList<>());
        List<Cell> visited = new ArrayList<>();
        if (solveUtil(maze.getEntry(), maze, path, visited)) return path;
        return Optional.empty();
    }

    private boolean solveUtil(Cell from, Maze maze, Optional<List<Cell>> path, List<Cell> visited) {
        if (!maze.canTraverse(from) | visited.contains(from)) return false;
        path.get().add(from);
        visited.add(from);
        if (from.equals(maze.getExit())) return true;
        if (solveUtil(from.up(), maze, path, visited)) return true;
        if (solveUtil(from.down(), maze, path, visited)) return true;
        if (solveUtil(from.left(), maze, path, visited)) return true;
        if (solveUtil(from.right(), maze, path, visited)) return true;
        path.get().remove(from);
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

