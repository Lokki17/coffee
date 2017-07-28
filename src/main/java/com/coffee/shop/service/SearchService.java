package com.coffee.shop.service;

import com.coffee.shop.dao.entity.CoffeeKind;
import com.coffee.shop.dao.entity.SearchCoffeeKind;
import javaslang.control.Option;

import java.util.List;


public interface SearchService {

//    List<SearchCoffeeKind> search(String key);

    SearchCoffeeKind create(SearchCoffeeKind newEntity);

    SearchCoffeeKind update(SearchCoffeeKind newEntity);
}
