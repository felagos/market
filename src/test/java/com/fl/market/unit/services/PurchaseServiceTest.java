package com.fl.market.unit.services;

import com.fl.market.BaseTest;
import com.fl.market.domain.Purchase;
import com.fl.market.domain.repository.PurchaseRepository;
import com.fl.market.domain.service.PurchaseService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class PurchaseServiceTest extends BaseTest {

    @Mock
    private PurchaseRepository purchaseRepository;

    @InjectMocks
    private PurchaseService purchaseService;

    private final List<Purchase> PURCHASES = Arrays.asList(new Purchase());
    private final Purchase PURCHASE = new Purchase();
    private final String CLIENT_ID = "1";

    @BeforeEach
    public void setup() {
        PURCHASE.setClientId(CLIENT_ID);
        PURCHASE.setComment("comentario");
        PURCHASE.setClientId("1");
        PURCHASE.setDate(LocalDateTime.now());
        PURCHASE.setPaymentMethod("E");
        PURCHASE.setState("p");
        PURCHASE.setItems(new ArrayList<>());
    }

    @Test
    public void get_all_purhcases() {
        Mockito.when(purchaseRepository.getAll()).thenReturn(PURCHASES);
        var response = purchaseService.getAll();

        Assertions.assertEquals(response.size(), PURCHASES.size());
        Assertions.assertEquals(response, PURCHASES);
    }

    @Test
    public void save_purchase() {
        Mockito.when(purchaseRepository.save(PURCHASE)).thenReturn(PURCHASE);
        var response = purchaseService.save(PURCHASE);

        Assertions.assertEquals(response, PURCHASE);
    }

    @Test
    public void get_purchases_by_client_id() {
        Mockito.when(purchaseRepository.getByClient(CLIENT_ID)).thenReturn(Optional.of(PURCHASES));
        var response = purchaseService.getByClient(CLIENT_ID);

        Assertions.assertEquals(response.get(), PURCHASES);
    }

    @Test
    public void get_purchases_by_client_id_not_founded() {
        Mockito.when(purchaseRepository.getByClient(CLIENT_ID)).thenReturn(Optional.empty());
        var response = purchaseService.getByClient(CLIENT_ID);

        Assertions.assertEquals(response.isEmpty(), true);
    }

}
