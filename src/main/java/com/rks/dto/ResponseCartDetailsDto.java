package com.rks.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
public class ResponseCartDetailsDto implements Serializable {
    private int productId;
    private int productQuantity;
    private String productName;
    private String productDescription;

}
