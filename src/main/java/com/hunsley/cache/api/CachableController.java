package com.hunsley.cache.api;

import com.hunsley.cache.model.Cacheable;
import com.hunsley.cache.model.CacheableException;
import com.hunsley.cache.service.CacheableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CachableController {

  @Autowired
  private CacheableService cacheableService;

  @RequestMapping(path = "cachable/{id}", method = RequestMethod.GET)
  public Cacheable getCachable(@PathVariable(name = "id") long id) throws CacheableException {
    return cacheableService.getCachable(id);
  }
}
