package com.fl.market.integration.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BaseIntegrationTest {

    @Autowired
    protected TestRestTemplate restTemplate;

    @LocalServerPort
    protected int PORT;

    protected String createURLWithPort(String uri) {
        return new StringBuilder("http://localhost:").append(PORT).append(uri).toString();
    }

}
