package com.coffee.shop.dao.search;

import com.coffee.shop.dao.entity.SearchCoffeeKind;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface SearchRepository extends ElasticsearchRepository<SearchCoffeeKind, String> {

    List<SearchCoffeeKind> findByDescriptionOrName(String name, String description);
}
