package com.coffee.shop.dao.repository;

import com.coffee.shop.dao.entity.Configuration;
import org.springframework.data.jpa.repository.Query;

public interface ConfigurationRepository extends BaseRepository<Configuration> {

    @Query(value = "SELECT c FROM Configuration c order by c.createdTime")
    Configuration findLast();

}
