package com.hunsley.cache.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import com.hunsley.cache.model.CacheableBean;
import com.hunsley.cache.model.CacheableException;
import com.hunsley.cache.repository.CacheableRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Testing cache and expire functions with an ehcache backed cache manager
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("ehcache")
public class EHCacheableServiceTest {

  @Autowired
  private CacheableService cacheableService;

  @Autowired
  private CacheableRepository cacheableRepository;

  @Before
  public void populateData() {
    cacheableRepository.save(new CacheableBean("a","a"));
  }

  /**
   * With the {@link Cacheable} annotation on the
   * service's getCacheable(...) method should only be entered once, even though it is
   * called 3 times.
   *
   * Expect only ever get on call made to the method when we wait less than the
   * TTI & TTL which is currently configured to 20s and 30s.
;   */
  @Test
  public void testCache() throws CacheableException, InterruptedException {
    cacheableService.resetCall();


    for (CacheableBean cacheableBean : cacheableRepository.findAll()) {
      final long id = cacheableBean.getId();
      CacheableBean bean = cacheableService.getCachable(id);
      assertNotNull(bean);
    }

    assertEquals(1, cacheableService.getCall());

    Thread.sleep(19000);

    for (CacheableBean cacheableBean : cacheableRepository.findAll()) {
      final long id = cacheableBean.getId();
      cacheableService.getCachable(id);
    }

    assertEquals(1, cacheableService.getCall());

  }

  /**
   * Wait over TTI & TTL times and expect a call to the method
   */
  @Test
  public void testCacheExpire() throws CacheableException, InterruptedException {
    cacheableService.resetCall();

    for (CacheableBean cacheableBean : cacheableRepository.findAll()) {
      final long id = cacheableBean.getId();
      CacheableBean bean = cacheableService.getCachable(id);
      assertNotNull(bean);
    }

    assertEquals(1, cacheableService.getCall());

    Thread.sleep(31000);

    for (CacheableBean cacheableBean : cacheableRepository.findAll()) {
      final long id = cacheableBean.getId();
      cacheableService.getCachable(id);
    }

    assertEquals(2, cacheableService.getCall());
  }
}
