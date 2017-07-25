package com.coffee.shop.dao.repository;

import com.coffee.shop.JpaTest;
import com.coffee.shop.dao.entity.CoffeeKind;
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
public class CoffeeKindRepositoryTest extends AbstractRepositoryTest {

    @Autowired
    private CoffeeKindRepository repository;

    @Test
    public void findOneTest() {
        CoffeeKind excepted = repository.findOne(coffeeKind.getId());

        assertEquals(excepted, coffeeKind);
    }

    @Test
    public void existsTest() {
        assertTrue(repository.exists(coffeeKind.getId()));
        assertFalse(repository.exists(new Random().nextLong()));
    }

    @Test
    public void findAllTest() {
        List<CoffeeKind> expected = repository.findAll();

        assertThat(expected, Matchers.containsInAnyOrder(coffeeKind));
        assertTrue(expected.size() == 1);
    }

    @Test
    public void countTest() {
        assertTrue(repository.count() == 1L);
    }

}