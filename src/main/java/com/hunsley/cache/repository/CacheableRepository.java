package com.hunsley.cache.repository;

import com.hunsley.cache.model.CacheableBean;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CacheableRepository extends CrudRepository<CacheableBean, Long> {

  List<CacheableBean> findByNameContaining(@Param("name") String name);

}
