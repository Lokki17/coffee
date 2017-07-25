package com.coffee.shop.api.controllers;

import com.coffee.shop.api.mapper.CoffeeCupMapper;
import com.coffee.shop.api.resources.CoffeeCupResource;
import com.coffee.shop.dao.entity.CoffeeCup;
import com.coffee.shop.service.CoffeeCupService;
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

    @PostMapping
    public ResponseEntity<CoffeeCupResource> create(@Valid @RequestBody CoffeeCupResource resource) {
        CoffeeCup entity = mapper.fromResource(resource);

        return new ResponseEntity<>(
                mapper.toResource(service.create(entity)),
                HttpStatus.CREATED
        );
    }

    @PutMapping("/{id}")
    public CoffeeCupResource update(@Valid @RequestBody CoffeeCupResource resource, @PathVariable("id") CoffeeCup entity) {
                CoffeeCup updated = mapper.fromResource(resource, entity);

        return mapper.toResource(service.update(updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") CoffeeCup entity) {
        service.delete(entity);

        return ResponseEntity.noContent().build();
    }
}