package com.rks.dto;

import com.rks.model.CartDetails;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CartDto {

    private int id;
    private List<CartDetailsDto> cartDetailsDtoList;
    private double total;
}
