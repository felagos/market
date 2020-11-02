package com.fl.market.domain.repository;

import com.fl.market.domain.dto.PurchaseDTO;

import java.util.List;
import java.util.Optional;

public interface PurchaseRepository {

    public List<PurchaseDTO> getAll();
    public Optional<List<PurchaseDTO>> getByClient(String clientId);
    public PurchaseDTO save(PurchaseDTO purchase);

}
