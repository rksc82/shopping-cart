package com.rks.service;

import com.rks.dto.*;
import com.rks.exceptions.NotFoundException;
import com.rks.exceptions.ShoppingCartException;
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

    @Mock
    private UserDetailsRepository userDetailsRepository;

    @InjectMocks
    private OrderService orderService;

    @Test
    public void createOrderForUserTest() throws Exception{
        CartDetails cartDetails1 = new CartDetails(12,12);
        CartDetails cartDetails2 = new CartDetails(24,25);
        Cart cart = new Cart(12, new Double(80),Arrays.asList(cartDetails1, cartDetails2), new UserDetails());

        Product product = new Product(12, "TestProduct", "TestDescription", 56, 64);
        UserDetails userDetails = new UserDetails("TestName", "TestName", "TestAddress", "TestContact", "TestEmail", cart);
        OrderDetails orderDetails1 = new OrderDetails(12, 12);
        OrderDetails orderDetails2 = new OrderDetails(24, 25);
        CartOrder expected = new CartOrder(Arrays.asList(orderDetails1, orderDetails2), "TestName", "TestName", "TestAddress", "TestContact", "TestEmail", "12", new Double(12));

        when(productRepository.findOne(anyInt())).thenReturn(product);
        when(cartRepository.save(any(Cart.class))).thenReturn(cart);
        when(orderRepository.save(any(CartOrder.class))).thenReturn(expected);
        when(userDetailsRepository.findOne(anyInt())).thenReturn(userDetails);

        ResponseOrderDto actual = orderService.createOrderForUser(12);

        assertEquals(actual.getUserFirstName(), expected.getUserFirstName());
        assertEquals(actual.getContact(), expected.getContact());
        assertEquals(actual.getUserLastName(), expected.getUserLastName());
        assertEquals(actual.getAddress(), expected.getAddress());
        assertEquals(actual.getEmail(), expected.getEmail());
    }

    @Test(expected = NotFoundException.class)
    public void createCartTest_throwsUserNotFoundException() throws Exception{
        CartDetails cartDetails1 = new CartDetails(12,12);
        CartDetails cartDetails2 = new CartDetails(24,25);
        Cart cart = new Cart(12, new Double(80), Arrays.asList(cartDetails1, cartDetails2), new UserDetails());

        CartOrder order = new CartOrder();
        when(productRepository.findOne(anyInt())).thenReturn(new Product());
        when(cartRepository.save(any(Cart.class))).thenReturn(cart);
        when(orderRepository.save(any(CartOrder.class))).thenReturn(order);
        when(userDetailsRepository.findOne(12)).thenReturn(null);

        orderService.createOrderForUser(12);
    }

    @Test(expected = NotFoundException.class)
    public void createCartTest_throwsProductNotFoundException() throws Exception{
        CartDetails cartDetails1 = new CartDetails(12,12);
        CartDetails cartDetails2 = new CartDetails(24,25);
        Cart cart = new Cart(12, new Double(80), Arrays.asList(cartDetails1, cartDetails2), new UserDetails());

        Product product = new Product(12, "Testname", "TestDescription", 12d, 10);
        UserDetails userDetails = new UserDetails("TestName", "TestName", "TestAddress", "TestContact", "TestEmail", cart);
        CartOrder order = new CartOrder();

        when(productRepository.findOne(anyInt())).thenReturn(product);
        when(cartRepository.save(any(Cart.class))).thenReturn(cart);
        when(orderRepository.save(any(CartOrder.class))).thenReturn(order);
        when(userDetailsRepository.findOne(12)).thenReturn(userDetails);

        orderService.createOrderForUser(12);
    }

    @Test(expected = ShoppingCartException.class)
    public void createCartTest_throwsShoppingCartException() throws Exception{
        Cart cart = new Cart(12, new Double(80), Arrays.asList(), new UserDetails());

        UserDetails userDetails = new UserDetails("TestName", "TestName", "TestAddress", "TestContact", "TestEmail", cart);
        CartOrder order = new CartOrder();
        when(productRepository.findOne(anyInt())).thenReturn(new Product());
        when(cartRepository.save(any(Cart.class))).thenReturn(cart);
        when(orderRepository.save(any(CartOrder.class))).thenReturn(order);
        when(userDetailsRepository.findOne(12)).thenReturn(userDetails);

        orderService.createOrderForUser(12);
    }

    @Test
    public void createOrderForGuestTest() throws Exception{
        RequestOrderDetailsDto requestOrderDetailsDto = new RequestOrderDetailsDto(12, 14);
        RequestOrderDto requestOrderDto = new RequestOrderDto("TestName", "TestName", "TestAddress", "TestContact", "TestEmail", Arrays.asList(requestOrderDetailsDto));

        CartDetails cartDetails1 = new CartDetails(12,12);
        CartDetails cartDetails2 = new CartDetails(24,25);
        Cart cart = new Cart(12, new Double(80), Arrays.asList(cartDetails1, cartDetails2), new UserDetails());

        Product product = new Product(12, "TestProduct", "TestDescription", 56, 64);
        UserDetails userDetails = new UserDetails("TestName", "TestName", "TestAddress", "TestContact", "TestEmail", new Cart());

        OrderDetails orderDetails1 = new OrderDetails(12, 12);
        OrderDetails orderDetails2 = new OrderDetails(24, 25);
        CartOrder expected = new CartOrder(Arrays.asList(orderDetails1, orderDetails2), "TestName", "TestName", "TestAddress", "TestContact", "TestEmail", "12", new Double(12));

        when(productRepository.findOne(anyInt())).thenReturn(product);
        when(cartRepository.save(any(Cart.class))).thenReturn(cart);
        when(orderRepository.save(any(CartOrder.class))).thenReturn(expected);
        when(userDetailsRepository.findOne(anyInt())).thenReturn(userDetails);

        ResponseOrderDto actual = orderService.createOrderForGuest(requestOrderDto);

        assertEquals(actual.getUserFirstName(), expected.getUserFirstName());
        assertEquals(actual.getContact(), expected.getContact());
        assertEquals(actual.getUserLastName(), expected.getUserLastName());
        assertEquals(actual.getAddress(), expected.getAddress());
        assertEquals(actual.getEmail(), expected.getEmail());
    }

    @Test(expected = ShoppingCartException.class)
    public void createOrderForGuestTest_throwsShoppingCartException() throws Exception{
        RequestOrderDetailsDto requestOrderDetailsDto = new RequestOrderDetailsDto(12, 0);

        RequestOrderDto requestOrderDto = new RequestOrderDto("TestName", "TestName", "TestAddress", "TestContact", "TestEmail", Arrays.asList(requestOrderDetailsDto));

        CartDetails cartDetails1 = new CartDetails(12,0);
        Cart cart = new Cart(12, new Double(80), Arrays.asList(cartDetails1), new UserDetails());

        Product product = new Product(12, "TestProduct", "TestDescription", 56, 64);
        UserDetails userDetails = new UserDetails("TestName", "TestName", "TestAddress", "TestContact", "TestEmail", new Cart());
        OrderDetails orderDetails1 = new OrderDetails(12, 0);
        CartOrder expected = new CartOrder(Arrays.asList(orderDetails1), "TestName", "TestName", "TestAddress", "TestContact", "TestEmail", "12", new Double(12));

        when(productRepository.findOne(anyInt())).thenReturn(product);
        when(cartRepository.save(any(Cart.class))).thenReturn(cart);
        when(orderRepository.save(any(CartOrder.class))).thenReturn(expected);
        when(userDetailsRepository.findOne(anyInt())).thenReturn(userDetails);

        orderService.createOrderForGuest(requestOrderDto);
    }

    @Test(expected = NotFoundException.class)
    public void createOrderForGuestTest_throwsNotFoundException() throws Exception {
        RequestOrderDetailsDto requestOrderDetailsDto = new RequestOrderDetailsDto(12, 14);
        RequestOrderDto requestOrderDto = new RequestOrderDto("TestName", "TestName", "TestAddress", "TestContact", "TestEmail", Arrays.asList(requestOrderDetailsDto));

        CartDetails cartDetails1 = new CartDetails(12,12);
        CartDetails cartDetails2 = new CartDetails(24,25);
        Cart cart = new Cart(12, new Double(80), Arrays.asList(cartDetails1, cartDetails2), new UserDetails());

        UserDetails userDetails = new UserDetails("TestName", "TestName", "TestAddress", "TestContact", "TestEmail", new Cart());
        OrderDetails orderDetails1 = new OrderDetails(12, 12);
        OrderDetails orderDetails2 = new OrderDetails(24, 25);
        CartOrder expected = new CartOrder(Arrays.asList(orderDetails1, orderDetails2), "TestName", "TestName", "TestAddress", "TestContact", "TestEmail", "12", new Double(12));

        when(productRepository.findOne(anyInt())).thenReturn(null);
        when(cartRepository.save(any(Cart.class))).thenReturn(cart);
        when(orderRepository.save(any(CartOrder.class))).thenReturn(expected);
        when(userDetailsRepository.findOne(anyInt())).thenReturn(userDetails);

        orderService.createOrderForGuest(requestOrderDto);
    }
}
