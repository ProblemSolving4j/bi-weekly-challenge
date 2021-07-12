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
        var path = new ArrayList<Cell>();
        return traverse(maze, path, new HashSet<>(), Direction.NONE, maze.getEntry())
                ? Optional.of(path)
                : Optional.empty();
    }

    private boolean traverse(Maze maze, List<Cell> path, Set<Cell> visited, Direction direction, Cell cell) {
        if (cell.equals(maze.getExit())) {
            path.add(cell);
            return true;
        }
        if (!maze.canTraverse(cell) || !visited.add(cell)) return false;

        path.add(cell);

        // @formatter:off recursive dfs
        boolean found = direction != Direction.LEFT  && traverse( maze, path, visited, Direction.RIGHT, cell.right() )
                     || direction != Direction.UP    && traverse( maze, path, visited, Direction.DOWN , cell.down()  )
                     || direction != Direction.RIGHT && traverse( maze, path, visited, Direction.LEFT , cell.left()  )
                     || direction != Direction.DOWN  && traverse( maze, path, visited, Direction.UP   , cell.up()    );
        // @formatter:on

        if (found) return true;
        // if the path was not found - backtrack
        path.remove(path.size() - 1);
        return false;
    }

    private enum Direction {
        UP,
        DOWN,
        LEFT,
        RIGHT,
        NONE
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

