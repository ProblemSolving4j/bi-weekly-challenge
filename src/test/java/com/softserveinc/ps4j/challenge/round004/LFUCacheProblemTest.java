package com.softserveinc.ps4j.challenge.round004;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LFUCacheProblemTest {

    private LFUCacheProblem problem;

    @BeforeEach
    void setUp() {
        problem = new LFUCacheProblem();
    }

    @Test
    @DisplayName("LFU cache should have positive capacity")
    void testInvalidCapacity() {
        assertAll(
                () -> assertThrows(IllegalArgumentException.class, () -> problem.solve(0)),
                () -> assertThrows(IllegalArgumentException.class, () -> problem.solve(-1)),
                () -> assertThrows(IllegalArgumentException.class, () -> problem.solve(Integer.MIN_VALUE))
        );
    }

    @Test
    @DisplayName("validate LFUCache implementation")
    void testSolution() {

        LFUCache<String, String> lfu = assertDoesNotThrow(() -> problem.solve(5));

        assertEquals(5, lfu.capacity());

        assertEquals(0, lfu.size());
        assertNull(lfu.put("k1", "v1"));
        assertEquals(1, lfu.size());
        assertNull(lfu.put("k2", "v2"));
        assertEquals(2, lfu.size());
        assertNull(lfu.put("k3", "v3"));
        assertEquals(3, lfu.size());
        assertNull(lfu.put("k4", "v4"));
        assertEquals(4, lfu.size());
        assertNull(lfu.put("k5", "v5"));
        assertEquals(5, lfu.size());

        assertNull(lfu.get("k"));
        //used once
        assertEquals("v1", lfu.get("k1"));
        assertEquals("v2", lfu.get("k2"));
        assertEquals("v3", lfu.get("k3"));
        assertEquals("v4", lfu.get("k4"));
        assertEquals("v5", lfu.get("k5"));
        //used twice
        assertEquals("v2", lfu.get("k2"));
        assertEquals("v3", lfu.get("k3"));
        assertEquals("v4", lfu.get("k4"));
        assertEquals("v5", lfu.get("k5"));
        //used thrice & replaced
        assertEquals("v5", lfu.put("k5", "v5+"));
        assertEquals(5, lfu.size());

        assertNull(lfu.put("k6", "v6"));
        assertEquals(5, lfu.size());
        assertNull(lfu.get("k1"));
        assertEquals("v6", lfu.get("k6"));
        assertEquals("v6", lfu.get("k6"));

        assertNull(lfu.put("k7", "v7"));
        assertEquals("v7", lfu.get("k7"));
        assertEquals("v7", lfu.get("k7"));
        assertNull(lfu.put("k8", "v8"));
        assertEquals("v8", lfu.get("k8"));
        assertEquals("v8", lfu.get("k8"));
        assertNull(lfu.put("k9", "v9"));
        assertEquals("v9", lfu.get("k9"));
        assertEquals("v9", lfu.get("k9"));
        assertEquals(5, lfu.size());
        assertNull(lfu.get("k2"));
        assertNull(lfu.get("k3"));
        assertNull(lfu.get("k4"));

        assertEquals("v5+", lfu.get("k5"));
        assertEquals("v6", lfu.get("k6"));
        assertEquals("v7", lfu.get("k7"));
        assertEquals("v8", lfu.get("k8"));
        assertEquals("v9", lfu.get("k9"));

        assertEquals(5, lfu.capacity());
    }
}