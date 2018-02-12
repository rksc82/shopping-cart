package com.rks.dto;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CartDetailsDtoTest {

    @Test
    public void CartDetailsTest(){
        CartDetailsDto cartDetailsDto = new CartDetailsDto(12, 12, "TestName", "TestDescription");

        assertEquals(cartDetailsDto.getProductId(), 12);
        assertEquals(cartDetailsDto.getProductQuantity(), 12);
        assertEquals(cartDetailsDto.getProductName(), "TestName");
        assertEquals(cartDetailsDto.getProductDescription(), "TestDescription");
    }
}
