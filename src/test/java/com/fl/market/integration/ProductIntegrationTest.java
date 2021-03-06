package com.fl.market.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fl.market.TestConfig;
import com.fl.market.domain.dto.ProductDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@TestConfig
public class ProductIntegrationTest extends BaseIntegration {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    private final ProductDTO PRODUCT = new ProductDTO();

    @BeforeEach
    public void init() {
        PRODUCT.setCategoryId(1);
        PRODUCT.setActive(true);
        PRODUCT.setName("Laptop");
        PRODUCT.setPrice(1111);
        PRODUCT.setStock(1111);
    }

    @Test
    public void get_all_products() throws Exception {
        var response = mockMvc.perform(MockMvcRequestBuilders.get("/products/").header("Authorization", TOKEN)).andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
    }

    @Test
    public void get_product_by_id() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/products/1").header("Authorization", TOKEN)).andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void get_product_by_id_not_founded() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/products/1000").header("Authorization", TOKEN)).andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    public void save_product() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.post("/products/").contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(PRODUCT)).header("Authorization", TOKEN)
        ).andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    public void update_product() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.put("/products/51").contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(PRODUCT)).header("Authorization", TOKEN)
        ).andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void delete_product() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/products/51").header("Authorization", TOKEN)).andExpect(MockMvcResultMatchers.status().isOk());
    }
}
