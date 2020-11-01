package com.fl.market.integration.controller;

import com.fl.market.BaseTest;
import com.fl.market.domain.service.ProductService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@AutoConfigureMockMvc
public class CategoryIntegrationTest extends BaseTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ProductService productService;

    @Test
    public void get_by_category_id() throws Exception {
        var response = mockMvc.perform(MockMvcRequestBuilders.get("/category/8")).andReturn();

        Assertions.assertEquals(response.getResponse().getStatus(), HttpStatus.OK.value());

    }

}
