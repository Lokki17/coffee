package com.coffee.shop.api.controllers;

import com.coffee.shop.api.mapper.CoffeeKindMapper;
import com.coffee.shop.api.resources.CoffeeKindResource;
import com.coffee.shop.dao.entity.CoffeeKind;
import com.coffee.shop.service.CoffeeKindService;
import com.coffee.shop.service.SearchService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.websocket.server.PathParam;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/search")
@RequiredArgsConstructor(onConstructor = @__({@Autowired}))
@Slf4j
public class SearchController {

    @NotNull
    private final SearchService service;

    @NotNull
    private final CoffeeKindMapper mapper;

    @GetMapping(params = "description")
    public List<CoffeeKindResource> getByDescription(@PathParam("description") String description) {
        return service.findByDescription(description).stream()
                .map(mapper::toResource)
                .collect(Collectors.toList());
    }

    @GetMapping(params = "name")
    public List<CoffeeKindResource> getByName(@PathParam("name") String name) {
        return service.findByName(name).stream()
                .map(mapper::toResource)
                .collect(Collectors.toList());
    }
}