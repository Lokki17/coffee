package com.coffee.shop.service;

import com.coffee.shop.dao.entity.CoffeeKind;
import javaslang.control.Option;

import java.util.List;

public interface CoffeeKindService {

    Option<CoffeeKind> getKind(String name);

    Option<CoffeeKind> findById(Long id);

    List<CoffeeKind> findAll();

    List<CoffeeKind> search(String key);

    CoffeeKind create(CoffeeKind newEntity);

    CoffeeKind update(CoffeeKind updatedEntity);

    void delete(CoffeeKind entity);
}
