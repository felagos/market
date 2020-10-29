package com.fl.market.integration.controller;

import com.fl.market.web.response.ResponseController;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CategoryIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @LocalServerPort
    private int PORT;

    private String URL;

    @BeforeEach
    public void init() {
        URL = "http://localhost:" + PORT;
    }

    @Test
    public void test() throws Exception {
        var response = restTemplate.getForEntity(URL + "/api/category/8", ResponseController.class);

        Assertions.assertEquals(response.getStatusCode(), HttpStatus.OK);
        Assertions.assertNotEquals(response.getBody().getData(), null);
    }

}
