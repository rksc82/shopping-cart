package com.rks.service;

import com.rks.dto.RequestCartDetailsDto;
import com.rks.dto.ResponseCartDetailsDto;
import com.rks.dto.ResponseCartDto;
import com.rks.exceptions.NotFoundException;
import com.rks.model.Cart;
import com.rks.model.Product;
import com.rks.model.UserDetails;
import com.rks.repository.CartRepository;
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
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class CartServiceTest {

    @Mock
    private CartRepository cartRepository;

    @Mock
    private ProductRepository productRepository;

    @Mock
    private UserDetailsRepository userDetailsRepository;

    @InjectMocks
    private CartService cartService;

    @Test
    public void findByUserIdTest() {
        ResponseCartDto expected = new ResponseCartDto(new ArrayList<ResponseCartDetailsDto>(),  new Double(80));

        Cart cart = new Cart(12, new Double(80),Arrays.asList(), new UserDetails());

        UserDetails userDetails = new UserDetails("TestName", "Test", "TestAddress", "TestContact", "TestEmail", cart);
        when(userDetailsRepository.findOne(12)).thenReturn(userDetails);
        ResponseCartDto actual = cartService.findByUserId(12);

        assertEquals(expected.getTotal(), actual.getTotal(), 0);
    }

    @Test(expected = NotFoundException.class)
    public void findByUserIdTest_throwsException() {
        when(userDetailsRepository.findOne(12)).thenReturn(null);
        cartService.findByUserId(12);
    }

    @Test
    public void updateCartByUserIdTest() throws NotFoundException {
        ResponseCartDetailsDto responseCartDetailsDto1 = new ResponseCartDetailsDto(1234,12, "testName", "testDescription");
        ResponseCartDetailsDto responseCartDetailsDto2 = new ResponseCartDetailsDto(1223,12, "testName", "testDescription");

        Cart cart = new Cart(12, new Double(80),Arrays.asList(), new UserDetails());

        UserDetails userDetails = new UserDetails("TestName", "Test", "TestAddress", "TestContact", "TestEmail", cart);
        Product product = new Product(12, "TestProduct", "TestDescription", 56, 64);

        when(userDetailsRepository.findOne(anyInt())).thenReturn(userDetails);
        when(productRepository.findOne(anyInt())).thenReturn(product);
        when(cartRepository.save(any(Cart.class))).thenReturn(cart);

        RequestCartDetailsDto requestCartDetailsDto = new RequestCartDetailsDto(12, 14);

        ResponseCartDto expected = new ResponseCartDto(Arrays.asList(responseCartDetailsDto1, responseCartDetailsDto2), 784 );
        ResponseCartDto actual = cartService.updateCartByUserId(Arrays.asList(requestCartDetailsDto), 12);

        assertEquals(expected.getTotal(), actual.getTotal(), 0);
    }

    @Test(expected = NotFoundException.class)
    public void updateCartTest_throwsProductNotFoundException() throws NotFoundException {
        Cart cart = new Cart(12, new Double(80),Arrays.asList(), new UserDetails());

        when(userDetailsRepository.findOne(anyInt())).thenReturn(null);
        when(productRepository.findOne(anyInt())).thenReturn(new Product());
        when(cartRepository.save(any(Cart.class))).thenReturn(cart);

        RequestCartDetailsDto requestCartDetailsDto = new RequestCartDetailsDto(12, 14);

        cartService.updateCartByUserId(Arrays.asList(requestCartDetailsDto), 12);
    }

    @Test(expected = NotFoundException.class)
    public void updateCartTest_throwsUserNotFoundException() throws NotFoundException {
        Cart cart = new Cart(12, new Double(80),Arrays.asList(), new UserDetails());

        when(userDetailsRepository.findOne(anyInt())).thenReturn(new UserDetails());
        when(productRepository.findOne(anyInt())).thenThrow(new NotFoundException("Product Not Found"));
        when(cartRepository.save(any(Cart.class))).thenReturn(cart);

        RequestCartDetailsDto requestCartDetailsDto = new RequestCartDetailsDto(12, 14);

        cartService.updateCartByUserId(Arrays.asList(requestCartDetailsDto), 12);
    }
}
