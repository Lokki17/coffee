package com.coffee.shop.service;

import com.coffee.shop.dao.entity.Configuration;
import javaslang.control.Option;

import java.util.List;

/**
 * @author Lokki17
 * @since 22.07.2017
 */
public interface ConfigurationService {

    Option<Configuration> findById(Long id);

    List<Configuration> findAll();

    Configuration create(Configuration newEntity);

    Configuration update(Configuration updatedEntity);

    void delete(Configuration entity);

    Option<Configuration> getLastConfig();

}
