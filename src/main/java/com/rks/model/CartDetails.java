package com.rks.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class CartDetails implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer cartDetailsId;

    @Column(name = "cart_id")
    private Integer cartId;

    private Integer productId;
    private Integer quantity;

    public CartDetails(Integer productId, Integer quantity){
        this.productId = productId;
        this.quantity = quantity;
    }
}
