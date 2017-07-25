package com.coffee.shop.api.config;

import com.coffee.shop.service.config.ServicesConfig;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@ComponentScan({
        "com.coffee.shop.api.controllers"
})
@Import({ServicesConfig.class})
public class WebConfiguration extends WebMvcConfigurerAdapter {

}
