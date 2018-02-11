package com.rks.controller;

import com.rks.dto.CartDetailsDto;
import com.rks.dto.CartDto;

import com.rks.model.Cart;
import com.rks.model.CartDetails;
import com.rks.service.CartService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;

import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@WebMvcTest(CartController.class)
public class CartControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean private CartService cartService;

    @Test
    public void createCartTest() throws Exception{

        CartDto cartDto = new CartDto(12, new ArrayList<CartDetailsDto>(), 12);
        when(cartService.createCart(cartDto)).thenReturn(cartDto);

        mvc.perform(post("/carts")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "  \"cartId\": 0,\n" +
                        "  \"cartDetailsDtoList\": [\n" +
                        "    {\n" +
                        "      \"productId\": 0,\n" +
                        "      \"productQuantity\": 0\n" +
                        "    }\n" +
                        "  ],\n" +
                        "  \"total\": 0\n" +
                        "}"))
                .andExpect(status().isOk());
    }

    @Test
    public void getCarts() throws Exception {

        when(cartService.findAll()).thenReturn(new ArrayList<Cart>());

        mvc.perform(get("/carts")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void getCartsById() throws Exception {

        when(cartService.findById(12)).thenReturn(new Cart(new Double(12), new ArrayList<CartDetails>(), "In Progress"));

        mvc.perform(get("/carts/12")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void updateCartTest() throws Exception{

        CartDto cartDto = new CartDto(12, new ArrayList<CartDetailsDto>(), 12);
        when(cartService.createCart(cartDto)).thenReturn(cartDto);

        mvc.perform(put("/carts")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "  \"cartId\": 0,\n" +
                        "  \"cartDetailsDtoList\": [\n" +
                        "    {\n" +
                        "      \"productId\": 0,\n" +
                        "      \"productQuantity\": 0\n" +
                        "    }\n" +
                        "  ],\n" +
                        "  \"total\": 0\n" +
                        "}"))
                .andExpect(status().isOk());
    }
}

