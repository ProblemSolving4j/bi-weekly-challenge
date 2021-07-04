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

    private Set<Node> visited;

    List<List<Node>> solve(Node root) {
        if (root.getConnections().isEmpty()) return Collections.emptyList();
        List<List<Node>> result = new LinkedList<>();
        visited = new HashSet<>();
        // tier - list of nodes with the same path length from the root
        List<Node> tier = new LinkedList<>(root.getConnections()); // tier 0
        visited.add(root);
        visited.addAll(tier); // track root and tier 0 nodes as already visited
        while (!tier.isEmpty()) {
            result.add(tier);
            tier = tier.stream()
                    .flatMap(node -> node.getConnections().stream()) // stream of all adjacent nodes
                    .distinct() // get rid of duplicate nodes
                    .filter(node -> visited.add(node)) // need only NOT visited nodes
                    .toList(); // build next tier
        }
        return result;
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
