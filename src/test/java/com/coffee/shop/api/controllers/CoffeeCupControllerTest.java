package com.coffee.shop.api.controllers;

import com.coffee.shop.dao.entity.CoffeeCup;
import com.coffee.shop.dao.entity.Order;
import org.junit.Test;

import java.util.Arrays;

import static com.coffee.shop.TestData.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class CoffeeCupControllerTest extends BaseControllerIntegrationTest {

    private static final String URL = "/cup";

    private static final String URL_ITEM = URL + "/{id}";

    private static final String URL_ORDER = URL + "/orders/{orderId}";

    @Test
    public void getById() throws Exception {
        CoffeeCup cup = testContext
                .cup(CUP_CAPUCHINO)
                .get();

        mvc.perform(get(URL_ITEM, cup.getId()))
                .andExpect(jsonPath("$.id").isNotEmpty())
                .andExpect(jsonPath("$.coffeeKind").isNotEmpty())
                .andExpect(jsonPath("$.count").isNotEmpty())
                .andExpect(status().isOk());
    }

    @Test
    public void getAll() throws Exception {
        testContext
                .cups(CUP_CAPUCHINO, CUP_ESPRESSOO, CUP_FRAPPE, CUP_MOKA, CUP_TURKISH);

        mvc.perform(get(URL))
                .andExpect(jsonPath("$").isArray());
    }

    @Test
    public void getAllByOrder() throws Exception {
        Order order = testContext
                .order(ORDER_2, CONFIGURATION)
                .get();

        mvc.perform(get(URL_ORDER, order.getId()))
                .andExpect(jsonPath("$").isArray());
    }
}