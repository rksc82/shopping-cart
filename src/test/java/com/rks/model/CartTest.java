package com.rks.model;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class CartTest {

    @Test
    public void CartTest() {
        CartDetails cartDetails = new CartDetails(12, 12);
        Cart cart = new Cart(new Double(12), Arrays.asList(cartDetails));

        assertEquals(cart.getTotal(), 12, 0);
        assertTrue(cart.getCartDetails().contains(cartDetails));
    }
}
