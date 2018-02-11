package com.rks.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class CartOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="order_id")
    private Integer orderId;
    private Double total;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name="order_id", referencedColumnName = "order_id")
    private List<OrderDetails> orderDetails;

    private String userFirstName;
    private String userLastName;
    private String address;
    private String contact;
    private String email;
    private String createdDate;

    public CartOrder(List<OrderDetails> orderDetails, String userFirstName, String userLastName, String address, String contact, String email, String createdDate, Double total){

        this.orderDetails = orderDetails;
        this.userFirstName = userFirstName;
        this.userLastName = userLastName;
        this.address = address;
        this.contact = contact;
        this.email = email;
        this.createdDate = createdDate;
        this.total = total;
    }
}
