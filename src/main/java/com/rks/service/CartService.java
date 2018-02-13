package com.rks.service;

import com.rks.dto.RequestCartDetailsDto;
import com.rks.dto.ResponseCartDetailsDto;
import com.rks.dto.ResponseCartDto;
import com.rks.exceptions.NotFoundException;
import com.rks.model.*;
import com.rks.repository.CartRepository;
import com.rks.repository.ProductRepository;
import com.rks.repository.UserDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class CartService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private UserDetailsRepository userRepository;

    public ResponseCartDto findByUserId(Integer userId) throws NotFoundException {
        UserDetails user = userRepository.findOne(userId);
        List<ResponseCartDetailsDto> responseCartDetailsDtoList = new ArrayList<>();

        if(user == null){
            throw new NotFoundException("Unable to find user with userId: "+ userId);
        }

        if(!user.getCart().getCartDetails().isEmpty()) {
            user.getCart().getCartDetails().forEach(cartDetails -> {
                Product product = productRepository.findOne(cartDetails.getProductId());
                responseCartDetailsDtoList.add(new ResponseCartDetailsDto(product.getProductId(),
                                                                          cartDetails.getQuantity(),
                                                                          product.getProductName(),
                                                                          product.getDescription()));
            });
        }

        return new ResponseCartDto(responseCartDetailsDtoList, user.getCart().getTotal());
    }

    public ResponseCartDto updateCartByUserId(List<RequestCartDetailsDto> requestCartDetailsDtoList, int userId) throws NotFoundException {
        double total = 0;
        UserDetails user = userRepository.findOne(userId);
        List<CartDetails> cartDetailsList = new ArrayList();
        List<ResponseCartDetailsDto> responseCartDetailsDtoList = new ArrayList();

        if(user == null){
            throw new NotFoundException("Unable to find cart with userId: " + userId);
        }

        Cart cart = user.getCart();

        for(RequestCartDetailsDto requestCartDetailsDto : requestCartDetailsDtoList){
            Product product = productRepository.findOne(requestCartDetailsDto.getProductId());
            if(product == null) {
                throw new NotFoundException("Unable to find product: " + requestCartDetailsDto.getProductId());
            }

            cartDetailsList.add(new CartDetails(product.getProductId(), requestCartDetailsDto.getProductQuantity()));
            responseCartDetailsDtoList.add(new ResponseCartDetailsDto(product.getProductId(),
                                                                      requestCartDetailsDto.getProductQuantity(),
                                                                      product.getProductName(),
                                                                      product.getDescription()));

            total += (requestCartDetailsDto.getProductQuantity() * product.getPrice());
        }

        cart.setTotal(total);
        cart.setCartDetails(cartDetailsList);
        cartRepository.save(cart);

        return new ResponseCartDto(responseCartDetailsDtoList, total);
    }
}
