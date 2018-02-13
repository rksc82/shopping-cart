package com.rks.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class ResponseCartDto {

    private List<ResponseCartDetailsDto> items;
    private double total;

    public ResponseCartDto(List<ResponseCartDetailsDto> items, double total){
        this.items = items;
        this.total = total;
    }
}
