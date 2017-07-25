package com.coffee.shop.api.converter;

import com.coffee.shop.dao.entity.CoffeeKind;
import com.coffee.shop.service.CoffeeKindService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;


@Component
@RequiredArgsConstructor(onConstructor = @__({@Autowired}))
public class IdStringToCoffeeKindConverter extends AbstractIdStringToObjectConverter<CoffeeKind> {

    @NotNull
    private final CoffeeKindService service;

    @Override
    public CoffeeKind convert(String id) {
        return convert(id, service::findById);
    }
}
