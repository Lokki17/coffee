package com.coffee.shop.dao.search;

import com.coffee.shop.dao.entity.CoffeeKind;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

/**
 * @author Lokki17
 * @since 23.07.2017
 */
public interface SearchRepository extends ElasticsearchRepository<CoffeeKind, String> {

    List<CoffeeKind> findByDescription(String description);

    List<CoffeeKind> findByName(String name);
}
