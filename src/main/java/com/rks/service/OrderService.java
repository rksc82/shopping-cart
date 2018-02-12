package com.rks.service;

import com.rks.dto.OrderDto;
import com.rks.exceptions.NotFoundException;
import com.rks.model.*;
import com.rks.repository.CartRepository;
import com.rks.repository.OrderRepository;
import com.rks.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OrderRepository orderRepository;

    private final String ORDER_PLACED = "Order Placed";

    public OrderDto createOrder(OrderDto orderDto, int cartId) throws NotFoundException {
        Cart cart = cartRepository.findOne(cartId);
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
        cart.setCartStatus(ORDER_PLACED);
        cartRepository.save(cart);

        CartOrder cartOrder = new CartOrder(orderDetailsList,
                                            orderDto.getUserFirstName(),
                                            orderDto.getUserLastName(),
                                            orderDto.getAddress(),
                                            orderDto.getContact(),
                                            orderDto.getEmail(),
                                      "12",
                                            cart.getTotal());

        orderDto.setTransactionId(orderRepository.save(cartOrder).getOrderId());
        return orderDto;
    }
}
