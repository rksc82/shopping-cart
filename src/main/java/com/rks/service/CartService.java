package com.rks.service;

import com.rks.dto.CartDetailsDto;
import com.rks.dto.CartDto;
import com.rks.exceptions.NotFoundException;
import com.rks.model.*;
import com.rks.repository.CartDetailsRepository;
import com.rks.repository.CartRepository;
import com.rks.repository.ProductRepository;
import com.rks.repository.UserDetailsRepository;
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

    @Autowired
    private UserDetailsRepository userRepository;


    @Autowired
    private CartDetailsRepository cartDetailsRepository;

    public CartDto createCart(CartDto cartDto) throws NotFoundException {
        double total = 0;
        Optional<Product> product;
        List<CartDetails> cartDetailsList = new ArrayList();

        for(CartDetailsDto cartDetailsDto : cartDto.getCartDetailsDtoList()){
            product = Optional.of(productRepository.findOne(cartDetailsDto.getProductId()));
            if(!product.isPresent()){
                throw new NotFoundException("Unable to find Product with id: " + cartDetailsDto.getProductId());
            }

            CartDetails cartDetails = new CartDetails(cartDetailsDto.getProductId(), cartDetailsDto.getProductQuantity());
            cartDetailsList.add(cartDetails);

            total += (cartDetailsDto.getProductQuantity() * product.get().getPrice());
       }

        int cartId = cartRepository.save(new Cart(total, cartDetailsList)).getCartId();

        return new CartDto(cartId, cartDto.getCartDetailsDtoList(), total);
    }

    public List<Cart> findAll(){
        return cartRepository.findAll();
    }

    public CartDto findByUserId(Integer userId) throws NotFoundException {

        UserDetails user = userRepository.findOne(userId);
        if(user == null){
            throw new NotFoundException("Unable to find user with userId: "+ userId);
        }
        Cart cart = user.getCart();

        List<CartDetailsDto> cartDetailsDtoList = new ArrayList<>();

        if(!cart.getCartDetails().isEmpty()) {
            for(CartDetails cartDetails: cart.getCartDetails()) {
                Product product = productRepository.getOne(cartDetails.getProductId());
                cartDetailsDtoList.add(new CartDetailsDto(cartDetails.getProductId(),
                        cartDetails.getQuantity(),
                        product.getProductName(),
                        product.getDescription()));
            }
        }

        return new CartDto(cart.getCartId(), cartDetailsDtoList, cart.getTotal());
    }

    public CartDto updateCart(CartDto cartDto, int userId) throws NotFoundException {
        double total = 0;
        Product product;
       Cart cart = cartRepository.findOne(userId);
        if(cart == null){
            throw new NotFoundException("Unable to find cart with userId: " + userId);
        }
        List<CartDetails> cartDetailsList = new ArrayList();

        for(CartDetailsDto cartDetailsDto : cartDto.getCartDetailsDtoList()){
            product = productRepository.findOne(cartDetailsDto.getProductId());
            if(product == null) {
                throw new NotFoundException("Unable to find product: " + product.getProductName());
            }

            CartDetails cartDetails = cartDetailsRepository.findByCartIdInAndProductIdIn(cartDto.getCartId(), product.getProductId());

            if(cartDetails != null){
                cartDetails.setQuantity(cartDetailsDto.getProductQuantity());
            } else {
                cartDetails = new CartDetails(product.getProductId(), cartDetailsDto.getProductQuantity());
            }

            cartDetailsList.add(cartDetails);
            total += (cartDetailsDto.getProductQuantity() * product.getPrice());
        }

        cart.setTotal(total);
        cart.setCartDetails(cartDetailsList);
        cartRepository.save(cart);

        return new CartDto(cart.getCartId(), cartDto.getCartDetailsDtoList(), total);
    }
}
