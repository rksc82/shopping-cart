package com.rks.exceptions;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ExceptionHandlerTest {

    @Test
    public void HandleNotFoundException(){
        NotFoundException notFoundException = new NotFoundException("Product Not Found");
        ExceptionsHandler exceptionsHandler = new ExceptionsHandler();
        ShoppingCartErrorResponse actualResponse = exceptionsHandler.handleProductNotFoundException((notFoundException));

        ShoppingCartErrorResponse  expectedResponse = new ShoppingCartErrorResponse("404", "Product Not Found");

        assertEquals(actualResponse.getCode(), expectedResponse.getCode());
        assertEquals(actualResponse.getMessage(), expectedResponse.getMessage());
    }

    @Test
    public void HandleShoppingCartException(){
        ShoppingCartException shoppingCartException = new ShoppingCartException("Unknown Exception occured");
        ExceptionsHandler exceptionsHandler = new ExceptionsHandler();
        ShoppingCartErrorResponse actualResponse = exceptionsHandler.handleShoppingStoreException((shoppingCartException));

        ShoppingCartErrorResponse  expectedResponse = new ShoppingCartErrorResponse("500", "Unknown Exception occured");

        assertEquals(actualResponse.getCode(), expectedResponse.getCode());
        assertEquals(actualResponse.getMessage(), expectedResponse.getMessage());
    }
}
