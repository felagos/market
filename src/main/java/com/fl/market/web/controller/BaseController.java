package com.fl.market.web.controller;

import com.fl.market.web.response.ResponseController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class BaseController {

    protected <T> ResponseEntity okResponse(String message, T data) {
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseController(message, data));
    }

    protected ResponseEntity notFoundResponse(String message) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseController(message, null));
    }

    protected <T> ResponseEntity createdResponse(String message, T data) {
        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseController(message, data));
    }

}
