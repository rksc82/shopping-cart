package com.rks.controller;

import com.rks.dto.UserDto;
import com.rks.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private UserService userService;

    @Test
    public void userCreateTest() throws Exception{

        UserDto userDto = new UserDto();
        when(userService.createUser(userDto)).thenReturn(userDto);

        mvc.perform(post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "  \"address\": \"testAddress\",\n" +
                        "  \"contact\": \"testContact\",\n" +
                        "  \"email\": \"testEmail\",\n" +
                        "  \"userFirstName\": \"testUser\",\n" +
                        "  \"userId\": 0,\n" +
                        "  \"userLastName\": \"test\"\n" +
                        "}"))
                .andExpect(status().isOk());
    }

    @Test
    public void userGetAllTest() throws Exception{

        UserDto userDto = new UserDto();
        when(userService.createUser(userDto)).thenReturn(userDto);

        mvc.perform(get("/users")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
