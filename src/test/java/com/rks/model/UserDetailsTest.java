package com.rks.model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class UserDetailsTest {

    @Test
    public void userDetailsTest() {
        UserDetails userDetails = new UserDetails("TestName", "TestName", "TestAddress", "TestContact", "TestEmail", new Cart());

        assertEquals(userDetails.getUserFirstName(), "TestName");
        assertEquals(userDetails.getUserLastName(), "TestName");
        assertEquals(userDetails.getAddress(), "TestAddress");
        assertEquals(userDetails.getContact(), "TestContact");
        assertEquals(userDetails.getEmail(), "TestEmail");
    }
}
