package com.fl.market.domain.dto;

import lombok.Data;

@Data
public class CategoryDTO {

    private long categoryId;
    private String category;
    private boolean active;

}
