package com.rks.dto;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class RequestOrderDetailsDtoTest {

    @Test
    public void requestOrderDetailsDtoTest() {
        RequestOrderDetailsDto requestOrderDetailsDto = new RequestOrderDetailsDto(12, 14);

        assertEquals(requestOrderDetailsDto.getProductId(), 12);
        assertEquals(requestOrderDetailsDto.getProductQuantity(), 14);
    }
}
