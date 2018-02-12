package com.rks.controller;

import com.rks.dto.OrderDto;
import com.rks.exceptions.NotFoundException;
import com.rks.model.CartOrder;
import com.rks.service.OrderService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @ApiOperation(value = "Create order from User Cart by UserId", response = CartOrder.class, responseContainer = "List")
    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE, value = "{userId}")
    @ResponseBody
    public CartOrder orderForUser(@PathVariable int userId) throws NotFoundException {
        return orderService.createOrderForUser(userId);
    }

    @ApiOperation(value = "Create order for a guest", response = CartOrder.class, responseContainer = "List")
    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public CartOrder orderForGuest(@RequestBody OrderDto orderDto) throws NotFoundException {
        return orderService.createOrderForGuest(orderDto);
    }
}
