package com.thoughtworks.springbootemployee.advice;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalControllerAdvice {

    @ExceptionHandler
    public ErrorMessage handleBadRequest(IllegalArgumentException exception) {
        return new ErrorMessage(exception.getMessage(), HttpStatus.BAD_REQUEST.name());
    }
}
