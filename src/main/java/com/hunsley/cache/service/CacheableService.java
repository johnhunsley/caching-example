package com.hunsley.cache.service;

import com.hunsley.cache.model.Cacheable;
import com.hunsley.cache.model.CacheableException;
import com.hunsley.cache.repository.CacheableRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CacheableService {

  @Autowired
  private CacheableRepository cacheableRepository;


  @org.springframework.cache.annotation.Cacheable
  public Cacheable getCachable(Long id) throws CacheableException {

    Optional<Cacheable> result = cacheableRepository.findById(id);

    if(result.isPresent()) {
      return result.get();
    }

    throw new CacheableException("No Cacheable Object in the data store with id " + id);
  }
}
