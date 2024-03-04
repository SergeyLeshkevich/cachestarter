package ru.clevertec.cache.entity.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import ru.clevertec.cache.entity.Cache;
import ru.clevertec.cache.entity.CacheFactory;

@Slf4j
public class CacheFactoryImpl<K, V> implements CacheFactory<K, V> {

    public static final String CAPACITY_KEY_DEFAULT = "5";
    public static final String ALGORITHM_KEY_DEFAULT = "LFU";

    @Value("${cache.capacity:#{null}}")
    private String capacityKey;

    @Value("${cache.algorithm:#{null}}")
    private String algorithmKey;

    public CacheFactoryImpl() {
        if (capacityKey == null) {
            log.info("Capacity is not specified. The default value is 5");
            capacityKey = CAPACITY_KEY_DEFAULT;
        }

        if (algorithmKey == null) {
            algorithmKey = ALGORITHM_KEY_DEFAULT;
            log.info("AlgorithmKey is not specified. The default value is LFU");
        }
    }

    /**
     * Gets a cache object depending on the settings in application.yaml
     *
     * @return Cache
     */
    @Override
    public Cache<K, V> createCache() {

        return "LFU".equals(algorithmKey)
                ? new LFUCache<>(Integer.parseInt(capacityKey))
                : new LRUCache<>(Integer.parseInt(capacityKey));
    }
}
