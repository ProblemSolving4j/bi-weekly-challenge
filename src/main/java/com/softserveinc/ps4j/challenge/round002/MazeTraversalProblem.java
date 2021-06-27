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
        List<Cell> path = new ArrayList<>();
        Set<Cell> visited = new HashSet<>();
        boolean pathExists = solveUtil(maze, maze.getEntry(), path, visited);
        return pathExists ? Optional.of(path) : Optional.empty();
    }

    boolean solveUtil(Maze maze, Cell cell, List<Cell> path, Set<Cell> visited) {
        if (isSafe(cell, maze, visited)) {
            path.add(cell);
            visited.add(cell);
            if (cell.equals(maze.getExit()) ||
                    solveUtil(maze, cell.up(), path, visited) ||
                    solveUtil(maze, cell.down(), path, visited) ||
                    solveUtil(maze, cell.left(), path, visited) ||
                    solveUtil(maze, cell.right(), path, visited))
                return true;
            path.remove(cell);
            return false;
        }
        return false;
    }

    private boolean isSafe(Cell cell, Maze maze, Set<Cell> visited) {
        return !visited.contains(cell) && maze.canTraverse(cell);
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

