package com.rks.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResponseOrderDetailsDto {
    private int productId;
    private int quantity;
    private String productName;
    private String productDescription;
    private double price;
}
