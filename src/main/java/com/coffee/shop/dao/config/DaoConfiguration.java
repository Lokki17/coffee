package com.coffee.shop.dao.config;

import com.coffee.shop.dao.DataInitializer;
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

    @Profile("initdb")
    @Bean
    @Lazy(false)
    DataInitializer init() {
        return new DataInitializer();
    }

}
