package com.hunsley.cache.repository;

import com.hunsley.cache.model.Cacheable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CacheableRepository extends CrudRepository<Cacheable, Long> {



}
