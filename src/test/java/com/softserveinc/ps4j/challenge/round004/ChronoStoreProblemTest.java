package com.softserveinc.ps4j.challenge.round004;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ChronoStoreProblemTest {

    private ChronoStoreProblem problem;

    @BeforeEach
    void setUp() {
        problem = new ChronoStoreProblem();
    }

    @Test
    @DisplayName("validate ChronoStore implementation")
    void testSolution() {
        ChronoStore<String, String> store = assertDoesNotThrow(() -> problem.solve());

        store.set("k1", "k1v1", 0);

        var k1e1 = new ChronoStore.Entry<>("k1v1", 0);

        assertEquals(k1e1, store.getLatestBeforeOrEqual("k1", 1));
        assertEquals(k1e1, store.getLatestBeforeOrEqual("k1", 0));
        assertEquals(k1e1, store.getEarliestAfterOrEqual("k1", -1));
        assertEquals(k1e1, store.getEarliestAfterOrEqual("k1", 0));
        assertNull(store.getEarliestAfterOrEqual("k2", 0));
        assertNull(store.getLatestBeforeOrEqual("k2", 0));
        assertNull(store.getEarliestAfterOrEqual("k1", 1));
        assertNull(store.getLatestBeforeOrEqual("k1", -1));

        store.set("k1", "k1v2", 2);
        store.set("k2", "k2v1", 2);

        var k1e2 = new ChronoStore.Entry<>("k1v2", 2);

        assertEquals(k1e1, store.getEarliestAfterOrEqual("k1", -1));
        assertEquals(k1e1, store.getEarliestAfterOrEqual("k1", 0));
        assertEquals(k1e2, store.getEarliestAfterOrEqual("k1", 1));
        assertEquals(k1e2, store.getEarliestAfterOrEqual("k1", 2));
        assertNull(store.getEarliestAfterOrEqual("k1", 3));

        assertEquals(k1e2, store.getLatestBeforeOrEqual("k1", 3));
        assertEquals(k1e2, store.getLatestBeforeOrEqual("k1", 2));
        assertEquals(k1e1, store.getLatestBeforeOrEqual("k1", 1));
        assertEquals(k1e1, store.getLatestBeforeOrEqual("k1", 0));
        assertNull(store.getLatestBeforeOrEqual("k1", -1));

        var k2e1 = new ChronoStore.Entry<>("k2v1", 2);
        assertEquals(k2e1, store.getEarliestAfterOrEqual("k2", 1));
        assertEquals(k2e1, store.getEarliestAfterOrEqual("k2", 2));
        assertNull(store.getEarliestAfterOrEqual("k2", 3));
        assertEquals(k2e1, store.getLatestBeforeOrEqual("k2", 3));
        assertEquals(k2e1, store.getLatestBeforeOrEqual("k2", 2));
        assertNull(store.getLatestBeforeOrEqual("k2", 1));

        store.set("k2", "k2v1+", 2);

        var k2e1Plus = new ChronoStore.Entry<>("k2v1+", 2);
        assertEquals(k2e1Plus, store.getEarliestAfterOrEqual("k2", 1));
        assertEquals(k2e1Plus, store.getEarliestAfterOrEqual("k2", 2));
        assertNull(store.getEarliestAfterOrEqual("k2", 3));
        assertEquals(k2e1Plus, store.getLatestBeforeOrEqual("k2", 3));
        assertEquals(k2e1Plus, store.getLatestBeforeOrEqual("k2", 2));
        assertNull(store.getLatestBeforeOrEqual("k2", 1));
    }

}