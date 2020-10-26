package com.fl.market.controller;

import com.fl.market.BaseTest;
import com.fl.market.domain.Purchase;
import com.fl.market.domain.service.PurchaseService;
import com.fl.market.web.controller.PurchaseController;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;

import java.util.Arrays;
import java.util.List;

public class PurchaseControllerTest extends BaseTest {

    @Mock
    private PurchaseService service;

    @InjectMocks
    private PurchaseController controller;

    private final List<Purchase> PURCHASES = Arrays.asList(new Purchase());

    @Test
    public void get_all_purchase() {
        Mockito.when(service.getAll()).thenReturn(PURCHASES);
        var response = controller.getAll();

        Assert.assertEquals(response.getStatusCode(), HttpStatus.OK);
        Assert.assertEquals(response.getBody().getData(), PURCHASES);
    }


}
