package com.fl.market.domain.dto;

import lombok.Data;

@Data
public class ProductDTO {

    private long productId;
    private String name;
    private int categoryId;
    private int price;
    private int stock;
    private boolean active;
    private CategoryDTO category;

}
