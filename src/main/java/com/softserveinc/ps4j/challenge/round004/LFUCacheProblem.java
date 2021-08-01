package com.softserveinc.ps4j.challenge.round004;

/**
 * Design a data structure that will implement {@link LFUCache}.
 */
class LFUCacheProblem {

    <K, V> LFUCache<K, V> solve(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("LFU cache capacity should be positive, got " + capacity);
        }
        throw new UnsupportedOperationException("not yet implemented");
    }

}

/**
 * Least Frequently Used (LFU) cache is a type of cache with limited capacity,
 * where if this capacity is reached, then the least frequently used entry is getting
 * evicted to make place for a newly inserted ones.
 * Use is defined as reading or replacing a value mapped to a given key.
 *
 * @param <K> the key type
 * @param <V> the value type
 */
interface LFUCache<K, V> {

    /**
     * Retrieves the value by the key and increments its usage counter.
     *
     * @param key the key of the association.
     * @return value associated with the key or null if none is found.
     */
    V get(K key);

    /**
     * Maps a value to a given unique key, possibly replacing the existing mapping.
     * If the replacement has taken place, the usage counter is to be incremented.
     *
     * @param key   the key of the association.
     * @param value the value to be mapped to the key.
     * @return the old value associated with the key or null if none existed.
     */
    V put(K key, V value);

    /**
     * Capacity is set at construction and doesn't change.
     *
     * @return max capacity of the cache
     */
    int capacity();

    /**
     * Size of the cache represents the number of entries (key-value pairs).
     *
     * @return the size of the cache (size <= capacity)
     */
    int size();

}
