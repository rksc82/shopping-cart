package com.rks.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class CartDto {

    private int cartId;
    private List<CartDetailsDto> cartDetailsDtoList;
    private double total;

    public CartDto(int cartId, List<CartDetailsDto> cartDetailsDtos, double total){
        this.cartId = cartId;
        this.cartDetailsDtoList = cartDetailsDtos;
        this.total = total;
    }
}
