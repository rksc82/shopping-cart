package com.rks.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResponseOrderDto {
    private Integer orderId;
    private Double total;
    private List<ResponseOrderDetailsDto> items;
    private String userFirstName;
    private String userLastName;
    private String address;
    private String contact;
    private String email;
    private String createdDate;
}
