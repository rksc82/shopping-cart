package com.rks.model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class OrderDetailsTest {

    @Test
    public void orderDetailsTest() {
        OrderDetails orderDetails = new OrderDetails(12, 12);

        assertEquals(orderDetails.getProductId(), 12, 0);
        assertEquals(orderDetails.getQuantity(), 12, 0);
    }
}
