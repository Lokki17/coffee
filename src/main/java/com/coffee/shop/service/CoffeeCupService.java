package com.coffee.shop.service;

import com.coffee.shop.dao.entity.CoffeeCup;
import javaslang.control.Option;

import java.util.List;

/**
 * @author Lokki17
 * @since 22.07.2017
 */
public interface CoffeeCupService {

    Option<CoffeeCup> findById(Long id);

    List<CoffeeCup> findAll();

    CoffeeCup create(CoffeeCup newEntity);

    CoffeeCup update(CoffeeCup updatedEntity);

    void delete(CoffeeCup entity);
}
