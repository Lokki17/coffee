package com.coffee.shop.api.mapper;

import com.coffee.shop.api.resources.ConfigurationResource;
import com.coffee.shop.dao.entity.Configuration;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class ConfigurationMapper {

    public ConfigurationResource toResource(Configuration entity) {
        return ConfigurationResource.builder()
                .id(entity.getId())
                .cupCount(entity.getCupCount())
                .deliveryPrice(entity.getDeliveryPrice().doubleValue())
                .discountSumm(entity.getDiscountSumm().doubleValue())
                .build();
    }

    public Configuration fromResource(ConfigurationResource resource) {
        return Configuration.builder()
                .cupCount(resource.getCupCount())
                .deliveryPrice(new BigDecimal(resource.getDeliveryPrice()))
                .discountSumm(new BigDecimal(resource.getDiscountSumm()))
                .build();
    }

    public Configuration fromResource(ConfigurationResource resource, Configuration entity) {
        entity.setCupCount(resource.getCupCount());
        entity.setDeliveryPrice(new BigDecimal(resource.getDeliveryPrice()));
        entity.setDiscountSumm(new BigDecimal(resource.getDiscountSumm()));
        return entity;
    }
}
