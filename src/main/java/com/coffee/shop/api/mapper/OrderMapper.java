package com.coffee.shop.api.mapper;

import com.coffee.shop.api.resources.OrderResource;
import com.coffee.shop.dao.entity.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.stream.Collectors;

/**
 * @author Lokki17
 * @since 22.07.2017
 */
@Component
@RequiredArgsConstructor(onConstructor = @__({@Autowired}))
public class OrderMapper {

    @NotNull
    private final CoffeeCupMapper coffeeCupMapper;

    public OrderResource toResource(Order entity) {
        return OrderResource.builder()
                .id(entity.getId())
                .address(entity.getAddress())
                .userName(entity.getUserName())
                .price(entity.getPrice().doubleValue())
                .cups(entity.getCups().stream()
                        .map(coffeeCupMapper::toResource)
                        .collect(Collectors.toSet()))
                .build();
    }

    public Order fromResource(OrderResource resource) {
        return Order.builder()
                .address(resource.getAddress())
                .userName(resource.getUserName())
                .cups(resource.getCups().stream()
                        .map(coffeeCupMapper::fromResource)
                        .collect(Collectors.toSet()))
                .build();
    }

    public Order fromResource(OrderResource resource, Order entity) {
        entity.setAddress(resource.getAddress());
        entity.setPrice(new BigDecimal(resource.getPrice()));
        entity.setUserName(resource.getUserName());
        entity.setCups(resource.getCups().stream()
        .map(coffeeCupMapper::fromResource)
        .collect(Collectors.toSet()));

        return entity;
    }
}
