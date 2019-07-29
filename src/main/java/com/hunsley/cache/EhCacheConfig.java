package com.hunsley.cache;

import net.sf.ehcache.config.CacheConfiguration;
import org.springframework.cache.CacheManager;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.cache.interceptor.SimpleKeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("ehcache")
public class EhCacheConfig {

  @Bean("customKeyGenerator")
  public KeyGenerator keyGenerator() {
    return new SimpleKeyGenerator();
  }

  @Bean
  public CacheManager cacheManager() {
    return new EhCacheCacheManager(ehCacheManager());
  }

  private net.sf.ehcache.CacheManager ehCacheManager() {
    CacheConfiguration cacheConfiguration = new CacheConfiguration();
    cacheConfiguration.setName("mycache");
    cacheConfiguration.setMemoryStoreEvictionPolicy("LRU");
    cacheConfiguration.setMaxEntriesLocalHeap(1000);
    cacheConfiguration.setTimeToLiveSeconds(30);
    cacheConfiguration.setTimeToIdleSeconds(20);

    net.sf.ehcache.config.Configuration config = new net.sf.ehcache.config.Configuration();
    config.addCache(cacheConfiguration);

    return net.sf.ehcache.CacheManager.newInstance(config);
  }

}
