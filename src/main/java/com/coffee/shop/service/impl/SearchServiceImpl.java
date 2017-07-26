package com.coffee.shop.service.impl;

import com.coffee.shop.dao.entity.CoffeeKind;
import com.coffee.shop.dao.entity.SearchCoffeeKind;
import com.coffee.shop.dao.search.SearchRepository;
import com.coffee.shop.service.SearchService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang.SerializationUtils;
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
    public List<SearchCoffeeKind> findByDescription(String description) {
        return repository.findByDescription(description);
    }

    @Override
    public List<SearchCoffeeKind> findByName(String name) {
        return repository.findByNameLike(name);
    }

    @Override
    public SearchCoffeeKind create(SearchCoffeeKind newEntity) {
        return repository.save(newEntity);
    }

    @Override
    public SearchCoffeeKind update(SearchCoffeeKind updatedEntity) {

        return repository.save(updatedEntity);
    }


    private CoffeeKind getKindWithIdNull(CoffeeKind coffeeKind){
        CoffeeKind tmp = (CoffeeKind) SerializationUtils.clone(coffeeKind);
        tmp.setId(null);
        return tmp;
    }
}
