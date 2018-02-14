package com.rks.repository;

import com.rks.model.CartOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<CartOrder, Integer> {
}
