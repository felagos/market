package com.fl.market.domain.dto;

import lombok.Data;

@Data
public class ErrorField {

    private String field;
    private String message;

    public ErrorField(String field, String message) {
        this.field = field;
        this.message = message;
    }
}
