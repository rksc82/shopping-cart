package com.rks.controller;

import com.rks.dto.OrderDto;
import com.rks.model.CartOrder;
import com.rks.service.OrderService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(OrderController.class)
public class OrderControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private OrderService orderService;

    @Test
    public void createOrderTestForGuest() throws Exception{
        OrderDto orderDto = new OrderDto();
        when(orderService.createOrderForGuest(orderDto)).thenReturn(new CartOrder());

        mvc.perform(post("/orders")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "  \"userFirstName\": \"testName\",\n" +
                        "  \"userLastName\": \"testName\",\n" +
                        "  \"address\": \"testAddress\",\n" +
                        "  \"contact\": \"testContact\",\n" +
                        "  \"email\": \"testEmail\",\n" +
                        "  \"createdDate\": \"testDate\",\n" +
                        "  \"transactionId\": 0,\n" +
                        "  \"cartDto\": {\n" +
                        "    \"cartDetailsDtoList\": [\n" +
                        "      {\n" +
                        "        \"productId\": 0,\n" +
                        "        \"productQuantity\": 0,\n" +
                        "        \"productName\": \"testName\",\n" +
                        "        \"productDescription\": \"testDescription\"\n" +
                        "      }\n" +
                        "    ],\n" +
                        "    \"cartId\": 0,\n" +
                        "    \"total\": 0\n" +
                        "  }\n" +
                        "}"))
                .andExpect(status().isOk());
    }

    @Test
    public void createOrderTestForUser() throws Exception{
        OrderDto orderDto = new OrderDto();
        when(orderService.createOrderForGuest(orderDto)).thenReturn(new CartOrder());

        mvc.perform(post("/orders/12")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
