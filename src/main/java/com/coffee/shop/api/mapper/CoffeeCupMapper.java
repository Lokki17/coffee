package com.coffee.shop.api.mapper;

import com.coffee.shop.api.resources.CoffeeCupResource;
import com.coffee.shop.dao.entity.CoffeeCup;
import com.coffee.shop.dao.exception.EntityNotFoundException;
import com.coffee.shop.service.CoffeeKindService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;

/**
 * @author Lokki17
 * @since 22.07.2017
 */
@Component
@RequiredArgsConstructor(onConstructor = @__({@Autowired}))
public class CoffeeCupMapper {

    @NotNull
    private final CoffeeKindService coffeeKindService;


    public CoffeeCupResource toResource(CoffeeCup entity) {
        return CoffeeCupResource.builder()
                .id(entity.getId())
                .coffeeKind(entity.getCoffeeKind().getName())
                .count(entity.getCount())
                .build();
    }

    public CoffeeCup fromResource(CoffeeCupResource resource) {
        return CoffeeCup.builder()
                .coffeeKind(coffeeKindService.getKind(resource.getCoffeeKind())
                        .getOrElseThrow(() -> new EntityNotFoundException("Coffee with name "
                                + resource.getCoffeeKind() + " not found")))
                .count(resource.getCount())
                .build();
    }

    public CoffeeCup fromResource(CoffeeCupResource resource, CoffeeCup entity) {
        entity.setCoffeeKind(coffeeKindService.getKind(resource.getCoffeeKind())
                .getOrElseThrow(() -> new EntityNotFoundException("Coffee with name "
                        + resource.getCoffeeKind() + " not found")));
        entity.setCount(resource.getCount());
        return entity;
    }
}
