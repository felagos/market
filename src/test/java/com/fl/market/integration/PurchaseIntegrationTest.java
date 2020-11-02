package com.fl.market.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fl.market.TestConfig;
import com.fl.market.domain.dto.PurchaseDTO;
import com.fl.market.domain.dto.PurchaseItemDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDateTime;
import java.util.Arrays;

@TestConfig
public class PurchaseIntegrationTest extends BaseIntegration {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    @Test
    public void get_all_purchases() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/purchases/").header("Authorization", TOKEN)).andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void get_puchase_by_client() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/purchases/client/4546221").header("Authorization", TOKEN)).andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void get_puchase_by_client_wrong_params() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/purchases/client/4546221L").header("Authorization", TOKEN)).andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    public void save_purchase() throws Exception {
        var purchase = new PurchaseDTO();
        var item = new PurchaseItemDTO();

        item.setProductId(1);
        item.setActive(true);
        item.setQuantity(10);
        item.setTotal(3000);

        purchase.setClientId("4546221");
        purchase.setDate(LocalDateTime.now());
        purchase.setPaymentMethod("E");
        purchase.setComment("");
        purchase.setState("P");
        purchase.setItems(Arrays.asList(item));

        mockMvc.perform(
                MockMvcRequestBuilders.post("/purchases/").contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(purchase)).header("Authorization", TOKEN)
        ).andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    public void save_purchase_wrong_field() throws Exception {
        var purchase = new PurchaseDTO();
        var item = new PurchaseItemDTO();

        item.setProductId(1);
        item.setActive(true);
        item.setQuantity(10);
        item.setTotal(3000);

        purchase.setDate(LocalDateTime.now());
        purchase.setPaymentMethod("E");
        purchase.setComment("");
        purchase.setState("P");
        purchase.setItems(Arrays.asList(item));

        mockMvc.perform(
                MockMvcRequestBuilders.post("/purchases/").contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(purchase)).header("Authorization", TOKEN)
        ).andExpect(MockMvcResultMatchers.status().isBadRequest());
    }
}
