package com.rks.dto;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class RequestCartDetailsDtoTest {

    @Test
    public void requestCartDetailsDtoTest(){
        RequestCartDetailsDto requestCartDetailsDto = new RequestCartDetailsDto(12, 14);

        assertEquals(requestCartDetailsDto.getProductId(), 12);
        assertEquals(requestCartDetailsDto.getProductQuantity(), 14);
    }
}
