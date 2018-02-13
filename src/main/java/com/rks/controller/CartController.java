package com.rks.controller;

import com.rks.dto.RequestCartDetailsDto;
import com.rks.dto.ResponseCartDto;
import com.rks.exceptions.NotFoundException;
import com.rks.service.CartService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/carts")
public class CartController {

    @Autowired
    private CartService cartService;

    @ApiOperation(value = "Query User Cart by UserId", response = ResponseCartDto.class, responseContainer = "List")
    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE, value = "/{userId}")
    @ResponseBody
    public ResponseCartDto getCartByUserId(@PathVariable Integer userId) throws NotFoundException {
        return cartService.findByUserId(userId);
    }

    @ApiOperation(value = "Update Cart values UserId", response = RequestCartDetailsDto.class, responseContainer = "List")
    @RequestMapping(method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE, value = "/{userId}")
    @ResponseBody
    public ResponseCartDto updateCartByUserId(@RequestBody List<RequestCartDetailsDto> requestCartDetailsDtoList, @PathVariable int userId) throws NotFoundException {
        return cartService.updateCartByUserId(requestCartDetailsDtoList, userId);
    }
}
