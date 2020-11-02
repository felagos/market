package com.fl.market.integration;

import com.fl.market.TestConfig;
import com.fl.market.web.security.JWTUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.security.core.userdetails.User;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;

@TestConfig
public class CategoryIntegrationTest extends BaseIntegration {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void get_category_by_id() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/category/8").header("Authorization", TOKEN)).andExpect(MockMvcResultMatchers.status().isOk());
    }
}
