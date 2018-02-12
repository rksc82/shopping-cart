package com.rks.repository;

import com.rks.model.CartDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CartDetailsRepository extends JpaRepository<CartDetails, Integer> {

   public void deleteByCartId(Integer cartId);

   public CartDetails findByCartIdInAndProductIdIn(Integer cartId, Integer productId);
}
