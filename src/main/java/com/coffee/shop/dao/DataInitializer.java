package com.coffee.shop.dao;

import com.coffee.shop.dao.entity.CoffeeKind;
import com.coffee.shop.dao.entity.Configuration;
import com.coffee.shop.dao.entity.SearchCoffeeKind;
import com.coffee.shop.dao.repository.CoffeeKindRepository;
import com.coffee.shop.dao.repository.ConfigurationRepository;
import com.coffee.shop.dao.search.SearchRepository;
import javaslang.control.Try;

import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class DataInitializer {

    private CoffeeKindRepository coffeeKindRepository;

    private ConfigurationRepository configurationRepository;

    private SearchRepository searchRepository;

    public DataInitializer(CoffeeKindRepository coffeeKindRepository, ConfigurationRepository configurationRepository,
                           SearchRepository searchRepository) {
        this.coffeeKindRepository = coffeeKindRepository;
        this.configurationRepository = configurationRepository;
        this.searchRepository = searchRepository;
        initialize();
    }

    public void initialize() {

        Try.of(() -> {
            Files.lines(Paths.get(this.getClass().getClassLoader().getResource("initdb").toURI()))
                    .map(this::getKindFromString)
                    .forEach(this::createCoffeeKindIfNotPresent);

            return null;
        });

        createConfigIfNotPresent(Configuration.builder()
                .cupCount(5)
                .discountSumm(new BigDecimal(10))
                .deliveryPrice(new BigDecimal(2))
                .build());
    }


    private CoffeeKind createCoffeeKindIfNotPresent(CoffeeKind kind) {

        List<CoffeeKind> result = coffeeKindRepository.findAll();


        if (!result.contains(kind)) {
            kind = coffeeKindRepository.save(kind);
        }
        createIndexIfNotPresent(kind);
        return kind;
    }

    private void createIndexIfNotPresent(CoffeeKind kind) {
        SearchCoffeeKind search = searchRepository.findOne(kind.getId().toString());

        if (search == null) {
            searchRepository.save(new SearchCoffeeKind(kind));
        }
    }

    private void createConfigIfNotPresent(Configuration config) {

        List<Configuration> result = configurationRepository.findAll();
        if (!result.contains(config)) {
            configurationRepository.save(config);
        }
    }

    private CoffeeKind getKindFromString(String line) {
        String[] array = line.split("\\|");
        return new CoffeeKind(array[0], array[1], new BigDecimal(array[2]));
    }

}
