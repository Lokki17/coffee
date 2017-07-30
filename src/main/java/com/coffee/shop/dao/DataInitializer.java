package com.coffee.shop.dao;

import com.coffee.shop.dao.entity.CoffeeKind;
import com.coffee.shop.dao.entity.Configuration;
import com.coffee.shop.dao.entity.SearchCoffeeKind;
import com.coffee.shop.dao.repository.CoffeeCupRepository;
import com.coffee.shop.dao.repository.CoffeeKindRepository;
import com.coffee.shop.dao.repository.ConfigurationRepository;
import com.coffee.shop.dao.repository.OrderRepository;
import com.coffee.shop.dao.search.SearchRepository;
import com.coffee.shop.service.SearchService;
import com.google.common.collect.Lists;
import javaslang.control.Try;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class DataInitializer {

    @Autowired
    private CoffeeKindRepository coffeeKindRepository;

    @Autowired
    private ConfigurationRepository configurationRepository;

    @Autowired
    private SearchService searchService;

    @Autowired
    private SearchRepository searchRepository;

    @Value("${coffee.shop.init-delay}")
    private Integer fileName;

    @PostConstruct
    public void initialize() throws InterruptedException {

        Thread.sleep(fileName);

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


    private void createIndexIfNotPresent(CoffeeKind kind) {
        List<SearchCoffeeKind> searches = Lists.newArrayList(searchRepository.findAll());
        Long count = searches.stream()
                .filter(value -> value.getName().equals(kind.getName()))
                .count();

        if (count == 0) {
            searchService.create(new SearchCoffeeKind(kind));
        }
    }

    private CoffeeKind createCoffeeKindIfNotPresent(CoffeeKind kind) {

        List<CoffeeKind> result = coffeeKindRepository.findAll();


        if (!result.contains(kind)) {
            kind = coffeeKindRepository.save(kind);
        }
        createIndexIfNotPresent(kind);
        return kind;
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
