package com.hunsley.cache.service;

import static org.junit.Assert.assertEquals;

import com.hunsley.cache.model.Cacheable;
import com.hunsley.cache.model.CacheableException;
import com.hunsley.cache.repository.CacheableRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CacheableServiceTest {

  @Autowired
  private CacheableService cacheableService;

  @Autowired
  private CacheableRepository cacheableRepository;

  @Test
  public void testCache() throws CacheableException {
    cacheableRepository.save(new Cacheable("a","a"));
    cacheableRepository.save(new Cacheable("b","b"));
    cacheableRepository.save(new Cacheable("c","c"));

    for (Cacheable cacheable : cacheableRepository.findAll()) {
      final long id = cacheable.getId();
      cacheableService.getCachable(id);
      cacheableService.getCachable(id);
      cacheableService.getCachable(id);
    }

    assertEquals(3, cacheableService.getCall());

  }

}
