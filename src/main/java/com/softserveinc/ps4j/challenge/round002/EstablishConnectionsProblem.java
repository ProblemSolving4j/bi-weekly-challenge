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

    List<List<Node>> solve(Node from) {
        Set<Node> level0 = from.getConnections();
        if (level0.isEmpty()) {
            return Collections.emptyList();
        }

        var distinctNodes = new HashSet<Node>();
        distinctNodes.add(from);
        distinctNodes.addAll(level0);

        var connectionsByLevel = new ArrayList<List<Node>>();

        var nextLevel = new ArrayList<>(level0);

        do {
            connectionsByLevel.add(nextLevel);

            var currentLevel = new ArrayList<Node>();

            for (Node node : nextLevel) {
                for (Node connection : node.getConnections()) {
                    if (distinctNodes.add(connection)) {
                        currentLevel.add(connection);
                    }
                }
            }
            nextLevel = currentLevel;

        } while (!nextLevel.isEmpty());

        return connectionsByLevel;
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
