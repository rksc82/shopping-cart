package com.rks.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class CartDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int cartDetailsId;

    @ManyToOne
    @JoinColumn(name="cart_id", nullable=false)
    private Cart cart;
    private int productId;
    private int quantity;
}
