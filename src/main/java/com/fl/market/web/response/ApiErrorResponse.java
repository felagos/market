package com.fl.market.web.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
public class ApiErrorResponse<T> {

    private T message;
    private final LocalDateTime dateTime = LocalDateTime.now();
    private String exception;
    private HttpStatus code;

    public ApiErrorResponse(T message, String exception, HttpStatus code) {
        this.message = message;
        this.exception = exception;
        this.code = code;
    }
}
