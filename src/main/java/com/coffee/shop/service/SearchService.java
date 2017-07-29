package com.coffee.shop.service;

import com.coffee.shop.dao.entity.SearchCoffeeKind;

public interface SearchService {

    SearchCoffeeKind create(SearchCoffeeKind newEntity);

    SearchCoffeeKind update(SearchCoffeeKind newEntity);
}
