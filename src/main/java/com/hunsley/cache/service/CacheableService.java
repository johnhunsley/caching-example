package com.hunsley.cache.service;

import com.hunsley.cache.model.CacheableBean;
import com.hunsley.cache.model.CacheableException;
import com.hunsley.cache.repository.CacheableRepository;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class CacheableService {

  private static Logger logger = LoggerFactory.getLogger(CacheableService.class);

  @Autowired
  private CacheableRepository cacheableRepository;

  private int call = 0;


  @Cacheable(value = "mycache")
  public synchronized CacheableBean getCachable(Long id) throws CacheableException {
    call++;
    logger.info("service has been called " + call + " times");

    Optional<CacheableBean> result = cacheableRepository.findById(id);

    if(result.isPresent()) {
      return result.get();
    }

    throw new CacheableException("No Cacheable Object in the data store with id " + id);
  }

  public int getCall() {
    return call;
  }

  public void resetCall() {
    call = 0;
  }
}
