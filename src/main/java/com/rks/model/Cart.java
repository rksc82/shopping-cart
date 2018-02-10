package com.rks.model;

import com.fasterxml.jackson.annotation.JsonSubTypes;
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
public class Cart implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="cart_id")
    private Integer cartId;
    private double total;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name="cart_id", referencedColumnName = "cart_id")
    private List<CartDetails> cartDetails;
}
