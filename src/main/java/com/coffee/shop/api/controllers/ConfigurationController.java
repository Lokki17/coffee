package com.coffee.shop.api.controllers;

import com.coffee.shop.api.mapper.ConfigurationMapper;
import com.coffee.shop.api.resources.ConfigurationResource;
import com.coffee.shop.dao.entity.Configuration;
import com.coffee.shop.dao.exception.EntityNotFoundException;
import com.coffee.shop.service.ConfigurationService;
import javaslang.control.Option;
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
@RequestMapping("/config")
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

    @GetMapping("history")
    public List<ConfigurationResource> getAll() {
        return service.findAll().stream()
                .map(mapper::toResource)
                .collect(Collectors.toList());
    }

    @GetMapping
    public ConfigurationResource getLast() {
        return service.getLastConfig()
                .map(mapper::toResource)
                .getOrElseThrow(() -> new EntityNotFoundException("Configuration not found"));
    }

    @PostMapping
    public ResponseEntity<ConfigurationResource> create(@Valid @RequestBody ConfigurationResource resource) {
        Configuration entity = mapper.fromResource(resource);

        return new ResponseEntity<>(
                mapper.toResource(service.create(entity)),
                HttpStatus.CREATED
        );
    }
}