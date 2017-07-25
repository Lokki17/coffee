package com.coffee.shop.service;

import com.coffee.shop.dao.entity.Configuration;
import com.coffee.shop.dao.entity.Order;

/**
 * @author Lokki17
 * @since 23.07.2017
 */
public interface PriceGenerator {

    public Order setPrice(Order order, Configuration configuration);
}
