package com.coffee.shop.service;

import com.coffee.shop.dao.entity.CoffeeKind;
import javaslang.control.Option;

import java.util.List;

/**
 * @author Lokki17
 * @since 22.07.2017
 */
public interface CoffeeKindService {

    Option<CoffeeKind> getKind(String name);

    Option<CoffeeKind> findById(Long id);

    List<CoffeeKind> findAll();

    CoffeeKind create(CoffeeKind newEntity);

    CoffeeKind update(CoffeeKind updatedEntity);

    void delete(CoffeeKind entity);
}
