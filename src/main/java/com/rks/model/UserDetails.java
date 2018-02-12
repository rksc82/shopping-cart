package com.rks.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Arrays;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    private Integer userId;
    private String userFirstName;
    private String userLastName;
    private String address;
    private String contact;
    private String email;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private List<Cart> cart;

    public UserDetails(String userFirstName, String userLastName, String address, String contact, String email, Cart cart) {
        this.userFirstName = userFirstName;
        this.userLastName = userLastName;
        this.address = address;
        this.contact = contact;
        this.email = email;
        this.cart = Arrays.asList(cart);
    }
}
