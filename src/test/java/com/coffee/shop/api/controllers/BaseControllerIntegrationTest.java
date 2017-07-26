package com.coffee.shop.api.controllers;

import com.coffee.shop.Application;
import com.coffee.shop.api.resources.CoffeeKindResource;
import com.coffee.shop.dao.entity.CoffeeKind;
import com.coffee.shop.dao.entity.Configuration;
import com.coffee.shop.dao.repository.CoffeeCupRepository;
import com.coffee.shop.dao.repository.CoffeeKindRepository;
import com.coffee.shop.dao.repository.ConfigurationRepository;
import com.coffee.shop.dao.repository.OrderRepository;
import com.coffee.shop.dao.search.SearchRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.io.ByteStreams;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import javaslang.control.Try;
import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultHandler;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.nio.charset.Charset;

@RunWith(SpringRunner.class)
@SpringBootTest(
        classes = {Application.class},
        properties = "spring.config.name=application,dao"
)
@ActiveProfiles("test")
public abstract class BaseControllerIntegrationTest {

    public static final MediaType APP_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

    protected MockMvc mvc;

    @Autowired
    public TestContext testContext;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private CoffeeCupRepository coffeeCupRepository;

    @Autowired
    private CoffeeKindRepository coffeeKindRepository;

    @Autowired
    private ConfigurationRepository configurationRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private SearchRepository searchRepository;

    @Before
    public void setup() {
        this.mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .alwaysDo(print())
                .build();
    }

    protected String json(Object object) {
        return Try.of(() -> objectMapper.writeValueAsString(object))
                .getOrElseThrow((Throwable e) -> new RuntimeException(e));
    }

    private ResultHandler print() {
        return result -> {
            System.out.println("Request body:");
            ByteStreams.copy(result.getRequest().getInputStream(), System.out);

            System.out.println("\n\nResponse:");
            System.out.println("Status: " + result.getResponse().getStatus());
            String content = result.getResponse().getContentAsString();
            if (content.length() > 0 && result.getResponse().getContentType().contains("json")) {
                try {
                    Gson gson = new GsonBuilder().setPrettyPrinting().create();
                    JsonElement element = new JsonParser().parse(content);
                    System.out.println(gson.toJson(element));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
    }

    @After
    public void tearDown() {
        orderRepository.deleteAll();
        coffeeCupRepository.deleteAll();
        coffeeKindRepository.deleteAll();
        configurationRepository.deleteAll();
        searchRepository.deleteAll();
        System.out.println();
    }

}
