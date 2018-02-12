package com.rks.model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ProductTest {

    @Test
    public void productTest() {

        Product product = new Product(12, "TestName", "TestDescription", 20d, 30);

        assertEquals(product.getProductId(), 12);
        assertEquals(product.getProductName(), "TestName");
        assertEquals(product.getDescription(), "TestDescription");
        assertEquals(product.getPrice(), 20, 0);
        assertEquals(product.getQuantity(), 30);
    }
}
