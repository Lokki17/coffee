package com.coffee.shop.api.controllers;

import com.coffee.shop.dao.entity.CoffeeCup;
import com.coffee.shop.dao.entity.CoffeeKind;
import com.coffee.shop.dao.entity.Configuration;
import com.coffee.shop.dao.entity.Order;
import com.coffee.shop.dao.repository.CoffeeCupRepository;
import com.coffee.shop.dao.repository.CoffeeKindRepository;
import com.coffee.shop.dao.repository.ConfigurationRepository;
import com.coffee.shop.dao.repository.OrderRepository;
import com.coffee.shop.service.CoffeeCupService;
import com.coffee.shop.service.CoffeeKindService;
import com.coffee.shop.service.ConfigurationService;
import com.coffee.shop.service.OrderService;
import javaslang.control.Option;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang.SerializationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
@RequiredArgsConstructor(onConstructor = @__({@Autowired}))
public class TestContext {

    private final CoffeeKindRepository coffeeKindRepository;

    private final CoffeeKindService coffeeKindService;

    private final CoffeeCupRepository coffeeCupRepository;

    private final CoffeeCupService coffeeCupService;

    private final ConfigurationRepository configurationRepository;

    private final ConfigurationService configurationService;

    private final OrderRepository orderRepository;

    private final OrderService orderService;

    public TestKind kind(CoffeeKind data) {
        CoffeeKind tmp = (CoffeeKind) SerializationUtils.clone(data);

        return new TestKind(tmp);
    }

    public List<TestKind> kinds(CoffeeKind... data) {
        return Stream.of(data)
                .map(this::kind)
                .collect(Collectors.toList());
    }

    public TestCup cup(CoffeeCup data) {
        CoffeeCup tmp = (CoffeeCup) SerializationUtils.clone(data);

        CoffeeKind coffeeKind = new TestKind(tmp.getCoffeeKind()).get();
        tmp.setCoffeeKind(coffeeKind);

        return new TestCup(tmp);
    }

    public List<TestCup> cups(CoffeeCup... cups) {
        return Stream.of(cups)
                .map(this::cup)
                .collect(Collectors.toList());
    }

    public TestOrder order(Order data, Configuration config) {
        Order tmp = (Order) SerializationUtils.clone(data);
        Configuration tmpConfiguration = (Configuration) SerializationUtils.clone(config);

        configurationRepository.save(tmpConfiguration);
        tmp.getCups()
                .forEach(TestCup::new);
//                .peek(TestCup::new)
//                .collect(Collectors.toSet());


        return new TestOrder(tmp);
    }

    public TestConfiguration config(Configuration data) {
        Configuration tmp = (Configuration) SerializationUtils.clone(data);

        return new TestConfiguration(tmp);
    }

    public class TestCup {

        final CoffeeCup self;

        TestCup(CoffeeCup data) {
            new TestKind(data.getCoffeeKind());

            this.self = Option.of(data.getId())
                    .map(value -> coffeeCupService.findById(value)
                            .getOrElse(() -> coffeeCupService.create(data)))
                    .getOrElse(() -> coffeeCupService.create(data));
        }

        public CoffeeCup get() {
            return self;
        }

    }

    public class TestKind {

        final CoffeeKind self;

        TestKind(CoffeeKind data) {
            this.self = Option.of(data.getName())
                    .map(value -> coffeeKindService.getKind(value)
                            .getOrElse(() -> coffeeKindService.create(data)))
                    .getOrElse(() -> coffeeKindService.create(data));
        }

        public CoffeeKind get() {
            return self;
        }
    }

    public class TestOrder {

        public final Order self;

        public TestOrder(Order data) {
            this.self = Option.of(data.getId())
                    .map(value -> orderService.findById(value)
                            .getOrElse(() -> orderService.create(data)))
                    .getOrElse(() -> orderService.create(data));
        }

        public Order get() {
            return self;
        }
    }

    public class TestConfiguration {

        public final Configuration self;

        public TestConfiguration(Configuration data) {
            this.self = Option.of(data.getId())
                    .map(value -> configurationService.findById(value)
                            .getOrElse(() -> configurationService.create(data)))
                    .getOrElse(() -> configurationService.create(data));
        }

        public Configuration get() {
            return self;
        }
    }

}
