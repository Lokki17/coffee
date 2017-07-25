package com.coffee.shop.dao.repository;

import com.coffee.shop.dao.entity.Order;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Random;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@JpaTest
public class OrderRepositoryTest extends AbstractRepositoryTest {

    @Autowired
    private OrderRepository repository;

    @Test
    public void findOneTest() {
        Order excepted = repository.findOne(order.getId());

        assertEquals(excepted, order);
    }

    @Test
    public void existsTest() {
        assertTrue(repository.exists(order.getId()));
        assertFalse(repository.exists(new Random().nextLong()));
    }

    @Test
    public void findAllTest() {
        List<Order> expected = repository.findAll();

        assertThat(expected, Matchers.containsInAnyOrder(order));
        assertTrue(expected.size() == 1);
    }

    @Test
    public void countTest() {
        assertTrue(repository.count() == 1L);
    }

}