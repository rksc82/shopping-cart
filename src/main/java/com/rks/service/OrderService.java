package com.rks.service;

import com.rks.dto.ResponseOrderDetailsDto;
import com.rks.dto.ResponseOrderDto;
import com.rks.dto.RequestOrderDetailsDto;
import com.rks.dto.RequestOrderDto;
import com.rks.exceptions.NotFoundException;
import com.rks.exceptions.ShoppingCartException;
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

    public ResponseOrderDto createOrderForUser(int userId) throws NotFoundException, ShoppingCartException {
        List<OrderDetails> orderDetailsList = new ArrayList<>();
        List<ResponseOrderDetailsDto> responseOrderDetailsDtoList = new ArrayList<>();

        UserDetails userDetails = userDetailsRepository.findOne(userId);
        if(userDetails == null) {
            throw new NotFoundException("User not found with id: " + userId);
        }

        Cart cart = userDetails.getCart();

        if(cart.getCartDetails().isEmpty()) {
            throw new ShoppingCartException("Unable to checkout. No items present in the cart.");
        }

        cart.getCartDetails().forEach(cartDetails -> {
            Product product = productRepository.findOne(cartDetails.getProductId());
            if (product.getQuantity() < cartDetails.getQuantity()) {
                throw new NotFoundException("Product" + product.getProductName() + " Not in stock:");
            }

            if(cartDetails.getQuantity() != 0) {
                orderDetailsList.add(new OrderDetails(product.getProductId(), cartDetails.getQuantity()));

                responseOrderDetailsDtoList.add(new ResponseOrderDetailsDto(product.getProductId(),
                        cartDetails.getQuantity(),
                        product.getProductName(),
                        product.getDescription(),
                        product.getPrice()));
            }

            product.setQuantity(product.getQuantity() - cartDetails.getQuantity());
            productRepository.save(product);
        });

        CartOrder order = orderRepository.save(new CartOrder(orderDetailsList,
                                            userDetails.getUserFirstName(),
                                            userDetails.getUserLastName(),
                                            userDetails.getAddress(),
                                            userDetails.getContact(),
                                            userDetails.getEmail(),
                                            LocalDateTime.now().toString(),
                                            cart.getTotal()));

        cart.setTotal(0d);
        cart.setCartDetails(new ArrayList<>());
        cartRepository.save(cart);

        return new ResponseOrderDto(order.getOrderId(),
                                    order.getTotal(),
                                    responseOrderDetailsDtoList,
                                    order.getUserFirstName(),
                                    order.getUserLastName(),
                                    order.getAddress(),
                                    order.getContact(),
                                    order.getEmail(),
                                    order.getCreatedDate());
    }

    public ResponseOrderDto createOrderForGuest(RequestOrderDto requestOrderDto) throws NotFoundException, ShoppingCartException {
        double total = 0;
        List<OrderDetails> orderDetailsList = new ArrayList<>();
        List<ResponseOrderDetailsDto> items = new ArrayList<>();

        if(requestOrderDto.getItems().isEmpty()){
            throw new ShoppingCartException("Unable to checkout. No items present in the cart.");
        }

        for (RequestOrderDetailsDto requestOrderDetailsDto : requestOrderDto.getItems()) {
            Product product = productRepository.findOne(requestOrderDetailsDto.getProductId());
            if(product == null) {
                throw new NotFoundException("Product Not Found with id: " + requestOrderDetailsDto.getProductId());
            }

            if (product.getQuantity() < requestOrderDetailsDto.getProductQuantity()) {
                throw new NotFoundException("Product" + product.getProductName() + " Not in stock:");

            }
            product.setQuantity(product.getQuantity() - requestOrderDetailsDto.getProductQuantity());
            productRepository.save(product);

            if(requestOrderDetailsDto.getProductQuantity() != 0) {
                orderDetailsList.add(new OrderDetails(product.getProductId(), requestOrderDetailsDto.getProductQuantity()));
                items.add(new ResponseOrderDetailsDto(product.getProductId(),
                        requestOrderDetailsDto.getProductQuantity(),
                        product.getProductName(),
                        product.getDescription(),
                        product.getPrice()));
            }

            total += (requestOrderDetailsDto.getProductQuantity() * product.getPrice());
        }

        if(total == 0){
            throw new ShoppingCartException("Unable to checkout. No items present in the cart.");
        }

        CartOrder cartOrder = new CartOrder(orderDetailsList,
                                            requestOrderDto.getUserFirstName(),
                                            requestOrderDto.getUserLastName(),
                                            requestOrderDto.getAddress(),
                                            requestOrderDto.getContact(),
                                            requestOrderDto.getEmail(),
                                            LocalDateTime.now().toString(),
                                            total);
        CartOrder order = orderRepository.save(cartOrder);
        return new ResponseOrderDto(order.getOrderId(),
                order.getTotal(),
                items,
                order.getUserFirstName(),
                order.getUserLastName(),
                order.getAddress(),
                order.getContact(),
                order.getEmail(),
                order.getCreatedDate());
    }
}
