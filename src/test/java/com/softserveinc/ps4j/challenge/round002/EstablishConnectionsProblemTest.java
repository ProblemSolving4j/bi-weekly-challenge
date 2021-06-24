package com.softserveinc.ps4j.challenge.round002;

import org.junit.jupiter.api.*;

import java.time.Duration;
import java.util.List;

import static java.util.List.of;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTimeoutPreemptively;
import static org.junit.jupiter.api.DynamicTest.dynamicTest;

class EstablishConnectionsProblemTest {

    private static final Duration SOLUTION_TIME_THRESHOLD = Duration.ofSeconds(3);

    private EstablishConnectionsProblem problem;

    @BeforeEach
    void setUp() {
        problem = new EstablishConnectionsProblem();
    }

    @TestFactory
    @DisplayName("establish all the connections of the node")
    List<DynamicNode> testSolution() {
        var n = new Node[10];
        for (int i = 0; i < 10; i++) {
            n[i] = new Node(i);
        }
        // level 0
        n[0].connect(n[1]);
        n[0].connect(n[2]);
        // level 1
        n[1].connect(n[3]);
        n[1].connect(n[4]);
        n[2].connect(n[5]);
        n[2].connect(n[6]);
        // level 2
        n[6].connect(n[7]);
        // cycle to level 0
        n[7].connect(n[0]);
        // segregated graph
        n[8].connect(n[9]);

        return List.of(
                dynamicTest("connections of single disconnected node",
                        () -> connectionsShouldBe(of(), new Node(0))),

                testFor(of(
                        of(n[1], n[2], n[7]),
                        of(n[3], n[4], n[5], n[6])
                ), n[0]),

                testFor(of(
                        of(n[0], n[3], n[4]),
                        of(n[2], n[7]),
                        of(n[5], n[6])
                ), n[1]),

                testFor(of(
                        of(n[0], n[5], n[6]),
                        of(n[1], n[7]),
                        of(n[3], n[4])
                ), n[2]),

                testFor(of(
                        of(n[1]),
                        of(n[0], n[4]),
                        of(n[2], n[7]),
                        of(n[5], n[6])
                ), n[3]),

                testFor(of(
                        of(n[1]),
                        of(n[0], n[3]),
                        of(n[2], n[7]),
                        of(n[5], n[6])
                ), n[4]),

                testFor(of(
                        of(n[2]),
                        of(n[0], n[6]),
                        of(n[1], n[7]),
                        of(n[3], n[4])
                ), n[5]),

                testFor(of(
                        of(n[2], n[7]),
                        of(n[0], n[5]),
                        of(n[1]),
                        of(n[3], n[4])
                ), n[6]),

                testFor(of(
                        of(n[0], n[6]),
                        of(n[1], n[2]),
                        of(n[3], n[4], n[5])
                ), n[7]),

                testFor(of(
                        of(n[9])
                ), n[8]),

                testFor(of(
                        of(n[8])
                ), n[9]));
    }

    private DynamicTest testFor(List<List<Node>> connections, Node node) {
        return dynamicTest("connections of node " + node.getId(),
                () -> connectionsShouldBe(connections, node));
    }

    private void connectionsShouldBe(List<List<Node>> connections, Node node) {
        List<List<Node>> result = assertTimeoutPreemptively(SOLUTION_TIME_THRESHOLD, () -> problem.solve(node));
        int size = connections.size();
        assertThat(result).hasSize(size);
        for (int i = 0; i < size; i++) {
            assertThat(result.get(i)).containsExactlyInAnyOrderElementsOf(connections.get(i));
        }
    }

}