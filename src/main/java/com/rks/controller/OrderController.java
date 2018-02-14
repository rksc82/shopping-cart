package com.rks.controller;

import com.rks.dto.RequestOrderDto;
import com.rks.dto.ResponseOrderDto;
import com.rks.exceptions.NotFoundException;
import com.rks.service.OrderService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @ApiOperation(value = "Create order from User Cart by UserId", response = ResponseOrderDto.class, responseContainer = "List")
    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE, value = "{userId}")
    public ResponseOrderDto orderForUser(@PathVariable int userId) throws NotFoundException {
        return orderService.createOrderForUser(userId);
    }

    @ApiOperation(value = "Create order for a guest", response = ResponseOrderDto.class, responseContainer = "List")
    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseOrderDto orderForGuest(@RequestBody RequestOrderDto requestOrderDto) throws NotFoundException {
        return orderService.createOrderForGuest(requestOrderDto);
    }
}
