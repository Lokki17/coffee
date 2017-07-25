package com.coffee.shop.dao.repository;

import com.coffee.shop.JpaTest;
import com.coffee.shop.dao.entity.CoffeeCup;
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
public class CoffeeCupRepositoryTest extends AbstractRepositoryTest {

    @Autowired
    private CoffeeCupRepository repository;

    @Test
    public void findOneTest() {
        CoffeeCup excepted = repository.findOne(coffeeCup.getId());

        assertEquals(excepted, coffeeCup);
    }

    @Test
    public void existsTest() {
        assertTrue(repository.exists(coffeeCup.getId()));
        assertFalse(repository.exists(new Random().nextLong()));
    }

    @Test
    public void findAllTest() {
        List<CoffeeCup> expected = repository.findAll();

        assertThat(expected, Matchers.containsInAnyOrder(coffeeCup));
        assertTrue(expected.size() == 1);
    }

    @Test
    public void countTest() {
        assertTrue(repository.count() == 1L);
    }

}