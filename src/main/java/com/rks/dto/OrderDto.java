package com.rks.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class OrderDto {

    private String userFirstName;
    private String userLastName;
    private String address;
    private String contact;
    private String email;
    private String createdDate;
    private Integer transactionId;
    private CartDto cartDto;
}
