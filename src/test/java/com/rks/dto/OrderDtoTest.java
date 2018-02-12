package com.rks.dto;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class OrderDtoTest {

    @Test
    public void orderDtoTest(){

        OrderDto orderDto =  new OrderDto("TestUser", "TestUser", "TestAddress", "TestContact","TestEmail","", 12 ,new CartDto());
        assertEquals(orderDto.getUserFirstName(), "TestUser");
        assertEquals(orderDto.getUserLastName(), "TestUser");
        assertEquals(orderDto.getAddress(), "TestAddress");
        assertEquals(orderDto.getContact(), "TestContact");
        assertEquals(orderDto.getEmail(), "TestEmail");
    }
}
