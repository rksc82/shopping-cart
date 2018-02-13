package com.rks.dto;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ResponseUserDtoTest {

    @Test
    public void UserDtoTest(){
        ResponseUserDto responseUserDto = new ResponseUserDto(12,"TestName","TestName","TestAddress","TestContact","TestEmail");

        assertEquals(responseUserDto.getUserId(), 12, 0);
        assertEquals(responseUserDto.getUserFirstName(), "TestName");
        assertEquals(responseUserDto.getUserLastName(), "TestName");
        assertEquals(responseUserDto.getAddress(), "TestAddress");
        assertEquals(responseUserDto.getContact(), "TestContact");
        assertEquals(responseUserDto.getEmail(), "TestEmail");
    }
}
