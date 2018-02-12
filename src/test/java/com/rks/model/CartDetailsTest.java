package com.rks.model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CartDetailsTest {

    @Test
    public void CartDetailsTest(){
        CartDetails cartDetails = new CartDetails(12, 12);

        assertEquals(cartDetails.getProductId(), 12, 0);
        assertEquals(cartDetails.getQuantity(), 12, 0);
    }
}
