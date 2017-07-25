package com.coffee.shop.service;

import com.coffee.shop.dao.entity.CoffeeKind;

import java.util.List;

/**
 * @author Lokki17
 * @since 22.07.2017
 */
public interface SearchService {

    List<CoffeeKind> findByDescription(String description);

    List<CoffeeKind> findByName(String name);

    CoffeeKind create(CoffeeKind newEntity);

    CoffeeKind update(CoffeeKind newEntity);
}
