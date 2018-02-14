package com.rks.dto;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ResponseOrderDetailsDtoTest {

    @Test
    public void responseOrderDetailsDtoTest(){
        ResponseOrderDetailsDto responseOrderDetailsDto = new ResponseOrderDetailsDto(12, 12, "TestName", "TestDescription",10d );

        assertEquals(responseOrderDetailsDto.getProductId(), 12);
        assertEquals(responseOrderDetailsDto.getQuantity(), 12);
        assertEquals(responseOrderDetailsDto.getProductName(), "TestName");
        assertEquals(responseOrderDetailsDto.getProductDescription(), "TestDescription");
        assertEquals(responseOrderDetailsDto.getPrice(), 10d, 0);
    }
}
