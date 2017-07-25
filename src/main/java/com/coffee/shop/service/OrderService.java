package com.coffee.shop.service;

import com.coffee.shop.dao.entity.Order;
import javaslang.control.Option;

import java.util.List;

/**
 * @author Lokki17
 * @since 22.07.2017
 */
public interface OrderService {

    Option<Order> findById(Long id);

    List<Order> findAll();

    Order create(Order newEntity);

    Order update(Order updatedEntity);

    void delete(Order entity);
}
