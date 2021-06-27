package com.softserveinc.ps4j.challenge.round002;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

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
        Set<Integer> visited = new HashSet<>();
        Map<Node, Integer> levels = new HashMap();
        LinkedList<Node> queue = new LinkedList<>();

        visited.add(from.getId());
        queue.add(from);

        while (queue.size() != 0) {
            // Dequeue a vertex from queue and print it
            from = queue.poll();
            for (Node connection : from.getConnections()) {
                if (!visited.contains(connection.getId())) {
                    visited.add(connection.getId());
                    queue.add(connection);
                    levels.put(connection, levels.getOrDefault(from, 0) + 1);
                }
            }
        }

        Map<Integer, List<Node>> mapByLevel = levels.keySet().stream().collect(Collectors.groupingBy(levels::get));
        return mapByLevel.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByKey())
                .map(Map.Entry::getValue)
                .collect(Collectors.toList());
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
