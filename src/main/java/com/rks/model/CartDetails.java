package com.rks.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.UUID;

@Getter
@Setter
@Entity
public class CartDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer cartDetailsId;

    @Column(name = "cart_id")
    private Integer cartId;
    private Integer productId;
    private Integer quantity;
}
