package com.coffee.shop.dao.repository;

import com.coffee.shop.dao.entity.CoffeeKind;

public interface CoffeeKindRepository extends BaseRepository<CoffeeKind> {

    CoffeeKind findByName(String name);
}
