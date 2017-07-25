package com.coffee.shop.api.converter;

import com.coffee.shop.dao.entity.Configuration;
import com.coffee.shop.service.ConfigurationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;


@Component
@RequiredArgsConstructor(onConstructor = @__({@Autowired}))
public class IdStringToConfigurationConverter extends AbstractIdStringToObjectConverter<Configuration> {

    @NotNull
    private final ConfigurationService service;

    @Override
    public Configuration convert(String id) {
        return convert(id, service::findById);
    }
}
