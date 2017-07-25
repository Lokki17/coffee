package com.coffee.shop.api.converter;

import com.coffee.shop.dao.entity.Order;
import com.coffee.shop.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;


@Component
@RequiredArgsConstructor(onConstructor = @__({@Autowired}))
public class IdStringToOrderConverter extends AbstractIdStringToObjectConverter<Order> {

    @NotNull
    private final OrderService service;

    @Override
    public Order convert(String id) {
        return convert(id, service::findById);
    }
}
