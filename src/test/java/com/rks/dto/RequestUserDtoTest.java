package com.rks.dto;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class RequestUserDtoTest {

    @Test
    public void requestUserDtoTest() {
        RequestUserDto requestUserDto = new RequestUserDto("TestName", "Test", "TestAddress", "TestContact", "TestEmail");

        assertEquals(requestUserDto.getUserFirstName(), "TestName");
        assertEquals(requestUserDto.getUserLastName(), "Test");
        assertEquals(requestUserDto.getEmail(), "TestEmail");
        assertEquals(requestUserDto.getAddress(), "TestAddress");
        assertEquals(requestUserDto.getContact(), "TestContact");
    }
}
