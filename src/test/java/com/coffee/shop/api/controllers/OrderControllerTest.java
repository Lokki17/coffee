package com.coffee.shop.api.controllers;

import com.coffee.shop.dao.entity.Order;
import org.junit.Test;
import org.springframework.http.MediaType;

import static com.coffee.shop.TestData.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class OrderControllerTest extends BaseControllerIntegrationTest {

    private static final String URL = "/order";

    private static final String URL_ITEM = URL + "/{id}";


    @Test
    public void getById() throws Exception {
        Order kind = testContext
                .config(CONFIGURATION)
                .and()
                .order(ORDER_1)
                .get();

        mvc.perform(get(URL_ITEM, kind.getId()))
                .andExpect(jsonPath("$.id").isNotEmpty())
                .andExpect(jsonPath("$.userName").isNotEmpty())
                .andExpect(jsonPath("$.address").isNotEmpty())
                .andExpect(jsonPath("$.cups").isArray())
                .andExpect(status().isOk());
    }

    @Test
    public void getAll() throws Exception {
        testContext
                .config(CONFIGURATION)
                .and()
                .orders(ORDER_1, ORDER_2);

        mvc.perform(get(URL))
                .andExpect(jsonPath("$").isArray())
                .andExpect(status().isOk());
    }

    @Test
    public void create() throws Exception {
        testContext
                .config(CONFIGURATION)
                .and()
                .kind(CAPUCHINO)
                .get();

        mvc.perform(post(URL)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(json(ORDER_1_RESOURCE)))
                .andExpect(jsonPath("$.id").isNotEmpty())
                .andExpect(jsonPath("$.userName").isNotEmpty())
                .andExpect(jsonPath("$.address").isNotEmpty())
                .andExpect(jsonPath("$.price").isNotEmpty())
                .andExpect(jsonPath("$.cups").isArray())
                .andExpect(status().isCreated());
    }

    @Test
    public void deleteKind() throws Exception {
        Order order = testContext
                .config(CONFIGURATION)
                .and()
                .order(ORDER_1)
                .get();

        mvc.perform(delete(URL_ITEM, order.getId()))
                .andExpect(status().isNoContent());

    }
}