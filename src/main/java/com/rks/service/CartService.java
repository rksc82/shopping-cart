package com.rks.service;

import com.rks.dto.CartDetailsDto;
import com.rks.dto.CartDto;
import com.rks.dto.OrderDto;
import com.rks.exceptions.ProductNotFoundException;
import com.rks.model.*;
import com.rks.repository.CartDetailsRepository;
import com.rks.repository.CartRepository;
import com.rks.repository.OrderRepository;
import com.rks.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CartService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CartRepository cartRepository;

    private final String IN_PROGRESS = "In Progress";

    public CartDto createCart(CartDto cartDto) throws ProductNotFoundException{
        double total = 0;
        Optional<Product> product;
        List<CartDetails> cartDetailsList = new ArrayList();

        for(CartDetailsDto cartDetailsDto : cartDto.getCartDetailsDtoList()){

            product = Optional.of(productRepository.findOne(cartDetailsDto.getProductId()));
            if(!product.isPresent()){
                throw new ProductNotFoundException("Unable to find Product with id: " + cartDetailsDto.getProductId());
            }

            CartDetails cartDetails = new CartDetails(cartDetailsDto.getProductId(), cartDetailsDto.getProductQuantity());

            cartDetailsList.add(cartDetails);
            total += (cartDetailsDto.getProductQuantity() * product.get().getPrice());
       }

        int cartId = cartRepository.save(new Cart(total, cartDetailsList, IN_PROGRESS)).getCartId();

        return new CartDto(cartId, cartDto.getCartDetailsDtoList(), total);
    }

    public List<Cart> findAll(){
        return cartRepository.findAll();
    }

    public Cart findById(Integer id) throws ProductNotFoundException{
        try {
          return cartRepository.findOne(id);
        } catch (Exception e){
            throw new ProductNotFoundException("Unable to find cart with id: "+id);
        }
    }

    public CartDto updateCart(CartDto cartDto) throws ProductNotFoundException{
        double total = 0;
        Product product = new Product();
        Cart cart = cartRepository.findOne(cartDto.getCartId());
        List<CartDetails> cartDetailsList = new ArrayList();

        for(CartDetailsDto cartDetailsDto : cartDto.getCartDetailsDtoList()){

            try {
                product = productRepository.findOne(cartDetailsDto.getProductId());
            } catch (Exception e) {
                throw new ProductNotFoundException("Unable to find product: " + product.getProductName());
            }
            CartDetails cartDetails = new CartDetails(cartDetailsDto.getProductId(), cartDetailsDto.getProductQuantity());

            cartDetailsList.add(cartDetails);
            total += (cartDetailsDto.getProductQuantity() * product.getPrice());
        }

        cartRepository.save(new Cart(total, cartDetailsList, IN_PROGRESS));

        return new CartDto(cart.getCartId(), cartDto.getCartDetailsDtoList(), total);
    }
}
