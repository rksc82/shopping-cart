package com.rks.dto;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class RequestOrderDtoTest {

    @Test
    public void orderDtoTest(){
        RequestOrderDto requestOrderDto =  new RequestOrderDto("TestUser", "TestUser", "TestAddress", "TestContact","TestEmail","", Arrays.asList());
        assertEquals(requestOrderDto.getUserFirstName(), "TestUser");
        assertEquals(requestOrderDto.getUserLastName(), "TestUser");
        assertEquals(requestOrderDto.getAddress(), "TestAddress");
        assertEquals(requestOrderDto.getContact(), "TestContact");
        assertEquals(requestOrderDto.getEmail(), "TestEmail");
    }
}
