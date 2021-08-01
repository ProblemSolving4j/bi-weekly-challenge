package com.softserveinc.ps4j.challenge.round004;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * Design a data structure that will implement {@link ChronoStore}.
 */
class ChronoStoreProblem {

    <K, V> ChronoStore<K, V> solve() {
        return new ChronoStoreProblemImpl<>();
    }

    private static class ChronoStoreProblemImpl<K, V> implements ChronoStore<K, V> {
        private final Map<K, TreeMap<Long, V>> map;

        public ChronoStoreProblemImpl() {
            this.map = new HashMap<>();
        }

        @Override
        public void set(K key, V value, long timestamp) {
            if (!map.containsKey(key)) {
                map.put(key, new TreeMap<>());
            }
            TreeMap<Long, V> timeMap = map.get(key);
            timeMap.put(timestamp, value);
        }

        @Override
        public Entry<V> getLatestBeforeOrEqual(K key, long timestamp) {
            TreeMap<Long, V> timeMap = map.get(key);
            if (timeMap == null) {
                return null;
            }
            Long prevTime = timeMap.floorKey(timestamp);
            if (prevTime == null) {
                return null;
            }
            return new Entry<>(timeMap.get(prevTime), prevTime);
        }

        @Override
        public Entry<V> getEarliestAfterOrEqual(K key, long timestamp) {
            TreeMap<Long, V> timeMap = map.get(key);
            if (timeMap == null) {
                return null;
            }
            Long nextTime = timeMap.ceilingKey(timestamp);
            if (nextTime == null) {
                return null;
            }
            return new Entry<>(timeMap.get(nextTime), nextTime);
        }
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