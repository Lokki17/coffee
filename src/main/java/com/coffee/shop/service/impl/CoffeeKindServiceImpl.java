package com.coffee.shop.service.impl;

import com.coffee.shop.dao.entity.CoffeeKind;
import com.coffee.shop.dao.entity.SearchCoffeeKind;
import com.coffee.shop.dao.exception.EntityExistsException;
import com.coffee.shop.dao.repository.CoffeeKindRepository;
import com.coffee.shop.dao.search.SearchRepository;
import com.coffee.shop.service.CoffeeKindService;
import javaslang.control.Option;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor(onConstructor = @__({@Autowired}))
@Slf4j
public class CoffeeKindServiceImpl implements CoffeeKindService {

    @NotNull
    private final CoffeeKindRepository repository;

    @NotNull
    private final SearchRepository searchRepository;

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
    public List<CoffeeKind> search(String key) {
        return searchRepository.findByDescriptionOrName(key, key).stream()
                .map(value -> repository.findOne(Long.parseLong(value.getCoffeeKindId())))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public CoffeeKind create(CoffeeKind newEntity) {
        if (repository.findByName(newEntity.getName()) != null) {
            log.error("Coffee kind already exists");
            throw new EntityExistsException("Coffee kind already exists");
        }

        CoffeeKind created = repository.save(newEntity);
        searchRepository.save(new SearchCoffeeKind(created));
        return created;
    }

    @Override
    @Transactional
    public CoffeeKind update(CoffeeKind updatedEntity) {
        searchRepository.save(new SearchCoffeeKind(updatedEntity));
        return repository.save(updatedEntity);
    }

    @Override
    @Transactional
    public void delete(CoffeeKind entity) {
        searchRepository.delete(entity.getId().toString());
        repository.delete(entity);
    }

}
