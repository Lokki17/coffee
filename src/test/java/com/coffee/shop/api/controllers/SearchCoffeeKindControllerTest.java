package com.coffee.shop.api.controllers;

import com.coffee.shop.api.resources.CoffeeKindResource;
import com.coffee.shop.dao.entity.CoffeeKind;
import org.apache.commons.lang.SerializationUtils;
import org.junit.Test;
import org.springframework.http.MediaType;

import static com.coffee.shop.TestData.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class SearchCoffeeKindControllerTest extends BaseControllerIntegrationTest {

    private static final String URL = "/search";


    @Test
    public void getByNameLike() throws Exception {
        CoffeeKind kind = testContext
                .kind(CAPUCHINO)
                .get();

        mvc.perform(get(URL)
        .param("name", kind.getName()))
                .andExpect(jsonPath("$").isArray())
                .andExpect(status().isOk());
    }

    @Test
    public void getByDescription() throws Exception {
        CoffeeKind kind = testContext
                .kind(CAPUCHINO)
                .get();

        mvc.perform(get(URL)
                .param("description", kind.getDescription()))
                .andExpect(jsonPath("$").isArray())
                .andExpect(status().isOk());
    }

}