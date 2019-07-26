package com.hunsley.cache.repository;

import com.hunsley.cache.model.CacheableBean;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CacheableRepository extends CrudRepository<CacheableBean, Long> {



}
