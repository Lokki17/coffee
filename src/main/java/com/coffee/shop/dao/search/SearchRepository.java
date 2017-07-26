package com.coffee.shop.dao.search;

import com.coffee.shop.dao.entity.CoffeeKind;
import com.coffee.shop.dao.entity.SearchCoffeeKind;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface SearchRepository extends ElasticsearchRepository<SearchCoffeeKind, String> {

    List<SearchCoffeeKind> findByDescription(String description);

    List<SearchCoffeeKind> findByNameLike(String name);
}
