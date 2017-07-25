package com.coffee.shop;

import com.coffee.shop.api.resources.CoffeeCupResource;
import com.coffee.shop.api.resources.CoffeeKindResource;
import com.coffee.shop.api.resources.ConfigurationResource;
import com.coffee.shop.dao.entity.CoffeeCup;
import com.coffee.shop.dao.entity.CoffeeKind;
import com.coffee.shop.dao.entity.Configuration;
import com.coffee.shop.dao.entity.Order;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashSet;

@Getter
public class TestData {

    public static CoffeeKind CAPUCHINO = CoffeeKind.builder()
            .name("Capuchino")
            .description("hot coffee")
            .price(new BigDecimal(2))
            .build();

    public static CoffeeKindResource CAPUCHINO_RESOURCE = CoffeeKindResource.builder()
            .name(CAPUCHINO.getName())
            .description(CAPUCHINO.getDescription())
            .price(CAPUCHINO.getPrice().doubleValue())
            .build();

    public static CoffeeKind MOKA = CoffeeKind.builder()
            .name("Moka")
            .description("just moka")
            .price(new BigDecimal(1.5))
            .build();

    public static CoffeeKind ESPRESSOO = CoffeeKind.builder()
            .name("Espresso")
            .description("most famous")
            .price(new BigDecimal(1))
            .build();

    public static CoffeeKind TURKISH = CoffeeKind.builder()
            .name("Turkish")
            .description("from east")
            .price(new BigDecimal(2.5))
            .build();

    public static CoffeeKind FRAPPE = CoffeeKind.builder()
            .name("Frappe")
            .description("cold greek coffee")
            .price(new BigDecimal(3))
            .build();

    public static Configuration CONFIGURATION = Configuration.builder()
            .cupCount(5)
            .discountSumm(new BigDecimal(10))
            .deliveryPrice(new BigDecimal(2))
            .build();

    public static ConfigurationResource CONFIGURATION_RESOURCE = ConfigurationResource.builder()
            .cupCount(CONFIGURATION.getCupCount())
            .discountSumm(CONFIGURATION.getDiscountSumm().doubleValue())
            .deliveryPrice(CONFIGURATION.getDeliveryPrice().doubleValue())
            .build();

    public static CoffeeCup CUP_CAPUCHINO = CoffeeCup.builder()
            .coffeeKind(CAPUCHINO)
            .count(1)
            .build();

    public static CoffeeCupResource CUP_CAPUCHINO_RESOURCE = CoffeeCupResource.builder()
            .coffeeKind(CUP_CAPUCHINO.getCoffeeKind().getName())
            .count(CUP_CAPUCHINO.getCount())
            .id(CUP_CAPUCHINO.getId())
            .build();

    public static CoffeeCup CUP_MOKA = CoffeeCup.builder()
            .coffeeKind(MOKA)
            .count(2)
            .build();

    public static CoffeeCup CUP_ESPRESSOO = CoffeeCup.builder()
            .coffeeKind(ESPRESSOO)
            .count(3)
            .build();

    public static CoffeeCup CUP_TURKISH = CoffeeCup.builder()
            .coffeeKind(TURKISH)
            .count(1)
            .build();

    public static CoffeeCup CUP_FRAPPE = CoffeeCup.builder()
            .coffeeKind(FRAPPE)
            .count(3)
            .build();

    public static Order ORDER_1 = Order.builder()
            .address("my address")
            .userName("Me")
            .cups(new HashSet<>(Arrays.asList(CUP_CAPUCHINO)))
            .price(new BigDecimal(5))
            .build();

    public static Order ORDER_2 = Order.builder()
            .address("any address")
            .userName("One")
            .cups(new HashSet<>(Arrays.asList(CUP_ESPRESSOO, CUP_TURKISH, CUP_FRAPPE)))
            .price(new BigDecimal(14.5))
            .build();

}
