package com.coffee.shop.service.impl;

import com.coffee.shop.dao.entity.CoffeeKind;
import com.coffee.shop.dao.search.SearchRepository;
import com.coffee.shop.service.SearchService;
import com.google.common.collect.Lists;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor = @__({@Autowired}))
public class SearchServiceImpl implements SearchService {

    @NotNull
    private final SearchRepository repository;

    @Override
    public List<CoffeeKind> findByDescription(String description) {
        return repository.findByDescription(description);
    }

    @Override
    public List<CoffeeKind> findByName(String name) {
        return repository.findByName(name);
    }

    @Override
    public CoffeeKind create(CoffeeKind newEntity) {
        return repository.save(newEntity);
    }

    @Override
    public CoffeeKind update(CoffeeKind updatedEntity) {
        return repository.save(updatedEntity);
    }

    @Override
    public void deleteAll() {
        repository.deleteAll();
    }
}
