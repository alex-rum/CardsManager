package com.cards.manager.controllers.advice;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static org.springframework.http.HttpStatus.NOT_FOUND;


@RestControllerAdvice
public class ExceptionControllerAdvice {

    @ResponseStatus(NOT_FOUND)
    @ExceptionHandler(IllegalArgumentException.class)
    public void handleError(IllegalArgumentException e) {
    }
}
