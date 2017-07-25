package com.coffee.shop.service.impl;

import com.coffee.shop.dao.entity.CoffeeKind;
import com.coffee.shop.dao.repository.CoffeeKindRepository;
import com.coffee.shop.service.CoffeeKindService;
import com.coffee.shop.service.SearchService;
import javaslang.control.Option;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author Lokki17
 * @since 22.07.2017
 */
@Service
@RequiredArgsConstructor(onConstructor = @__({@Autowired}))
public class CoffeeKindServiceImpl implements CoffeeKindService {

    @NotNull
    private final CoffeeKindRepository repository;

    @NotNull
    private final SearchService searchService;

    @Override
    public Option<CoffeeKind> getKind(String name) {
        return Option.of(repository.findByName(name));
    }

    @Override
    public Option<CoffeeKind> findById(Long id) {
        return Option.of(repository.findOne(id));
    }

    @Override
    public List<CoffeeKind> findAll() {
        return repository.findAll();
    }

    @Override
    public CoffeeKind create(CoffeeKind newEntity) {
        searchService.create(newEntity);
        return repository.save(newEntity);
    }

    @Override
    public CoffeeKind update(CoffeeKind updatedEntity) {
        searchService.update(updatedEntity);
        return repository.save(updatedEntity);
    }

    @Override
    public void delete(CoffeeKind entity) {
        repository.delete(entity);
    }

}
