package ru.clevertec.cache;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.clevertec.cache.entity.impl.LRUCache;

import static org.assertj.core.api.Assertions.assertThat;

class LRUCacheTest {

    private LRUCache<Integer, String> lruCache;

    @BeforeEach
    void setUp() {
        lruCache = new LRUCache<>(3);
    }

    @Test
    void shouldReturnValueWhenKeyExists() {
        lruCache.put(1, "One");
        assertThat(lruCache.get(1)).isEqualTo("One");
    }

    @Test
    void shouldReturnNullWhenKeyDoesNotExist() {
        assertThat(lruCache.get(1)).isNull();
    }

    @Test
    void shouldRemoveOldestValueWhenExceedingCapacity() {
        lruCache.put(1, "One");
        lruCache.put(2, "Two");
        lruCache.put(3, "Three");
        lruCache.put(4, "Four");
        assertThat(lruCache.get(1)).isNull();
    }

    @Test
    void shouldRemoveByKey() {
        lruCache.put(1, "One");
        lruCache.put(2, "Two");
        assertThat(lruCache.removeByKey(1)).isEqualTo("One");
        assertThat(lruCache.get(1)).isNull();
    }

    @Test
    void shouldNotRemoveNonexistentKey() {
        lruCache.put(1, "One");
        lruCache.put(2, "Two");
        assertThat(lruCache.removeByKey(3)).isNull();
    }

    @Test
    void shouldNotExceedCapacityWhenPuttingExistingKey() {
        lruCache.put(1, "One");
        lruCache.put(2, "Two");
        lruCache.put(3, "Three");
        lruCache.put(2, "NewTwo");
        assertThat(lruCache.get(1)).isEqualTo("One");
    }
}
