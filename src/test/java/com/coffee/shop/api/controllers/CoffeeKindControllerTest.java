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

public class CoffeeKindControllerTest extends BaseControllerIntegrationTest {

    private static final String URL = "/kind";

    private static final String URL_ITEM = URL + "/{id}";


    @Test
    public void getById() throws Exception {
        CoffeeKind kind = testContext
                .kind(CAPUCHINO)
                .get();

        mvc.perform(get(URL_ITEM, kind.getId()))
                .andExpect(jsonPath("$.id").isNotEmpty())
                .andExpect(jsonPath("$.name").isNotEmpty())
                .andExpect(jsonPath("$.description").isNotEmpty())
                .andExpect(jsonPath("$.price").isNumber())
                .andExpect(status().isOk());
    }

    @Test
    public void getAll() throws Exception {
        testContext
                .kinds(CAPUCHINO, TURKISH, FRAPPE, MOKA, ESPRESSOO);

        mvc.perform(get(URL))
                .andExpect(jsonPath("$").isArray())
                .andExpect(status().isOk());
    }

    @Test
    public void create() throws Exception {

        mvc.perform(post(URL)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(json(CAPUCHINO_RESOURCE)))
                .andExpect(jsonPath("$.id").isNotEmpty())
                .andExpect(jsonPath("$.name").isNotEmpty())
                .andExpect(jsonPath("$.description").isNotEmpty())
                .andExpect(jsonPath("$.price").isNumber())
                .andExpect(status().isCreated());
    }

    @Test
    public void createAlreadyExists() throws Exception {
        testContext
                .kind(CAPUCHINO);

        mvc.perform(post(URL)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(json(CAPUCHINO_RESOURCE)))
                .andExpect(status().isConflict());
    }

    @Test
    public void update() throws Exception {
        CoffeeKind kind = testContext
                .kind(CAPUCHINO)
                .get();

        CoffeeKindResource resource = (CoffeeKindResource) SerializationUtils.clone(CAPUCHINO_RESOURCE);
        resource.setName("New capuchino");
        resource.setId(kind.getId());


        mvc.perform(put(URL_ITEM, kind.getId())
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(json(resource)))
                .andExpect(jsonPath("$.id").isNotEmpty())
                .andExpect(jsonPath("$.name").isNotEmpty())
                .andExpect(jsonPath("$.description").isNotEmpty())
                .andExpect(jsonPath("$.price").isNumber())
                .andExpect(status().isOk());
    }

    @Test
    public void deleteKind() throws Exception {
        CoffeeKind kind = testContext
                .kind(CAPUCHINO)
                .get();

        mvc.perform(delete(URL_ITEM, kind.getId()))
                .andExpect(status().isNoContent());

    }

}