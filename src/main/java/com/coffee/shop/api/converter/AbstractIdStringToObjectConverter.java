package com.coffee.shop.api.converter;

import com.coffee.shop.dao.exception.EntityNotFoundException;
import javaslang.control.Option;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public abstract class AbstractIdStringToObjectConverter<T> implements Converter<String, T> {

    public T convert(String id, Function<Long, Option<T>> func) {
        return func.apply(Long.valueOf(id))
                .getOrElseThrow(() -> new EntityNotFoundException("Entity with id " + id + "not found"));
    }
}
