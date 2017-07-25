package com.coffee.shop.service.impl;

import com.coffee.shop.dao.entity.Configuration;
import com.coffee.shop.dao.repository.ConfigurationRepository;
import com.coffee.shop.service.ConfigurationService;
import javaslang.control.Option;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author Lokki17
 * @since 22.07.2017
 */
@Service
@RequiredArgsConstructor(onConstructor = @__({@Autowired}))
public class ConfigurationServiceImpl implements ConfigurationService {

    @NotNull
    private final ConfigurationRepository repository;

    @Override
    public Option<Configuration> findById(Long id) {
        return Option.of(repository.findOne(id));
    }

    @Override
    public List<Configuration> findAll() {
        return repository.findAll();
    }

    @Override
    public Configuration create(Configuration newEntity) {
        return repository.save(newEntity);
    }

    @Override
    public Option<Configuration> getLastConfig() {
        return Option.of(repository.findLast());
    }
}
