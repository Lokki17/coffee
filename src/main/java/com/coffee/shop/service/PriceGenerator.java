package com.coffee.shop.service;

import com.coffee.shop.dao.entity.Configuration;
import com.coffee.shop.dao.entity.Order;

public interface PriceGenerator {

    public Order setPrice(Order order, Configuration configuration);
}
