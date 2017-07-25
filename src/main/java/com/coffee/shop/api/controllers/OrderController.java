package com.coffee.shop.api.controllers;

import com.coffee.shop.api.mapper.OrderMapper;
import com.coffee.shop.api.resources.OrderResource;
import com.coffee.shop.dao.entity.Order;
import com.coffee.shop.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/order")
@RequiredArgsConstructor(onConstructor = @__({@Autowired}))
@Slf4j
public class OrderController {

    @NotNull
    private final OrderService service;

    @NotNull
    private final OrderMapper mapper;

    @GetMapping("/{id}")
    public OrderResource getById(@PathVariable("id") Order order) {
        return mapper.toResource(order);
    }

    @GetMapping
    public List<OrderResource> getAll() {
        return service.findAll().stream()
                .map(mapper::toResource)
                .collect(Collectors.toList());
    }

    @PostMapping
    public ResponseEntity<OrderResource> create(@Valid @RequestBody OrderResource resource) {
        Order entity = mapper.fromResource(resource);

        return new ResponseEntity<>(
                mapper.toResource(service.create(entity)),
                HttpStatus.CREATED
        );
    }

    @PutMapping("/{id}")
    public OrderResource update(@Valid @RequestBody OrderResource resource, @PathVariable("id") Order entity) {
        Order updated = mapper.fromResource(resource, entity);

        return mapper.toResource(service.update(updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Order entity) {
        service.delete(entity);

        return ResponseEntity.noContent().build();
    }

}