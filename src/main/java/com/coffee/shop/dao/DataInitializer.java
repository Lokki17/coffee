package com.coffee.shop.dao;

import com.coffee.shop.dao.entity.*;
import com.coffee.shop.dao.repository.CoffeeCupRepository;
import com.coffee.shop.dao.repository.CoffeeKindRepository;
import com.coffee.shop.dao.repository.ConfigurationRepository;
import com.coffee.shop.dao.repository.OrderRepository;
import com.coffee.shop.dao.search.SearchRepository;
import com.coffee.shop.service.SearchService;
import com.google.common.collect.Lists;
import javaslang.control.Option;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

public class DataInitializer {

    @Autowired
    private CoffeeKindRepository coffeeKindRepository;

    @Autowired
    private CoffeeCupRepository coffeeCupRepository;

    @Autowired
    private ConfigurationRepository configurationRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private SearchService searchService;

    @Autowired
    private SearchRepository searchRepository;

    @Value("${coffee.shop.init-delay}")
    private Integer fileName;

    @PostConstruct
    public void initialize() throws InterruptedException {

        Thread.sleep(fileName);

        CoffeeKind capuchino = createCoffeeKindIfNotPresent(CoffeeKind.builder()
                .name("Capuchino")
                .description("hot coffee")
                .price(new BigDecimal(2))
                .build());

        CoffeeKind moka = createCoffeeKindIfNotPresent(CoffeeKind.builder()
                .name("Moka")
                .description("just moka")
                .price(new BigDecimal(1.5))
                .build());

        CoffeeKind espresso = createCoffeeKindIfNotPresent(CoffeeKind.builder()
                .name("Espresso")
                .description("most famous")
                .price(new BigDecimal(1))
                .build());

        CoffeeKind turkish = createCoffeeKindIfNotPresent(CoffeeKind.builder()
                .name("Turkish")
                .description("from east")
                .price(new BigDecimal(2.5))
                .build());

        CoffeeKind frappe = createCoffeeKindIfNotPresent(CoffeeKind.builder()
                .name("Turkish Frappe")
                .description("cold greek coffee")
                .price(new BigDecimal(3))
                .build());

        Configuration configuration = createConfigIfNotPresent(Configuration.builder()
                .cupCount(5)
                .discountSumm(new BigDecimal(10))
                .deliveryPrice(new BigDecimal(2))
                .build());

        CoffeeCup cup_capuchino = createCoffeeCupIfNotPresent(CoffeeCup.builder()
                .coffeeKind(capuchino)
                .count(1)
                .build());

        CoffeeCup cup_moka = createCoffeeCupIfNotPresent(CoffeeCup.builder()
                .coffeeKind(moka)
                .count(2)
                .build());

        CoffeeCup cup_espresso = createCoffeeCupIfNotPresent(CoffeeCup.builder()
                .coffeeKind(espresso)
                .count(3)
                .build());

        CoffeeCup cup_turkish = createCoffeeCupIfNotPresent(CoffeeCup.builder()
                .coffeeKind(turkish)
                .count(1)
                .build());

        CoffeeCup cup_frappe = createCoffeeCupIfNotPresent(CoffeeCup.builder()
                .coffeeKind(frappe)
                .count(3)
                .build());

        Order order_1 = createOrderIfNotPresent(Order.builder()
                .address("my address")
                .userName("Me")
                .cups(new HashSet<>(Collections.singletonList(cup_capuchino)))
                .price(new BigDecimal(5))
                .build());

        Order order_2 = createOrderIfNotPresent(Order.builder()
                .address("any address")
                .userName("One")
                .cups(new HashSet<>(Arrays.asList(cup_espresso, cup_turkish, cup_frappe)))
                .price(new BigDecimal(14.5))
                .build());
    }


    private void createIndexIfNotPresent(CoffeeKind kind) {
        List<SearchCoffeeKind> searches = Lists.newArrayList(searchRepository.findAll());
        Long count = searches.stream()
                .filter(value -> value.getName().equals(kind.getName()))
                .count();

        if (count == 0){
            searchService.create(new SearchCoffeeKind(kind));
        }
    }

    private CoffeeCup createCoffeeCupIfNotPresent(CoffeeCup coffeeCup) {
        List<CoffeeCup> result = coffeeCupRepository.findAll();

        if (!result.contains(coffeeCup)) {
            return coffeeCupRepository.save(coffeeCup);
        } else return coffeeCup;
    }

    private CoffeeKind createCoffeeKindIfNotPresent(CoffeeKind kind) {
        createIndexIfNotPresent(kind);

        List<CoffeeKind> result = coffeeKindRepository.findAll();

        if (!result.contains(kind)) {
            return coffeeKindRepository.save(kind);
        } else return kind;
    }

    private Configuration createConfigIfNotPresent(Configuration config) {

        List<Configuration> result = configurationRepository.findAll();
        if (!result.contains(config)) {
            return configurationRepository.save(config);
        } else return config;
    }

    private Order createOrderIfNotPresent(Order order) {
        List<Order> result = orderRepository.findAll();
        if (!result.contains(order)) {
            return orderRepository.save(order);
        } else return order;
    }

}
