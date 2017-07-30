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

    private final CoffeeKindService coffeeKindService;

    private final CoffeeCupService coffeeCupService;

    private final ConfigurationRepository configurationRepository;

    private final ConfigurationService configurationService;

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


    public TestOrder order(Order data) {
        Order tmp = (Order) SerializationUtils.clone(data);
        tmp.getCups()
                .forEach(TestCup::new);

        return new TestOrder(tmp);
    }

    public List<Order> orders(Order... orders) {
        return Stream.of(orders)
                .peek(this::order)
        .collect(Collectors.toList());
    }


    public TestConfiguration config(Configuration data) {
        Configuration tmp = (Configuration) SerializationUtils.clone(data);

        return new TestConfiguration(tmp, this);
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

        public final TestContext context;

        public TestConfiguration(Configuration data, TestContext context) {
            this.context = context;
            this.self = Option.of(data.getId())
                    .map(value -> configurationService.findById(value)
                            .getOrElse(() -> configurationService.create(data)))
                    .getOrElse(() -> configurationService.create(data));
        }

        public Configuration get() {
            return self;
        }

        public TestContext and(){
            return context;
        }
    }

}
