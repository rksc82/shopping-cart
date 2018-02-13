package com.rks.dto;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ResponseCartDetailsDtoTest {

    @Test
    public void CartDetailsTest(){
        ResponseCartDetailsDto responseCartDetailsDto = new ResponseCartDetailsDto(12, 12, "TestName", "TestDescription");

        assertEquals(responseCartDetailsDto.getProductId(), 12);
        assertEquals(responseCartDetailsDto.getProductQuantity(), 12);
        assertEquals(responseCartDetailsDto.getProductName(), "TestName");
        assertEquals(responseCartDetailsDto.getProductDescription(), "TestDescription");
    }
}
