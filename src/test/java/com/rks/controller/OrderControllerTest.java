package com.rks.controller;

import com.rks.dto.OrderDto;
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
    public void createCartTest() throws Exception{

        OrderDto orderDto = new OrderDto();
        when(orderService.createOrder(orderDto, 12)).thenReturn(orderDto);

        mvc.perform(post("/orders/12")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "  \"userFirstName\": \"string\",\n" +
                        "  \"userLastName\": \"string\",\n" +
                        "  \"address\": \"string\",\n" +
                        "  \"contact\": \"string\",\n" +
                        "  \"email\": \"string\",\n" +
                        "  \"createdDate\": \"string\",\n" +
                        "  \"transactionId\": 0\n" +
                        "}"))
                .andExpect(status().isOk());
    }
}
