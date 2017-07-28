package com.coffee.shop.service;

import com.coffee.shop.dao.entity.SearchCoffeeKind;


public interface SearchService {

//    List<SearchCoffeeKind> search(String key);

    SearchCoffeeKind create(SearchCoffeeKind newEntity);

    SearchCoffeeKind update(SearchCoffeeKind newEntity);
}
