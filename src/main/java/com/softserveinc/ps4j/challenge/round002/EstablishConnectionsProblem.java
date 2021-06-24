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

        if (from.getConnections().size() == 0) return List.of();

        var connections = new ArrayDeque<List<Node>>();
        var uniqueNodes = new HashSet<Node>();
        uniqueNodes.add(from);

        var zeroLevelConnection = new ArrayList<>(from.getConnections());
        connections.add(zeroLevelConnection);
        uniqueNodes.addAll(zeroLevelConnection);

        do {
            var nextLevelConnections = new ArrayList<Node>();

            for (Node node : connections.getLast()) {

                for (Node connection : node.getConnections()) {
                    if (uniqueNodes.add(connection)) {
                        nextLevelConnections.add(connection);
                    }
                }
            }

            if (nextLevelConnections.size() == 0) break;
            connections.add(nextLevelConnections);
        } while(true);

        return new ArrayList<>(connections);
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
