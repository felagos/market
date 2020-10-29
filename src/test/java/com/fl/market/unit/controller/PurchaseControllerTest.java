package com.fl.market.unit.controller;

import com.fl.market.BaseTest;
import com.fl.market.domain.Purchase;
import com.fl.market.domain.service.PurchaseService;
import com.fl.market.web.controller.PurchaseController;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;


public class PurchaseControllerTest extends BaseTest {

    @Mock
    private PurchaseService service;

    @InjectMocks
    private PurchaseController controller;

    private final List<Purchase> PURCHASES = Arrays.asList(new Purchase());
    private final Purchase PURCHASE = new Purchase();
    private final String CLIENT_ID = "11111";

    @Test
    public void get_all_purchase() {
        Mockito.when(service.getAll()).thenReturn(PURCHASES);
        var response = controller.getAll();

        Assertions.assertEquals(response.getStatusCode(), HttpStatus.OK);
        Assertions.assertEquals(response.getBody().getData(), PURCHASES);
    }

    @Test
    public void get_purchases_by_client_id_case_200() {
        Mockito.when(service.getByClient(anyString())).thenReturn(Optional.of(PURCHASES));
        var response = controller.getByClient(CLIENT_ID);

        Assertions.assertEquals(response.getStatusCode(), HttpStatus.OK);
        Assertions.assertEquals(response.getBody().getData(), PURCHASES);
    }

    @Test
    public void get_purchases_by_client_id_case_404() {
        Mockito.when(service.getByClient(anyString())).thenReturn(Optional.empty());
        var response = controller.getByClient(CLIENT_ID);

        Assertions.assertEquals(response.getStatusCode(), HttpStatus.NOT_FOUND);
    }

    @Test
    public void save_purchase() {
        Mockito.when(service.save(any(Purchase.class))).thenReturn(PURCHASE);
        var response = controller.save(PURCHASE);

        Assertions.assertEquals(response.getStatusCode(), HttpStatus.CREATED);
        Assertions.assertEquals(response.getBody().getData(), PURCHASE);
    }

}
