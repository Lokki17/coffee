package com.coffee.shop.service.impl;

import com.coffee.shop.dao.DataInitializer;
import com.coffee.shop.dao.repository.CoffeeKindRepository;
import com.coffee.shop.dao.repository.ConfigurationRepository;
import com.coffee.shop.dao.search.SearchRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;

import javax.validation.constraints.NotNull;

@RequiredArgsConstructor(onConstructor = @__({@Autowired}))
public class ApplicationListenerBean implements ApplicationListener<ApplicationReadyEvent> {

    @NotNull
    private final CoffeeKindRepository coffeeKindRepository;

    @NotNull
    private final ConfigurationRepository configurationRepository;

    @NotNull
    private final SearchRepository searchRepository;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        new DataInitializer(coffeeKindRepository, configurationRepository, searchRepository);
    }
}
