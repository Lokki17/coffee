package com.coffee.shop.service.impl;

import com.coffee.shop.dao.entity.Configuration;
import com.coffee.shop.dao.entity.Order;
import com.coffee.shop.dao.exception.EntityNotFoundException;
import com.coffee.shop.dao.repository.CoffeeCupRepository;
import com.coffee.shop.dao.repository.OrderRepository;
import com.coffee.shop.service.ConfigurationService;
import com.coffee.shop.service.OrderService;
import com.coffee.shop.service.ScriptBeanGenerator;
import javaslang.control.Option;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author Lokki17
 * @since 22.07.2017
 */
@Service
@RequiredArgsConstructor(onConstructor = @__({@Autowired}))
public class OrderServiceImpl implements OrderService {

    @NotNull
    private final OrderRepository repository;

    @NotNull
    private final CoffeeCupRepository coffeeCupRepository;

    @NonNull
    private final ConfigurationService configurationService;

    @NotNull
    private final ScriptBeanGenerator generator;

    @Override
    public Option<Order> findById(Long id) {
        return Option.of(repository.findOne(id));
    }

    @Override
    public List<Order> findAll() {
        return repository.findAll();
    }

    @Override
    @Transactional
    public Order create(Order newEntity) {
        setPrice(newEntity);
        coffeeCupRepository.save(newEntity.getCups());

        return repository.save(newEntity);
    }

    @Override
    @Transactional
    public Order update(Order updatedEntity) {
        setPrice(updatedEntity);
        coffeeCupRepository.save(updatedEntity.getCups());

        return repository.save(updatedEntity);
    }

    @Override
    public void delete(Order entity) {
        repository.delete(entity);
    }

    private void setPrice(Order order) {
        Configuration currentConfig = configurationService.getLastConfig()
                .getOrElseThrow(() -> new EntityNotFoundException("Config not found"));
        generator.getGenerator().setPrice(order, currentConfig);
    }
}
