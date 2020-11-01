package com.fl.market.integration;

import com.fl.market.TestConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@TestConfig
public class CategoryIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void get_category_by_id() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/category/8")).andExpect(MockMvcResultMatchers.status().isOk());
    }
}
