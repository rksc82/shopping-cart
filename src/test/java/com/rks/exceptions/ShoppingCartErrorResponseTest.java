package com.rks.exceptions;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class ShoppingCartErrorResponseTest {

    @Test
    public void shoppingCartErrorResponseTest(){
        ShoppingCartErrorResponse shoppingCartErrorResponse = new ShoppingCartErrorResponse("404", "Product not found");

        assertEquals(shoppingCartErrorResponse.getCode(), "404");
        assertEquals(shoppingCartErrorResponse.getMessage(), "Product not found");
    }
}
