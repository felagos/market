package com.fl.market.domain.dto;

import lombok.Data;

@Data
public class PurchaseItemDTO {

    private long productId;
    private int quantity;
    private double total;
    private boolean active;

}
