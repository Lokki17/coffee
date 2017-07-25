package com.coffee.shop.dao.repository;

import com.coffee.shop.JpaTest;
import com.coffee.shop.dao.entity.Configuration;
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
public class ConfigurationRepositoryTest extends AbstractRepositoryTest {

    @Autowired
    private ConfigurationRepository repository;

    @Test
    public void findOneTest() {
        Configuration excepted = repository.findOne(configuration.getId());

        assertEquals(excepted, configuration);
    }

    @Test
    public void existsTest() {
        assertTrue(repository.exists(configuration.getId()));
        assertFalse(repository.exists(new Random().nextLong()));
    }

    @Test
    public void findAllTest() {
        List<Configuration> expected = repository.findAll();

        assertThat(expected, Matchers.containsInAnyOrder(configuration));
        assertTrue(expected.size() == 1);
    }

    @Test
    public void countTest() {
        assertTrue(repository.count() == 1L);
    }

}