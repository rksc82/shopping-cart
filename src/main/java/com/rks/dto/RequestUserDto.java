package com.rks.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RequestUserDto {
    private String userFirstName;
    private String userLastName;
    private String address;
    private String contact;
    private String email;
}
