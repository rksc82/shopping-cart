package com.rks.Service;

import com.rks.dto.CartDetailsDto;
import com.rks.dto.CartDto;
import com.rks.model.Cart;
import com.rks.model.CartDetails;
import com.rks.model.Product;
import com.rks.repository.CartDetailsRepository;
import com.rks.repository.CartRepository;
import com.rks.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class CartService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CartRepository cartRepository;

    public CartDto createCart(CartDto cartDto){
        double total = 0;
        List<CartDetails> cartDetailsList = new ArrayList();

        for(CartDetailsDto cartDetailsDto : cartDto.getCartDetailsDtoList()){
            Product product = productRepository.findOne(cartDetailsDto.getProductId());

            CartDetails cartDetails = new CartDetails();
            cartDetails.setProductId(cartDetailsDto.getProductId());
            cartDetails.setQuantity(cartDetailsDto.getProductQuantity());

            cartDetailsList.add(cartDetails);
            total = total + cartDetailsDto.getProductQuantity() * product.getPrice();

       }

        Cart cart = new Cart();
        cart.setTotal(total);
        cart.setCartDetails(cartDetailsList);
        int cartId = cartRepository.save(cart).getCartId();

        cartDto.setId(cartId);
        cartDto.setTotal(total);
        return cartDto;
    }

    public List<Cart> findAll(){
        return cartRepository.findAll();
    }

//    public String order(int id){
//        double total = 0;
//        List<CartProduct> cartProducts = new ArrayList<>();
//
//        Cart carts = cartRepository.findOne(id);
//        for(Cart cart: carts){
//            cartProducts.add(new CartProduct(cartDto.getId(), cartDto.getQuantity()));
//
//            Product product = productRepository.findOne(cartDto.getId());
//            total = total + cartDto.getQuantity() * product.getPrice();
//        }
//
//        CartProductList cartProductList = new CartProductList(cartProducts, total);
//
//        cartRepository.save(new Cart(1,cartProductList));
//
//        CartDto cartToDto = new CartDto(1, cartDtos, total);
//        return cartToDto;    }
}
