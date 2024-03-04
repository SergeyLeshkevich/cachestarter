package ru.clevertec.cache.entity;

public interface CacheFactory<K, V> {

    Cache<K, V> createCache();
}
