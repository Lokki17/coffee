package com.coffee.shop.dao.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@Configuration
@EnableJpaRepositories(basePackages = "com.coffee.shop.dao.repository")
@EntityScan(basePackages = {
        "com.coffee.shop.dao.entity",
        "com.coffee.shop.dao.repository"
})
public class DaoConfiguration {

}
