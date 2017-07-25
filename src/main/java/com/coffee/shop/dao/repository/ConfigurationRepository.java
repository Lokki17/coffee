package com.coffee.shop.dao.repository;

import com.coffee.shop.dao.entity.Configuration;
import org.springframework.data.jpa.repository.Query;

/**
 * @author Lokki17
 * @since 22.07.2017
 */
public interface ConfigurationRepository extends BaseRepository<Configuration> {

    @Query(value = "SELECT c FROM Configuration c order by c.createdTime")
    Configuration findLast();

}
