package com.rks.model;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class CartOrderTest {

    @Test
    public void cartOrderTest() {
        OrderDetails orderDetails = new OrderDetails(12, 12);
        CartOrder cartOrder = new CartOrder(Arrays.asList(orderDetails), "TestUser", "TestUser", "TestAddress", "TestContact", "TestEmail", "", 12d);

        assertEquals(cartOrder.getUserFirstName(), "TestUser");
        assertEquals(cartOrder.getUserLastName(), "TestUser");
        assertEquals(cartOrder.getAddress(), "TestAddress");
        assertEquals(cartOrder.getEmail(), "TestEmail");
        assertEquals(cartOrder.getContact(), "TestContact");
        assertEquals(cartOrder.getTotal(), 12, 0);
        assertTrue(cartOrder.getOrderDetails().contains(orderDetails));
    }
}
