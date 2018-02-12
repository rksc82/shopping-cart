package com.rks.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Cart implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="cart_id")
    private Integer cartId;
    private Double total;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name="cart_id", referencedColumnName = "cart_id")
    private List<CartDetails> cartDetails;

    private String cartStatus;

    public Cart(Double total, List<CartDetails>cartDetails, String cartStatus){
        this.total = total;
        this.cartDetails = cartDetails;
        this.cartStatus = cartStatus;
    }
}
