package com.coffee.shop.api.controllers;

import com.coffee.shop.api.mapper.CoffeeKindMapper;
import com.coffee.shop.api.resources.CoffeeKindResource;
import com.coffee.shop.dao.entity.CoffeeKind;
import com.coffee.shop.service.CoffeeKindService;
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
@RequestMapping("/kind")
@RequiredArgsConstructor(onConstructor = @__({@Autowired}))
@Slf4j
public class CoffeeKindController {

    @NotNull
    private final CoffeeKindService service;

    @NotNull
    private final CoffeeKindMapper mapper;

    @GetMapping("/{id}")
    public CoffeeKindResource getById(@PathVariable("id") CoffeeKind coffeeKind) {
        return mapper.toResource(coffeeKind);
    }

    @GetMapping
    public List<CoffeeKindResource> getAll() {
        return service.findAll().stream()
                .map(mapper::toResource)
                .collect(Collectors.toList());
    }

    @PostMapping
    public ResponseEntity<CoffeeKindResource> create(@Valid @RequestBody CoffeeKindResource resource) {
        CoffeeKind entity = mapper.fromResource(resource);

        return new ResponseEntity<>(
                mapper.toResource(service.create(entity)),
                HttpStatus.CREATED
        );
    }

    @PutMapping("/{id}")
    public CoffeeKindResource update(@Valid @RequestBody CoffeeKindResource resource, @PathVariable("id") CoffeeKind entity) {
        CoffeeKind updated = mapper.fromResource(resource, entity);

        return mapper.toResource(service.update(updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") CoffeeKind entity) {
        service.delete(entity);

        return ResponseEntity.noContent().build();
    }
}