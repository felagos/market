package com.fl.market.integration;

import com.fl.market.TestConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@TestConfig
public class PingIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void doPing() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/ping/")).andExpect(MockMvcResultMatchers.status().isOk());
    }

}
