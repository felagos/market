package com.fl.market.domain.repository;

import com.fl.market.domain.Purchase;

import java.util.List;
import java.util.Optional;

public interface PurchaseRespository {

    public List<Purchase> getAll();
    public Optional<List<Purchase>> getByClient(String clientId);
    public Purchase save(Purchase purchase);

}
