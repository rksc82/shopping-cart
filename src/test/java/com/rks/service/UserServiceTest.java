package com.rks.service;

import com.rks.dto.RequestUserDto;
import com.rks.dto.ResponseUserDto;
import com.rks.model.Cart;
import com.rks.model.UserDetails;
import com.rks.repository.UserDetailsRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)

public class UserServiceTest {

    @Mock
    private UserDetailsRepository userDetailsRepository;

    @InjectMocks
    private UserService userService;

    @Test
    public void createUser(){
        RequestUserDto requestUserDto = new RequestUserDto("TestName" , "TestName", "TestAddress", "TestContact", "TestEmail");

        ResponseUserDto expected = new ResponseUserDto(12,"TestName" , "TestName", "TestAddress", "TestContact", "TestEmail");
        UserDetails userDetails = new UserDetails("TestName", "TestName", "TestAddress", "TestContact", "TestEmail", new Cart());

        when(userDetailsRepository.save(any(UserDetails.class))).thenReturn(userDetails);
        ResponseUserDto actual = userService.createUser(requestUserDto);

        assertEquals(actual.getUserFirstName(), expected.getUserFirstName());
        assertEquals(actual.getUserLastName(), expected.getUserLastName());
        assertEquals(actual.getAddress(), expected.getAddress());
        assertEquals(actual.getContact(), expected.getContact());
        assertEquals(actual.getEmail(), expected.getEmail());
    }

    @Test
    public void getAllUsers(){
        UserDetails userDetails = new UserDetails("TestName", "TestName", "TestAddress", "TestContact", "TestEmail", new Cart());

        when(userDetailsRepository.findAll()).thenReturn(Arrays.asList(userDetails));
        List<UserDetails> actual = userService.findAll();

        assertTrue(actual.contains(userDetails));
    }
}
