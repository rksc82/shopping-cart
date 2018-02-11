package com.rks.controller;

import com.rks.exceptions.ProductNotFoundException;
import com.rks.service.CartService;
import com.rks.dto.CartDto;
import com.rks.dto.OrderDto;
import com.rks.model.Cart;
import javassist.NotFoundException;
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

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE, value = "/{id}")
    @ResponseBody
    public Cart getCartsById(@PathVariable Integer id) throws ProductNotFoundException{
        return cartService.findById(id);
    }

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public CartDto createCart(@RequestBody CartDto cartDto) throws ProductNotFoundException{
        return cartService.createCart(cartDto);
    }

    @RequestMapping(method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public CartDto updateCart(@RequestBody CartDto cartDto) throws ProductNotFoundException{
        return cartService.updateCart(cartDto);
    }
}
