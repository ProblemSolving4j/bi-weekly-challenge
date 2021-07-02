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
        List<List<Node>> connectionsByLevels = new ArrayList<>();
        solveUtil(from, connectionsByLevels, 0, new ArrayList<>(Arrays.asList(from)));
        return connectionsByLevels;
    }


    private void solveUtil(Node from, List<List<Node>> connectionsByLevels, int currentLevel, List<Node> visited) {
        if (from.getConnections().size() == 0 || visited.containsAll(from.getConnections())) return;
        if (connectionsByLevels.size() <= currentLevel) connectionsByLevels.add(new ArrayList<>());
        List<Node> toVisit = new ArrayList<>();
        from.getConnections().stream().filter((n) -> !visited.contains(n)).forEach((n) -> toVisit.add(n));
        from.getConnections().stream().filter((n) -> !visited.contains(n)).forEach((n) -> {
            connectionsByLevels.get(currentLevel).add(n);
            visited.add(n);
        });
        toVisit.stream().forEach((n) -> solveUtil(n, connectionsByLevels, currentLevel + 1, visited));
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
