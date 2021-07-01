package com.softserveinc.ps4j.challenge.round002;

import java.util.*;

/**
 * Given a graph {@link Node}, return all of its connections grouped by the connection level.
 *
 * <p>For example, for a graph (1)<-->(2)<-->(3),
 * given the node (1), return [[2],[3]]:
 * (1) connects with (2) on level 0, and with (3) on level (1)
 *
 * <p>All connections are bi-directional
 * <p>Modifications to the graph are forbidden
 */
class EstablishConnectionsProblem {

    record Entry(Node node, int level) {
    }

    // Basic BFS
    List<List<Node>> solve(Node from) {
        List<List<Node>> connections = new ArrayList<>();

        Set<Node> visited = new HashSet<>();
        Queue<Entry> queue = new LinkedList<>();

        visited.add(from);

        for (Node sibling : from.getConnections()) {
            visited.add(sibling);
            queue.add(new Entry(sibling, 1));
        }

        int currentLevel = 1;
        List<Node> currentLevelNodes = new ArrayList<>();

        while (!queue.isEmpty()) {
            Entry e = queue.poll();

            if (e.level > currentLevel) {
                connections.add(currentLevelNodes);

                currentLevelNodes = new ArrayList<>();
                currentLevel++;
            }

            currentLevelNodes.add(e.node);

            for (Node sibling : e.node.getConnections()) {
                if (!visited.contains(sibling)) {
                    visited.add(sibling);
                    queue.add(new Entry(sibling, e.level + 1));
                }
            }
        }

        // May be empty is a node has no connections at all.
        if (!currentLevelNodes.isEmpty()) {
            connections.add(currentLevelNodes);
        }

        return connections;
    }

}

final class Node {

    private final int id;

    private final Set<Node> connections;

    Node(int id) {
        this.id = id;
        this.connections = new LinkedHashSet<>();
    }

    void connect(Node node) {
        this.connections.add(node);
        node.connections.add(this);
    }

    int getId() {
        return id;
    }

    Set<Node> getConnections() {
        return Collections.unmodifiableSet(connections);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Node node = (Node) o;
        return id == node.id;
    }

    @Override
    public int hashCode() {
        return id;
    }

    @Override
    public String toString() {
        return "Node{" +
                "id=" + id +
                '}';
    }
}
