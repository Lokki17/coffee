package com.coffee.shop.api.controllers;

import com.coffee.shop.api.mapper.CoffeeCupMapper;
import com.coffee.shop.api.resources.CoffeeCupResource;
import com.coffee.shop.dao.entity.CoffeeCup;
import com.coffee.shop.dao.entity.Order;
import com.coffee.shop.service.CoffeeCupService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/cup")
@RequiredArgsConstructor(onConstructor = @__({@Autowired}))
@Slf4j
public class CoffeeCupController {

    @NotNull
    private final CoffeeCupService service;

    @NotNull
    private final CoffeeCupMapper mapper;

    @GetMapping("/{id}")
    public CoffeeCupResource getById(@PathVariable("id") CoffeeCup coffeeCup) {
        return mapper.toResource(coffeeCup);
    }

    @GetMapping
    public List<CoffeeCupResource> getAll() {
        return service.findAll().stream()
                .map(mapper::toResource)
                .collect(Collectors.toList());
    }

    @GetMapping("orders/{orderId}")
    public List<CoffeeCupResource> getAllByOrder(@PathVariable("orderId") Order order) {
        return order.getCups().stream()
                .map(mapper::toResource)
                .collect(Collectors.toList());
    }
}