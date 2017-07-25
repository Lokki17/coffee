package com.coffee.shop.api.converter;

import com.coffee.shop.dao.entity.CoffeeCup;
import com.coffee.shop.service.CoffeeCupService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;


@Component
@RequiredArgsConstructor(onConstructor = @__({@Autowired}))
public class IdStringToCoffeeCupConverter extends AbstractIdStringToObjectConverter<CoffeeCup> {

    @NotNull
    private final CoffeeCupService service;

    @Override
    public CoffeeCup convert(String id) {
        return convert(id, service::findById);
    }
}
