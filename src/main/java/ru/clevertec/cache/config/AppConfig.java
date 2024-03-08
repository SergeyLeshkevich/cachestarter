package ru.clevertec.cache.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.clevertec.cache.entity.CacheFactory;
import ru.clevertec.cache.entity.impl.CacheFactoryImpl;

@Configuration
@EnableConfigurationProperties(AppConfig.class)
public class AppConfig {

    @Bean
    @ConfigurationProperties("cache")
    public <K,V> CacheFactory<K,V> getCacheFactory(){
        return new CacheFactoryImpl<>();
    }
}
