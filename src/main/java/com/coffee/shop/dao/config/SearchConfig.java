package com.coffee.shop.dao.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

@Configuration
@EnableElasticsearchRepositories(basePackages = "com.coffee.shop.dao.search")
public class SearchConfig {

}
