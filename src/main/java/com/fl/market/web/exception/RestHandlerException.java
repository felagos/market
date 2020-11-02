package com.fl.market.web.exception;

import com.fl.market.domain.dto.ErrorField;
import com.fl.market.web.response.ApiErrorResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolationException;
import java.util.*;
import java.util.stream.Collectors;

@ControllerAdvice
public class RestHandlerException extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatus status, WebRequest request) {

        var errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(x -> new ErrorField(x.getField(), x.getDefaultMessage()))
                .collect(Collectors.toList());

        var apiError = new ApiErrorResponse<List<ErrorField>>(errors, ex.getStackTrace()[0].getClassName(), status.BAD_REQUEST);

        return handleExceptionInternal(ex, apiError, headers, status.BAD_REQUEST, request);
    }

    @ExceptionHandler(value = {ConstraintViolationException.class})
    protected ResponseEntity<Object> handleConstraintException(ConstraintViolationException ex, WebRequest request) {
        var messages = ex.getMessage().split(", ");
        var apiError = new ApiErrorResponse<String[]>(messages, ex.getStackTrace()[0].getClassName(), HttpStatus.BAD_REQUEST);

        return handleExceptionInternal(ex, apiError, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

}
