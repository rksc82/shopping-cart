package com.rks.dto;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class UserDtoTest {

    @Test
    public void UserDtoTest(){
        UserDto userDto = new UserDto(12,"TestName","TestName","TestAddress","TestContact","TestEmail");

        assertEquals(userDto.getUserId(), 12, 0);
        assertEquals(userDto.getUserFirstName(), "TestName");
        assertEquals(userDto.getUserLastName(), "TestName");
        assertEquals(userDto.getAddress(), "TestAddress");
        assertEquals(userDto.getContact(), "TestContact");
        assertEquals(userDto.getEmail(), "TestEmail");
    }
}
