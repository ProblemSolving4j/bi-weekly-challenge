package com.softserveinc.ps4j.challenge.round004;

import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;

/**
 * Design a data structure that will implement {@link LFUCache}.
 */
class LFUCacheProblem {

    <K, V> LFUCache<K, V> solve(int capacity) {
        {
            if (capacity <= 0) {
                throw new IllegalArgumentException("LFU cache capacity should be positive, got " + capacity);
            }
            return new LFUCacheProblemImpl<>(capacity);
        }
    }

    private static class LFUCacheProblemImpl<K, V> implements LFUCache<K, V> {

        private final int capacity;
        private final HashMap<K, Entry<V>> valMap;
        private int minFrequency;
        private final HashMap<Integer, Deque<K>> frequencyMap;

        public LFUCacheProblemImpl(int capacity) {
            this.capacity = capacity;
            this.valMap = new HashMap<>();
            this.frequencyMap = new HashMap<>();
            this.minFrequency = 0;
        }

        @Override
        public V get(K key) {
            if (!valMap.containsKey(key)) {
                return null;
            }
            Entry<V> entry = valMap.get(key);
            updateFrequencyMap(key, entry.frequency);
            valMap.put(key, new Entry<>(entry.value, entry.frequency + 1));
            return entry.value;
        }

        @Override
        public V put(K key, V value) {
            if (!valMap.containsKey(key)) {
                if (valMap.size() >= capacity) {
                    Deque<K> keysWithMinFrequency = frequencyMap.get(minFrequency);
                    K evict = keysWithMinFrequency.pollFirst();
                    valMap.remove(evict);
                    if (keysWithMinFrequency.size() == 0) {
                        frequencyMap.remove(minFrequency);
                    }
                }
                valMap.put(key, new Entry<>(value, 1));
                addToFrequencyMap(key, 1);
                minFrequency = 1;
                return null;
            } else {
                Entry<V> prevEntry = valMap.get(key);
                valMap.put(key, new Entry<>(value, prevEntry.frequency() + 1));
                return prevEntry.value();
            }
        }

        @Override
        public int capacity() {
            return capacity;
        }

        @Override
        public int size() {
            return valMap.size();
        }

        /**
         * The tuple consisting of a generic value and a frequency.
         *
         * @param <V> the type of the value.
         */
        record Entry<V>(V value, int frequency) {
        }

        private void updateFrequencyMap(K key, int freq) {
            Deque<K> keys = frequencyMap.get(freq);
            if (keys.size() == 1) {
                frequencyMap.remove(freq);
                if (minFrequency == freq) {
                    minFrequency++;
                }
            } else {
                keys.remove(key);
            }
            addToFrequencyMap(key, freq + 1);
        }

        private void addToFrequencyMap(K key, int freq) {
            if (!frequencyMap.containsKey(freq)) {
                frequencyMap.put(freq, new LinkedList<>());
            }
            Deque<K> keys = frequencyMap.get(freq);
            keys.addLast(key);
            frequencyMap.put(freq, keys);
        }

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
