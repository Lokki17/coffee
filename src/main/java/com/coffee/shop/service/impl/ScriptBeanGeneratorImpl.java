package com.coffee.shop.service.impl;

import com.coffee.shop.service.PriceGenerator;
import com.coffee.shop.service.ScriptBeanGenerator;
import com.coffee.shop.service.exception.FileNotFoundException;
import groovy.lang.GroovyClassLoader;
import javaslang.control.Try;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;

/**
 * @author Lokki17
 * @since 23.07.2017
 */
@Service
@RequiredArgsConstructor(onConstructor = @__({@Autowired}))
public class ScriptBeanGeneratorImpl implements ScriptBeanGenerator {

    @Value("${coffee.shop.script-name}")
    private String fileName;

    @Override
    public PriceGenerator getGenerator() {
        GroovyClassLoader loader = new GroovyClassLoader();
        Class clazz = Try.of(() -> loader.parseClass(new File(this.getClass().getClassLoader().getResource(fileName).toURI())))
                .getOrElseThrow(() -> new FileNotFoundException("File " + fileName + " not found"));

        return (PriceGenerator) Try.of((Try.CheckedSupplier<Object>) clazz::newInstance)
                .getOrElseThrow(() -> new RuntimeException("Can't get class instance"));
    }
}
