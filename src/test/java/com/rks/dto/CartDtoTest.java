package com.rks.dto;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class CartDtoTest {

    @Test
    public void CartDtoTest(){

        CartDetailsDto cartDetailsDto = new CartDetailsDto(12,12, "Testname", "TestDescription");
        CartDto cartDto = new CartDto(12, Arrays.asList(cartDetailsDto), 12d);

        assertEquals(cartDto.getCartId(), 12);
        assertEquals(cartDto.getTotal(), 12, 0);
        assertTrue(cartDto.getCartDetailsDtoList().contains(cartDetailsDto));
    }
}
