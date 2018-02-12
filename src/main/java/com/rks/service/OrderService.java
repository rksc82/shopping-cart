package com.rks.service;

import com.rks.dto.CartDetailsDto;
import com.rks.dto.OrderDto;
import com.rks.exceptions.NotFoundException;
import com.rks.model.*;
import com.rks.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class OrderService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserDetailsRepository userDetailsRepository;

    public CartOrder createOrderForUser(int userId) throws NotFoundException {
        Cart cart = cartRepository.findByUserId(userId);
        UserDetails userDetails = userDetailsRepository.findOne(userId);

        List<OrderDetails> orderDetailsList = new ArrayList<>();
        for (CartDetails cartDetails : cart.getCartDetails()) {
            Product product = productRepository.findOne(cartDetails.getProductId());
            if (product.getQuantity() < cartDetails.getQuantity()) {
                throw new NotFoundException("Product" + product.getProductName() + " Not in stock:");
            }
            product.setQuantity(product.getQuantity() - cartDetails.getQuantity());
            productRepository.save(product);

            orderDetailsList.add(new OrderDetails(product.getProductId(), cartDetails.getQuantity()));
        }

        CartOrder cartOrder = new CartOrder(orderDetailsList,
                                            userDetails.getUserFirstName(),
                                            userDetails.getUserLastName(),
                                            userDetails.getAddress(),
                                            userDetails.getContact(),
                                            userDetails.getEmail(),
                                            LocalDateTime.now().toString(),
                                            cart.getTotal());

        cart.setTotal(0d);
        cart.setCartDetails(new ArrayList<CartDetails>());
        cartRepository.save(cart);

        return cartOrder;
    }

    public CartOrder createOrderForGuest(OrderDto orderDto) throws NotFoundException {
        double total = 0;
        List<OrderDetails> orderDetailsList = new ArrayList<>();

        for (CartDetailsDto cartDetails : orderDto.getCartDto().getCartDetailsDtoList()) {
            Product product = productRepository.findOne(cartDetails.getProductId());
            if(product == null) {
                throw new NotFoundException("Product Not Found with id: " + cartDetails.getProductId());
            }

            if (product.getQuantity() < cartDetails.getProductQuantity()) {
                throw new NotFoundException("Product" + product.getProductName() + " Not in stock:");

            }
            product.setQuantity(product.getQuantity() - cartDetails.getProductQuantity());
            productRepository.save(product);

            orderDetailsList.add(new OrderDetails(product.getProductId(), cartDetails.getProductQuantity()));
            total += (cartDetails.getProductQuantity() * product.getPrice());
        }

        CartOrder cartOrder = new CartOrder(orderDetailsList,
                                            orderDto.getUserFirstName(),
                                            orderDto.getUserLastName(),
                                            orderDto.getAddress(),
                                            orderDto.getContact(),
                                            orderDto.getEmail(),
                                            "12",
                                            total);

        return orderRepository.save(cartOrder);
    }
}
