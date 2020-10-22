package com.fl.market.domain;

import lombok.Data;

@Data
public class PurchaseItem {

    private long productId;
    private int quantity;
    private double total;
    private boolean active;

}
