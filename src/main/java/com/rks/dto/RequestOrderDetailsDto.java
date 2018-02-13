package com.rks.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class RequestOrderDetailsDto {
    private int productId;
    private int productQuantity;
}
