package com.fl.market;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
class MarketApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    @Test
    public void get_category_by_id() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/category/8")).andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void get_all_products() throws Exception {
        var response = mockMvc.perform(MockMvcRequestBuilders.get("/products/")).andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
    System.out.println("response "+response.getResponse().getContentAsString());
    }

    @Test
    public void get_product_by_id() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/products/1")).andExpect(MockMvcResultMatchers.status().isOk());
    }

}