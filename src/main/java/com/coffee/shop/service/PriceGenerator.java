package com.coffee.shop.service;

import com.coffee.shop.dao.entity.Configuration;
import com.coffee.shop.dao.entity.Order;

public interface PriceGenerator {

    /**
     * Computes and set price for current order.
     * @param order Current order
     * @param configuration Current configuration
     */
    void setPrice(Order order, Configuration configuration);
}
