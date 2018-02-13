package com.rks.controller;

import com.rks.dto.RequestUserDto;
import com.rks.dto.ResponseUserDto;
import com.rks.model.UserDetails;
import com.rks.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @ApiOperation(value = "Create user", response = ResponseUserDto.class, responseContainer = "List")
    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseUserDto createUser(@RequestBody RequestUserDto requestUserDto) {
        return userService.createUser(requestUserDto);
    }

    @ApiOperation(value = "Get list of all users", response = UserDetails.class, responseContainer = "List")
    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<UserDetails> getAllUsers() {
        return userService.findAll();
    }
}
