package com.coffee.shop.service.impl;

import com.coffee.shop.dao.entity.SearchCoffeeKind;
import com.coffee.shop.dao.search.SearchRepository;
import com.coffee.shop.service.CoffeeKindService;
import com.coffee.shop.service.SearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;

@Service
@RequiredArgsConstructor(onConstructor = @__({@Autowired}))
public class SearchServiceImpl implements SearchService {

    @NotNull
    private final SearchRepository repository;

    @NotNull
    private final CoffeeKindService coffeeKindService;
//
//    @Override
//    public List<SearchCoffeeKind> search(String key) {
//
//        return repository.findByDescriptionOrName(key);
//    }

    @Override
    public SearchCoffeeKind create(SearchCoffeeKind newEntity) {
        return repository.save(newEntity);
    }

    @Override
    public SearchCoffeeKind update(SearchCoffeeKind updatedEntity) {

        return repository.save(updatedEntity);
    }

//    private List<CoffeeKind> getList(List<SearchCoffeeKind> searchKinds) {
//        return searchKinds.stream()
//                .map(value -> coffeeKindService.findById(Long.parseLong(value.getCoffeeKindId()))
//                        .getOrElseThrow(() -> new EntityNotFoundException("CoffeeKind with id" + value.getCoffeeKindId() + " not found")))
//                .collect(Collectors.toList());
//
//    }
}
