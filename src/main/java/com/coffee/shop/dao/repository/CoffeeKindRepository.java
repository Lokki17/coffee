package com.coffee.shop.dao.repository;

import com.coffee.shop.dao.entity.CoffeeKind;

/**
 * @author Lokki17
 * @since 22.07.2017
 */
public interface CoffeeKindRepository extends BaseRepository<CoffeeKind> {

    CoffeeKind findByName(String name);
}
