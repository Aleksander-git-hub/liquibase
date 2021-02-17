package com.shop.liquibase.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartDto {

    private Long id;

    private Boolean broken;

    private UserDto user;
}
