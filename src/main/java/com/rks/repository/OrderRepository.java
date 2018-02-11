package com.rks.repository;

import com.rks.model.CartOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<CartOrder, Integer> {
}
