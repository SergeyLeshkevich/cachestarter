package ru.clevertec.cache;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.clevertec.cache.entity.impl.LFUCache;

import static org.assertj.core.api.Assertions.assertThat;


class LFUCacheTest {

    private LFUCache<Integer, String> lfuCache;

    @BeforeEach
    void setUp() {
        lfuCache = new LFUCache<>(3);
    }

    @AfterEach
    void setDn() {
        lfuCache = null;
    }

    @Test
    void shouldReturnValueWhenKeyExists() {
        lfuCache.put(1, "One");
        assertThat(lfuCache.get(1)).isEqualTo("One");
    }

    @Test
    void shouldReturnNullWhenKeyDoesNotExist() {
        assertThat(lfuCache.get(1)).isNull();
    }

    @Test
    void shouldRemoveByKey() {
        lfuCache.put(1, "One");
        lfuCache.put(2, "Two");
        assertThat(lfuCache.removeByKey(1)).isEqualTo("One");
        assertThat(lfuCache.get(1)).isNull();
    }

    @Test
    void shouldNotRemoveNonexistentKey() {
        lfuCache.put(1, "One");
        lfuCache.put(2, "Two");
        assertThat(lfuCache.removeByKey(3)).isNull();
    }

    @Test
    void shouldNotExceedCapacityWhenPuttingExistingKey() {
        lfuCache.put(1, "One");
        lfuCache.put(2, "Two");
        lfuCache.put(3, "Three");
        lfuCache.put(2, "NewTwo");
        assertThat(lfuCache.get(1)).isEqualTo("One");
    }

}