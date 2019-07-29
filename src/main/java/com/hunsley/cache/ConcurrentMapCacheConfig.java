package com.hunsley.cache;

import org.springframework.cache.CacheManager;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("map")
public class ConcurrentMapCacheConfig {

  @Bean
  public CacheManager cacheManager() {
    return new ConcurrentMapCacheManager("mycache");
  }
}
