package com.coffee.shop.service.impl;

import com.coffee.shop.dao.entity.CoffeeCup;
import com.coffee.shop.dao.repository.CoffeeCupRepository;
import com.coffee.shop.service.CoffeeCupService;
import javaslang.control.Option;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor = @__({@Autowired}))
public class CoffeeCupServiceImpl implements CoffeeCupService {

    @NotNull
    private final CoffeeCupRepository repository;

    @Override
    public Option<CoffeeCup> findById(Long id) {
        return Option.of(repository.findOne(id));
    }

    @Override
    public List<CoffeeCup> findAll() {
        return repository.findAll();
    }

    @Override
    public CoffeeCup create(CoffeeCup newEntity) {
        return repository.save(newEntity);
    }

    @Override
    public CoffeeCup update(CoffeeCup updatedEntity) {
        return repository.save(updatedEntity);
    }

    @Override
    public void delete(CoffeeCup entity) {
        repository.delete(entity);
    }
}
