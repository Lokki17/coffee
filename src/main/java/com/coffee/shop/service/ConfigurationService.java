package com.coffee.shop.service;

import com.coffee.shop.dao.entity.Configuration;
import javaslang.control.Option;

import java.util.List;

public interface ConfigurationService {

    Option<Configuration> findById(Long id);

    List<Configuration> findAll();

    Configuration create(Configuration newEntity);

    Option<Configuration> getLastConfig();

}
