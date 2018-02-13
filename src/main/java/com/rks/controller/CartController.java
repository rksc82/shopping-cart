package com.rks.controller;

import com.rks.exceptions.NotFoundException;
import com.rks.service.CartService;
import com.rks.dto.CartDto;
import com.rks.model.Cart;
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

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<Cart> getCarts(){
        return cartService.findAll();
    }

    @ApiOperation(value = "Query User Cart by UserId", response = CartDto.class, responseContainer = "List")
    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE, value = "/{userId}")
    @ResponseBody
    public CartDto getCartByUserId(@PathVariable Integer userId) throws NotFoundException {
        return cartService.findByUserId(userId);
    }

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public CartDto createCart(@RequestBody CartDto cartDto) throws NotFoundException {
        return cartService.createCart(cartDto);
    }

    @ApiOperation(value = "Update Cart values UserId", response = CartDto.class, responseContainer = "List")
    @RequestMapping(method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE, value = "/{userId}")
    @ResponseBody
    public CartDto updateCartByUserId(@RequestBody CartDto cartDto, @PathVariable int userId) throws NotFoundException {
        return cartService.updateCart(cartDto ,userId);
    }
}
