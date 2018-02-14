package com.rks.dto;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ResponseCartDtoTest {

    @Test
    public void CartDtoTest(){
        ResponseCartDetailsDto responseCartDetailsDto = new ResponseCartDetailsDto(12,12, "Testname", "TestDescription");
        ResponseCartDto responseCartDto = new ResponseCartDto(Arrays.asList(responseCartDetailsDto), 12d);

        assertEquals(responseCartDto.getTotal(), 12, 0);
        assertTrue(responseCartDto.getItems().contains(responseCartDetailsDto));
    }
}
