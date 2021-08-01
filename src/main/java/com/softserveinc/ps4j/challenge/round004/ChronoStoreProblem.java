package com.softserveinc.ps4j.challenge.round004;

/**
 * Design a data structure that will implement {@link ChronoStore}.
 */
class ChronoStoreProblem {

    <K, V> ChronoStore<K, V> solve() {
        throw new UnsupportedOperationException("not yet implemented");
    }

}

/**
 * A key-value store that allows for one key to be mapped to several values marked by
 * different timestamps.
 *
 * @param <K> the key type
 * @param <V> the value type
 */
interface ChronoStore<K, V> {

    /**
     * Maps a key to a value with a given timestamp.
     * Several values can be mapped to one key if they have different timestamps,
     * otherwise the mapping would be replaced.
     *
     * @param key       the key of the association
     * @param value     the value of the association
     * @param timestamp the timestamp of the mapping
     */
    void set(K key, V value, long timestamp);

    /**
     * Returns the first entry for the given key that has a timestamp <= the given timestamp.
     *
     * @param key       the key of the association.
     * @param timestamp the timestamp of the lookup.
     * @return the closest entry at or before the given timestamp.
     */
    Entry<V> getLatestBeforeOrEqual(K key, long timestamp);

    /**
     * Returns the first entry for the given key that has a timestamp >= the given timestamp.
     *
     * @param key       the key of the association.
     * @param timestamp the timestamp of the lookup.
     * @return the closest entry at or after the given timestamp.
     */
    Entry<V> getEarliestAfterOrEqual(K key, long timestamp);

    /**
     * The tuple consisting of a generic value and a timestamp.
     *
     * @param <T> the type of the value.
     */
    record Entry<T>(T value, long timestamp) {
    }

}