package com.rks.dto;

import org.junit.Test;
import java.util.Arrays;
import static org.junit.Assert.assertEquals;

public class ResponseOrderDtoTest {

    @Test
    public void orderDtoTest() {
        ResponseOrderDto responseOrderDto = new ResponseOrderDto(12, new Double(12), Arrays.asList(), "TestUser", "TestUser", "TestAddress", "TestContact", "TestEmail", "");

        assertEquals(responseOrderDto.getUserFirstName(), "TestUser");
        assertEquals(responseOrderDto.getUserLastName(), "TestUser");
        assertEquals(responseOrderDto.getAddress(), "TestAddress");
        assertEquals(responseOrderDto.getContact(), "TestContact");
        assertEquals(responseOrderDto.getEmail(), "TestEmail");
    }
}
