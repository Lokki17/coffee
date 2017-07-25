package com.coffee.shop.api.controllers;

import com.coffee.shop.api.mapper.ConfigurationMapper;
import com.coffee.shop.api.resources.ConfigurationResource;
import com.coffee.shop.dao.entity.Configuration;
import com.coffee.shop.service.ConfigurationService;
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
@RequestMapping("/coffee")
@RequiredArgsConstructor(onConstructor = @__({@Autowired}))
@Slf4j
public class ConfigurationController {

    @NotNull
    private final ConfigurationService service;

    @NotNull
    private final ConfigurationMapper mapper;

    @GetMapping("/{id}")
    public ConfigurationResource getById(@PathVariable("id") Configuration configuration) {
        return mapper.toResource(configuration);
    }

    @GetMapping
    public List<ConfigurationResource> getAll() {
        return service.findAll().stream()
                .map(mapper::toResource)
                .collect(Collectors.toList());
    }

    @PostMapping
    public ResponseEntity<ConfigurationResource> create(@Valid @RequestBody ConfigurationResource resource) {
        Configuration entity = mapper.fromResource(resource);

        return new ResponseEntity<>(
                mapper.toResource(service.create(entity)),
                HttpStatus.CREATED
        );
    }

    @PutMapping("/{id}")
    public ConfigurationResource update(@Valid @RequestBody ConfigurationResource resource, @PathVariable("id") Configuration entity) {
        Configuration updated = mapper.fromResource(resource, entity);

        return mapper.toResource(service.update(updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Configuration entity) {
        service.delete(entity);

        return ResponseEntity.noContent().build();
    }

}