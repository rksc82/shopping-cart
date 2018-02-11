package com.rks.controller;

import com.rks.dto.OrderDto;
import com.rks.exceptions.ProductNotFoundException;
import com.rks.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE, value = "{id}")
    @ResponseBody
    public OrderDto order(@RequestBody OrderDto orderDto, @PathVariable Integer id) throws ProductNotFoundException {
        return orderService.createOrder(orderDto, id);
    }
}
