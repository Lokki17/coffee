package com.coffee.shop.dao.repository;

import com.coffee.shop.JpaTest;
import com.coffee.shop.dao.entity.CoffeeCup;
import com.coffee.shop.dao.entity.CoffeeKind;
import com.coffee.shop.dao.entity.Configuration;
import com.coffee.shop.dao.entity.Order;
import lombok.Getter;
import org.apache.commons.lang.SerializationUtils;
import org.junit.After;
import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Arrays;
import java.util.HashSet;

import static com.coffee.shop.TestData.*;

/**
 * @author Lokki17
 * @since 23.07.2017
 */
@Getter
@JpaTest
public abstract class AbstractRepositoryTest {

    @Autowired
    protected CoffeeKindRepository coffeeKindRepository;

    @Autowired
    protected CoffeeCupRepository coffeeCupRepository;

    @Autowired
    protected ConfigurationRepository configurationRepository;

    @Autowired
    protected OrderRepository orderRepository;

    protected static final Pageable PAGE = new PageRequest(0, Integer.MAX_VALUE);

    protected CoffeeCup coffeeCup;
    protected CoffeeKind coffeeKind;
    protected Configuration configuration;
    protected Order order;

    @Before
    public void setUp() {
        coffeeKind = coffeeKindRepository.saveAndFlush((CoffeeKind) SerializationUtils.clone(CAPUCHINO));
        CoffeeCup currentCup = (CoffeeCup) SerializationUtils.clone(CUP_CAPUCHINO);
        currentCup.setCoffeeKind(coffeeKind);
        coffeeCup = coffeeCupRepository.saveAndFlush(currentCup);
        configuration = configurationRepository.saveAndFlush(CONFIGURATION);
        Order currentOrder = (Order) SerializationUtils.clone(ORDER_1);
        currentOrder.setCups(new HashSet<>(Arrays.asList(currentCup)));
        order = orderRepository.saveAndFlush(currentOrder);
    }

    @After
    public void delete() {
        coffeeKindRepository.deleteAll();
        coffeeCupRepository.deleteAll();
        orderRepository.deleteAll();
        configurationRepository.deleteAll();
    }

}
