package com.coffee.shop.dao.config;

import com.coffee.shop.dao.repository.CoffeeKindRepository;
import com.coffee.shop.dao.repository.ConfigurationRepository;
import com.coffee.shop.dao.search.SearchRepository;
import com.coffee.shop.service.impl.ApplicationListenerBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@Configuration
@EnableJpaRepositories(basePackages = "com.coffee.shop.dao.repository")
@EntityScan(basePackages = {
        "com.coffee.shop.dao.entity",
        "com.coffee.shop.dao.repository"
})
public class DaoConfiguration {

    @Autowired(required = false)
    private CoffeeKindRepository coffeeKindRepository;

    @Autowired(required = false)
    private ConfigurationRepository configurationRepository;

    @Autowired(required = false)
    private SearchRepository searchRepository;

    @Profile("initdb")
    @Bean
    @Lazy(false)
    ApplicationListenerBean get() {
        return new ApplicationListenerBean(coffeeKindRepository, configurationRepository, searchRepository);
    }
}
