package com.rks.service;

import com.rks.dto.CartDetailsDto;
import com.rks.dto.CartDto;
import com.rks.exceptions.NotFoundException;
import com.rks.model.Cart;
import com.rks.model.CartDetails;
import com.rks.model.Product;
import com.rks.repository.CartDetailsRepository;
import com.rks.repository.CartRepository;
import com.rks.repository.ProductRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
    private CartDetailsRepository cartDetailsRepository;

    @InjectMocks
    private CartService cartService;

    @Test
    public void createCartTest() throws NotFoundException {

        CartDetailsDto cartDetailsDto1 = new CartDetailsDto(1234,12, "testName", "testDescription");
        CartDetailsDto cartDetailsDto2 = new CartDetailsDto(1223,12, "testName", "testDescription");
        Cart cart = new Cart(12, new Double(80),12, new ArrayList<CartDetails>());
        Product product = new Product(12, "TestProduct", "TestDescription", 56, 64);

        when(productRepository.findOne(anyInt())).thenReturn(product);
        when(cartRepository.save(any(Cart.class))).thenReturn(cart);

        CartDto expected = new CartDto(12, Arrays.asList(cartDetailsDto1, cartDetailsDto2), 24 );
        CartDto actual = cartService.createCart(expected);

        assertEquals(actual.getCartId(), expected.getCartId());
        assertEquals(actual.getCartDetailsDtoList(), expected.getCartDetailsDtoList());
    }

    @Test(expected = NotFoundException.class)
    public void createCartTest_throwsNotFoundException() throws javassist.NotFoundException {

        CartDetailsDto cartDetailsDto1 = new CartDetailsDto(1234,12, "testName", "testDescription");
        CartDetailsDto cartDetailsDto2 = new CartDetailsDto(1223,12, "testName", "testDescription");
        Cart cart = new Cart(12, new Double(80),12, new ArrayList<CartDetails>());

        when(productRepository.findOne(anyInt())).thenThrow(new NotFoundException("Unable to find Product"));
        when(cartRepository.save(any(Cart.class))).thenReturn(cart);

        CartDto cartDto = new CartDto(1234, Arrays.asList(cartDetailsDto1, cartDetailsDto2), 24 );
        cartService.createCart(cartDto);
    }

    @Test
    public void findAllTest() {

        Cart cart = new Cart(12, new Double(80), 12, new ArrayList<CartDetails>());

        when(cartRepository.findAll()).thenReturn(Arrays.asList(cart));
        List<Cart> carts = cartService.findAll();

        assert(carts.size() == 1);
        assert(carts.contains(cart));
    }

    @Test
    public void findByIdTest() {

        CartDto expected = new CartDto(12, new ArrayList<CartDetailsDto>(),  new Double(80));
        Cart cart = new Cart(12, new Double(80), 12, new ArrayList<CartDetails>());

        when(cartRepository.findByUserId(12)).thenReturn(cart);
        CartDto actual = cartService.findById(12);

        assertEquals(expected.getCartId(), actual.getCartId());
        assertEquals(expected.getTotal(), actual.getTotal(), 0);
    }

    @Test(expected = NotFoundException.class)
    public void findByIdTest_throwsException() {

        Cart cart = new Cart(12, new Double(80), 12, new ArrayList<CartDetails>());

        when(cartRepository.findAll()).thenThrow(new NotFoundException("Unable to find Product with id:" + cart.getCartId()));
        cartService.findAll();
    }

    @Test
    public void updateCartTest() throws NotFoundException {

        CartDetailsDto cartDetailsDto1 = new CartDetailsDto(1234,12, "testName", "testDescription");
        CartDetailsDto cartDetailsDto2 = new CartDetailsDto(1223,12, "testName", "testDescription");
        Cart cart = new Cart(12, new Double(80), 12, new ArrayList<CartDetails>());
        Product product = new Product(12, "TestProduct", "TestDescription", 56, 64);
        CartDetails cartDetails = new CartDetails(1234, 12);

        when(cartRepository.findByUserId(anyInt())).thenReturn(cart);
        when(productRepository.findOne(anyInt())).thenReturn(product);
        when(cartRepository.save(any(Cart.class))).thenReturn(cart);
        when(cartDetailsRepository.findByCartIdInAndProductIdIn(anyInt(), anyInt())).thenReturn(cartDetails);

        CartDto expected = new CartDto(12, Arrays.asList(cartDetailsDto1, cartDetailsDto2), 24 );
        CartDto actual = cartService.updateCart(expected, 12);

        assertEquals(actual.getCartId(), expected.getCartId());
        assertEquals(actual.getCartDetailsDtoList(), expected.getCartDetailsDtoList());
    }

    @Test(expected = NotFoundException.class)
    public void updateCartTest_throwsProductNotFoundException() throws NotFoundException {

        CartDetailsDto cartDetailsDto1 = new CartDetailsDto(1234,12, "testName", "testDescription");
        CartDetailsDto cartDetailsDto2 = new CartDetailsDto(1223,12, "testName", "testDescription");
        Cart cart = new Cart(12, new Double(80), 12, new ArrayList<CartDetails>());
        Product product = new Product(12, "TestProduct", "TestDescription", 56, 64);

        when(cartRepository.findByUserId(anyInt())).thenReturn(cart);
        when(productRepository.findOne(anyInt())).thenThrow(new NotFoundException("Product Not Found"));
        when(cartRepository.save(any(Cart.class))).thenReturn(cart);

        CartDto expected = new CartDto(12, Arrays.asList(cartDetailsDto1, cartDetailsDto2), 24 );
        cartService.updateCart(expected, 12);
    }
}
