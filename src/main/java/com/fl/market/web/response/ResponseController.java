package com.fl.market.web.response;

import lombok.Data;

@Data
public class ResponseController<T> {

    private String message;
    private T data;

    public ResponseController(String message, T data) {
        this.message = message;
        this.data = data;
    }
}
