package com.hunsley.cache.service;

import static org.junit.Assert.assertEquals;

import com.hunsley.cache.model.CacheableBean;
import com.hunsley.cache.model.CacheableException;
import com.hunsley.cache.repository.CacheableRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * test the basic cacheable function
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("map")
public class MapCacheableServiceTest {

  @Autowired
  private CacheableService cacheableService;

  @Autowired
  private CacheableRepository cacheableRepository;

  @Test
  public void testCache() throws CacheableException {
    cacheableRepository.save(new CacheableBean("a","a"));
    cacheableRepository.save(new CacheableBean("b","b"));
    cacheableRepository.save(new CacheableBean("c","c"));

    for (CacheableBean cacheableBean : cacheableRepository.findAll()) {
      final long id = cacheableBean.getId();
      cacheableService.getCachable(id);
      cacheableService.getCachable(id);
      cacheableService.getCachable(id);
    }

    assertEquals(3, cacheableService.getCall());

  }

}
