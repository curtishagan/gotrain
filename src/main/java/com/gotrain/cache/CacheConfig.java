package com.gotrain.cache;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableCaching
public class CacheConfig {

    // On a long-term project, I would certainly look to make the
    // caching system much more robust.
    @Bean
    public CacheManager cacheManager() {
        return new ConcurrentMapCacheManager("timetableEntries");
    }
}
