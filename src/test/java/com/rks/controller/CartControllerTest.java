package com.rks.controller;

import com.rks.dto.RequestCartDetailsDto;
import com.rks.dto.ResponseCartDetailsDto;

import com.rks.dto.ResponseCartDto;
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
    public void getCartsByUserId() throws Exception {
        when(cartService.findByUserId(12)).thenReturn(new ResponseCartDto(new ArrayList<ResponseCartDetailsDto>(), 20d));

        mvc.perform(get("/carts/12")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void updateCartByUserIdTest() throws Exception{

        ResponseCartDto responseCartDto = new ResponseCartDto(new ArrayList<ResponseCartDetailsDto>(), 12);
        when(cartService.updateCartByUserId(new ArrayList<RequestCartDetailsDto>(), 12)).thenReturn(responseCartDto);

        mvc.perform(put("/carts/12")
                .contentType(MediaType.APPLICATION_JSON)
                .content("[\n" +
                        "  {\n" +
                        "    \"productId\": 0,\n" +
                        "    \"productQuantity\": 0\n" +
                        "  }\n" +
                        "]"))
                .andExpect(status().isOk());
    }
}

