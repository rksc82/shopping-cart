package com.rks.controller;

import com.rks.Service.CartService;
import com.rks.dto.CartDto;
import com.rks.model.Cart;
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
    public List<Cart> getAll(){
        return cartService.findAll();
    }

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public CartDto createCart(@RequestBody CartDto cartDto){
        return cartService.createCart(cartDto);
    }

//    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE, value = "{id}")
//    @ResponseBody
//    public CartDto order(@PathVariable String id){
//        return cartService.order(id);
//    }
}
