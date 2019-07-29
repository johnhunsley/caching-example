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

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("ehcache")
public class CacheableServiceTest {

  @Autowired
  private CacheableService cacheableService;

  @Autowired
  private CacheableRepository cacheableRepository;

  /**
   * With the {@link org.springframework.cache.annotation.Cacheable} annotation on the
   * service's getCacheable(...) method should only be entered once, even though it is
   * called 3 times
   */
  @Test
  public void testCache() throws CacheableException, InterruptedException {
    cacheableRepository.save(new CacheableBean("a","a"));

    for (CacheableBean cacheableBean : cacheableRepository.findAll()) {
      final long id = cacheableBean.getId();
      cacheableService.getCachable(id);
      cacheableService.getCachable(id);
      cacheableService.getCachable(id);
    }

    assertEquals(1, cacheableService.getCall());
//
//    Thread.sleep(31000);
//
//    for (CacheableBean cacheableBean : cacheableRepository.findAll()) {
//      final long id = cacheableBean.getId();
//      cacheableService.getCachable(id);
//    }
//
//    assertEquals(2, cacheableService.getCall());

  }

}
