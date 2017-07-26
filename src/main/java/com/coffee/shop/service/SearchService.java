package com.coffee.shop.service;

import com.coffee.shop.dao.entity.CoffeeKind;
import com.coffee.shop.dao.entity.SearchCoffeeKind;

import java.util.List;

/**
 * @author Lokki17
 * @since 22.07.2017
 */
public interface SearchService {

    List<SearchCoffeeKind> findByDescription(String description);

    List<SearchCoffeeKind> findByName(String name);

    SearchCoffeeKind create(SearchCoffeeKind newEntity);

    SearchCoffeeKind update(SearchCoffeeKind newEntity);
}
