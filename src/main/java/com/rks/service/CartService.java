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

    @Autowired
    private CartDetailsRepository cartDetailsRepository;

    @Autowired
    private OrderRepository orderRepository;

    private final String IN_PROGRESS = "In Progress";
    private final String ORDER_PLACED = "Order Placed";

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

    public OrderDto order(OrderDto orderDto, Integer id) throws ProductNotFoundException{

        Cart cart = cartRepository.findOne(id);
        List<OrderDetails> orderDetailsList = new ArrayList<>();
        for (CartDetails cartDetails : cart.getCartDetails()) {

            Product product = productRepository.findOne(cartDetails.getProductId());

            if (product.getQuantity() < cartDetails.getQuantity()) {
                throw new ProductNotFoundException("Product" + product.getProductName() + " Not in stock:");

            }
            product.setQuantity(product.getQuantity() - cartDetails.getQuantity());
            productRepository.save(product);

            OrderDetails orderDetails = new OrderDetails();
            orderDetails.setProductId(product.getProductId());
            orderDetails.setQuantity(cartDetails.getQuantity());

            orderDetailsList.add(orderDetails);
        }

        cart.setCartStatus("OrderDto Place");
        cartRepository.save(cart);

        CartOrder cartOrder = new CartOrder();
        cartOrder.setOrderDetails(orderDetailsList);
        cartOrder.setTotal(cart.getTotal());
        cartOrder.setAddress(orderDto.getAddress());
        cartOrder.setContact(orderDto.getContact());
        cartOrder.setUserFirstName(orderDto.getUserFirstName());
        cartOrder.setUserLastName(orderDto.getUserLastName());
        cartOrder.setEmail(orderDto.getEmail());

        orderRepository.save(cartOrder);

        orderDto.setTransactionId(orderRepository.save(cartOrder).getOrderId());
        return orderDto;
    }
}
