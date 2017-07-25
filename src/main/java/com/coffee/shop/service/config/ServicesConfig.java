package com.coffee.shop.service.config;

import com.coffee.shop.dao.config.DaoConfiguration;
import com.coffee.shop.dao.config.SearchConfig;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author Lokki17
 * @since 22.07.2017
 */
@Configuration
@ComponentScan("com.coffee.shop.service.impl")
@Import({DaoConfiguration.class, SearchConfig.class})
public class ServicesConfig {
}
