package com.rks.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ExceptionsHandler {
    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ShoppingCartErrorResponse handleProductNotFoundException(final NotFoundException ex) {
        return new ShoppingCartErrorResponse(HttpStatus.NOT_FOUND.toString(), ex.getMessage());
    }

    @ExceptionHandler(ShoppingCartException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ShoppingCartErrorResponse handleShoppingStoreException(final ShoppingCartException ex) {
        return new ShoppingCartErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.toString(), ex.getMessage());
    }
}