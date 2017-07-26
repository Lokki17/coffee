package com.coffee.shop.api.mapper;

import com.coffee.shop.api.resources.CoffeeKindResource;
import com.coffee.shop.api.resources.SearchCoffeeKindResource;
import com.coffee.shop.dao.entity.CoffeeKind;
import com.coffee.shop.dao.entity.SearchCoffeeKind;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class CoffeeKindMapper {

    public CoffeeKindResource toResource(CoffeeKind entity) {
        return CoffeeKindResource.builder()
                .id(entity.getId())
                .name(entity.getName())
                .description(entity.getDescription())
                .price(entity.getPrice().doubleValue())
                .build();
    }

    public SearchCoffeeKindResource toResource(SearchCoffeeKind entity) {
        return SearchCoffeeKindResource.builder()
                .name(entity.getName())
                .description(entity.getDescription())
                .price(entity.getPrice().doubleValue())
                .build();
    }

    public CoffeeKind fromResource(CoffeeKindResource resource) {
        return CoffeeKind.builder()
                .name(resource.getName())
                .description(resource.getDescription())
                .price(new BigDecimal(resource.getPrice()))
                .build();
    }

    public CoffeeKind fromResource(CoffeeKindResource resource, CoffeeKind entity) {
        entity.setDescription(resource.getDescription());
        entity.setName(resource.getName());
        return entity;
    }
}
