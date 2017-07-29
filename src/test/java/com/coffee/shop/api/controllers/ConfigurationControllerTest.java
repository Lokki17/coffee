package com.coffee.shop.api.controllers;

import com.coffee.shop.dao.entity.Configuration;
import org.junit.Test;
import org.springframework.http.MediaType;

import static com.coffee.shop.TestData.CONFIGURATION;
import static com.coffee.shop.TestData.CONFIGURATION_RESOURCE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class ConfigurationControllerTest extends BaseControllerIntegrationTest {

    private static final String URL = "/config";

    private static final String URL_ITEM = URL + "/{id}";

    private static final String URL_HISTORY = URL + "/history";


    @Test
    public void getById() throws Exception {
        Configuration configuration = testContext
                .config(CONFIGURATION)
                .get();

        mvc.perform(get(URL_ITEM, configuration.getId()))
                .andExpect(jsonPath("$.id").isNotEmpty())
                .andExpect(jsonPath("$.cupCount").isNotEmpty())
                .andExpect(jsonPath("$.discountSumm").isNotEmpty())
                .andExpect(jsonPath("$.deliveryPrice").isNumber())
                .andExpect(status().isOk());
    }

    @Test
    public void getAll() throws Exception {
        testContext
                .config(CONFIGURATION)
                .get();

        mvc.perform(get(URL_HISTORY))
                .andExpect(jsonPath("$").isArray())
                .andExpect(status().isOk());
    }

    @Test
    public void create() throws Exception {

        mvc.perform(post(URL)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(json(CONFIGURATION_RESOURCE)))
                .andExpect(jsonPath("$.id").isNotEmpty())
                .andExpect(jsonPath("$.cupCount").isNotEmpty())
                .andExpect(jsonPath("$.discountSumm").isNotEmpty())
                .andExpect(jsonPath("$.deliveryPrice").isNumber())
                .andExpect(status().isCreated());
    }

    @Test
    public void getCurrent() throws Exception {

        testContext
                .config(CONFIGURATION)
                .get();

        mvc.perform(get(URL))
                .andExpect(jsonPath("$.id").isNotEmpty())
                .andExpect(jsonPath("$.cupCount").isNotEmpty())
                .andExpect(jsonPath("$.discountSumm").isNotEmpty())
                .andExpect(jsonPath("$.deliveryPrice").isNumber())
                .andExpect(status().isOk());
    }

    @Test
    public void getCurrentNotFound() throws Exception {

        mvc.perform(get(URL))
                .andExpect(status().isNotFound());
    }
}