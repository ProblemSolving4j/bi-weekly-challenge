package com.softserveinc.ps4j.challenge.round004;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Design a data structure that will implement {@link LFUCache}.
 */
class LFUCacheProblem {

    <K, V> LFUCache<K, V> solve(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("LFU cache capacity should be positive, got " + capacity);
        }
        return new LFUCacheImpl<>(capacity);
    }

    private static final class LFUCacheImpl<K, V> implements LFUCache<K, V> {

        final int capacity;

        final Map<K, Node<K, V>> cache;

        final List<LinkedNodeSequence<K, V>> nodesByUsages;

        int minFrequency;

        public LFUCacheImpl(int capacity) {
            this.capacity = capacity;
            this.cache = new HashMap<>(capacity);
            this.nodesByUsages = new ArrayList<>();
        }

        @Override
        public V get(K key) {
            var node = cache.get(key);
            if (node == null) return null;
            use(node);
            return node.value;
        }

        @Override
        public V put(K key, V value) {
            Node<K, V> node = cache.computeIfAbsent(key, Node::new);
            V oldValue;
            if (node.init()) {
                if (cache.size() > capacity) {
                    evict();
                }
                minFrequency = 0;
                getByUsages(0).append(node);
                oldValue = null;
            } else {
                use(node);
                oldValue = node.value;
            }
            node.value = value;
            return oldValue;
        }

        @Override
        public int capacity() {
            return capacity;
        }

        @Override
        public int size() {
            return cache.size();
        }

        void use(Node<K, V> node) {
            var list = getByUsages(node.usages);
            list.remove(node);
            if (node.usages == minFrequency && list.isEmpty()) {
                minFrequency++;
            }
            list = getByUsages(++node.usages);
            list.append(node);
        }

        void evict() {
            var list = nodesByUsages.get(minFrequency);
            var head = list.poll();
            if (list.isEmpty()) {
                minFrequency++;
            }
            //noinspection ConstantConditions
            cache.remove(head.key);
        }

        LinkedNodeSequence<K, V> getByUsages(int usages) {
            LinkedNodeSequence<K, V> list;
            if (usages == nodesByUsages.size()) {
                list = new LinkedNodeSequence<>();
                nodesByUsages.add(list);
            } else {
                list = nodesByUsages.get(usages);
            }
            return list;
        }

        static final class LinkedNodeSequence<K, V> {

            Node<K, V> head;

            Node<K, V> tail;

            void remove(Node<K, V> node) {
                var next = node.next;
                var prev = node.prev;
                if (node == head) {
                    head = next;
                } else {
                    prev.next = next;
                }
                if (node == tail) {
                    tail = prev;
                } else {
                    next.prev = prev;
                }
                node.prev = null;
                node.next = null;
            }

            Node<K, V> poll() {
                if (isEmpty()) return null;
                var node = head;
                if (head == tail) {
                    head = null;
                    tail = null;
                } else {
                    head = head.next;
                }
                return node;
            }

            void append(Node<K, V> node) {
                if (isEmpty()) {
                    head = node;
                } else {
                    node.prev = tail;
                    tail.next = node;
                }
                tail = node;
            }

            boolean isEmpty() {
                return head == null;
            }
        }

        static final class Node<K, V> {

            final K key;

            V value;

            int usages = -1;

            Node<K, V> next;

            Node<K, V> prev;

            Node(K key) {
                this.key = key;
            }

            boolean init() {
                if (usages >= 0) return false;
                usages = 0;
                return true;
            }
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
