package com.rks.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResponseUserDto {
    private Integer userId;
    private String userFirstName;
    private String userLastName;
    private String address;
    private String contact;
    private String email;
}
