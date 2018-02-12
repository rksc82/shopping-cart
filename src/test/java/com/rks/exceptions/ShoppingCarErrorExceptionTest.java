package com.rks.exceptions;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ShoppingCarErrorExceptionTest {

    @Test
    public void ShoppingCartErrorException() {
        assertEquals("Unknown Exception occured while updating items", new NotFoundException("Unknown Exception occured while updating items").getMessage());
    }

}
