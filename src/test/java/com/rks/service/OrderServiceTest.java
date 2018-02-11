package com.rks.service;

import com.rks.dto.OrderDto;
import com.rks.exceptions.ProductNotFoundException;
import com.rks.model.Cart;
import com.rks.model.CartDetails;
import com.rks.model.CartOrder;
import com.rks.model.Product;
import com.rks.repository.CartRepository;
import com.rks.repository.OrderRepository;
import com.rks.repository.ProductRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class OrderServiceTest {

    @Mock
    private CartRepository cartRepository;

    @Mock
    private ProductRepository productRepository;

    @Mock
    private OrderRepository orderRepository;

    @InjectMocks
    private OrderService orderService;

    @Test
    public void createCartTest() throws Exception{

        OrderDto orderDto = new OrderDto("TestUser", "TestUser", "TestAddress", "TestContact","TestEmail","", 12);

        CartDetails cartDetails1 = new CartDetails(12,12);
        CartDetails cartDetails2 = new CartDetails(24,25);
        Cart cart = new Cart(12, new Double(80), Arrays.asList(cartDetails1, cartDetails2), "In Progress");

        Product product = new Product(12, "TestProduct", "TestDescription", 56, 64);

        CartOrder order = new CartOrder();
        when(productRepository.findOne(anyInt())).thenReturn(product);
        when(cartRepository.save(any(Cart.class))).thenReturn(cart);
        when(orderRepository.save(any(CartOrder.class))).thenReturn(order);
        when(cartRepository.findOne(anyInt())).thenReturn(cart);

        OrderDto actual = orderService.createOrder(orderDto, 12);

        assertEquals(actual, orderDto);
    }

    @Test(expected = ProductNotFoundException.class)
    public void createCartTest_throwsException() throws Exception{

        OrderDto orderDto = new OrderDto("TestUser", "TestUser", "TestAddress", "TestContact","TestEmail","", 12);

        CartDetails cartDetails1 = new CartDetails(12,12);
        CartDetails cartDetails2 = new CartDetails(24,25);
        Cart cart = new Cart(12, new Double(80), Arrays.asList(cartDetails1, cartDetails2), "In Progress");

        CartOrder order = new CartOrder();
        when(productRepository.findOne(anyInt())).thenThrow(new ProductNotFoundException("Product not in stock"));
        when(cartRepository.save(any(Cart.class))).thenReturn(cart);
        when(orderRepository.save(any(CartOrder.class))).thenReturn(order);
        when(cartRepository.findOne(anyInt())).thenReturn(cart);

        OrderDto actual = orderService.createOrder(orderDto, 12);

        assertEquals(actual, orderDto);
    }
}
