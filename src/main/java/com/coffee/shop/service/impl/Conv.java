package com.coffee.shop.service.impl;

import org.springframework.stereotype.Component;

import javax.persistence.AttributeConverter;

@Component
public class Conv implements AttributeConverter<Long, String>{

    @Override
    public String convertToDatabaseColumn(Long attribute) {
        return attribute.toString();
    }

    @Override
    public Long convertToEntityAttribute(String dbData) {
        return Long.parseLong(dbData);
    }
}
