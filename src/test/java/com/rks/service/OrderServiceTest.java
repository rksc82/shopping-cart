package com.rks.service;

import com.rks.dto.CartDetailsDto;
import com.rks.dto.CartDto;
import com.rks.dto.OrderDto;
import com.rks.exceptions.NotFoundException;
import com.rks.model.*;
import com.rks.repository.CartRepository;
import com.rks.repository.OrderRepository;
import com.rks.repository.ProductRepository;
import com.rks.repository.UserDetailsRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
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

    @Mock
    private UserDetailsRepository userDetailsRepository;

    @InjectMocks
    private OrderService orderService;

    @Test
    public void createOrderForUserTest() throws Exception{
        CartDetails cartDetails1 = new CartDetails(12,12);
        CartDetails cartDetails2 = new CartDetails(24,25);
        Cart cart = new Cart(12, new Double(80), 12, Arrays.asList(cartDetails1, cartDetails2));
        Product product = new Product(12, "TestProduct", "TestDescription", 56, 64);
        UserDetails userDetails = new UserDetails("TestName", "TestName", "TestAddress", "TestContact", "TestEmail", new Cart());
        OrderDetails orderDetails1 = new OrderDetails(12, 12);
        OrderDetails orderDetails2 = new OrderDetails(24, 25);
        CartOrder expected = new CartOrder(Arrays.asList(orderDetails1, orderDetails2), "TestName", "TestName", "TestAddress", "TestContact", "TestEmail", "12", new Double(12));

        when(productRepository.findOne(anyInt())).thenReturn(product);
        when(cartRepository.save(any(Cart.class))).thenReturn(cart);
        when(orderRepository.save(any(CartOrder.class))).thenReturn(expected);
        when(cartRepository.findByUserId(anyInt())).thenReturn(cart);
        when(userDetailsRepository.findOne(anyInt())).thenReturn(userDetails);

        CartOrder actual = orderService.createOrderForUser(12);

        assertEquals(actual.getUserFirstName(), expected.getUserFirstName());
        assertEquals(actual.getContact(), expected.getContact());
        assertEquals(actual.getUserLastName(), expected.getUserLastName());
        assertEquals(actual.getAddress(), expected.getAddress());
        assertEquals(actual.getEmail(), expected.getEmail());
    }

    @Test(expected = NotFoundException.class)
    public void createCartTest_throwsException() throws Exception{
        CartDetails cartDetails1 = new CartDetails(12,12);
        CartDetails cartDetails2 = new CartDetails(24,25);
        Cart cart = new Cart(12, new Double(80), 12, Arrays.asList(cartDetails1, cartDetails2));

        UserDetails user = new UserDetails();
        CartOrder order = new CartOrder();
        when(productRepository.findOne(anyInt())).thenThrow(new NotFoundException("Product not in stock"));
        when(cartRepository.save(any(Cart.class))).thenReturn(cart);
        when(orderRepository.save(any(CartOrder.class))).thenReturn(order);
        when(cartRepository.findByUserId(anyInt())).thenReturn(cart);
        when(userDetailsRepository.findOne(12)).thenReturn(user);

        orderService.createOrderForUser(12);
    }

    @Test
    public void createOrderForGuestTest() throws Exception{

        CartDetailsDto cartDetailsDto = new CartDetailsDto(12, 12,"TestName","TestDescription");
        CartDto cartDto = new CartDto(12, Arrays.asList(cartDetailsDto), 12);
        OrderDto orderDto = new OrderDto("TestName", "TestName", "TestAddress", "TestContact", "TestEmail", "", 12, cartDto);
        CartDetails cartDetails1 = new CartDetails(12,12);
        CartDetails cartDetails2 = new CartDetails(24,25);
        Cart cart = new Cart(12, new Double(80), 12, Arrays.asList(cartDetails1, cartDetails2));
        Product product = new Product(12, "TestProduct", "TestDescription", 56, 64);
        UserDetails userDetails = new UserDetails("TestName", "TestName", "TestAddress", "TestContact", "TestEmail", new Cart());
        OrderDetails orderDetails1 = new OrderDetails(12, 12);
        OrderDetails orderDetails2 = new OrderDetails(24, 25);
        CartOrder expected = new CartOrder(Arrays.asList(orderDetails1, orderDetails2), "TestName", "TestName", "TestAddress", "TestContact", "TestEmail", "12", new Double(12));

        when(productRepository.findOne(anyInt())).thenReturn(product);
        when(cartRepository.save(any(Cart.class))).thenReturn(cart);
        when(orderRepository.save(any(CartOrder.class))).thenReturn(expected);
        when(cartRepository.findByUserId(anyInt())).thenReturn(cart);
        when(userDetailsRepository.findOne(anyInt())).thenReturn(userDetails);

        CartOrder actual = orderService.createOrderForGuest(orderDto);

        assertEquals(actual.getUserFirstName(), expected.getUserFirstName());
        assertEquals(actual.getContact(), expected.getContact());
        assertEquals(actual.getUserLastName(), expected.getUserLastName());
        assertEquals(actual.getAddress(), expected.getAddress());
        assertEquals(actual.getEmail(), expected.getEmail());
    }

    @Test(expected = NotFoundException.class)
    public void createOrderForGuestTest_throwsNotFoundException() throws Exception {
        CartDetailsDto cartDetailsDto = new CartDetailsDto(12, 12,"TestName","TestDescription");
        CartDto cartDto = new CartDto(12, Arrays.asList(cartDetailsDto), 12);
        OrderDto orderDto = new OrderDto("TestName", "TestName", "TestAddress", "TestContact", "TestEmail", "", 12, cartDto);
        CartDetails cartDetails1 = new CartDetails(12,12);
        CartDetails cartDetails2 = new CartDetails(24,25);
        Cart cart = new Cart(12, new Double(80), 12, Arrays.asList(cartDetails1, cartDetails2));
        UserDetails userDetails = new UserDetails("TestName", "TestName", "TestAddress", "TestContact", "TestEmail", new Cart());
        OrderDetails orderDetails1 = new OrderDetails(12, 12);
        OrderDetails orderDetails2 = new OrderDetails(24, 25);
        CartOrder expected = new CartOrder(Arrays.asList(orderDetails1, orderDetails2), "TestName", "TestName", "TestAddress", "TestContact", "TestEmail", "12", new Double(12));

        when(productRepository.findOne(anyInt())).thenReturn(null);
        when(cartRepository.save(any(Cart.class))).thenReturn(cart);
        when(orderRepository.save(any(CartOrder.class))).thenReturn(expected);
        when(cartRepository.findByUserId(anyInt())).thenReturn(cart);
        when(userDetailsRepository.findOne(anyInt())).thenReturn(userDetails);

        orderService.createOrderForGuest(orderDto);
    }
}
