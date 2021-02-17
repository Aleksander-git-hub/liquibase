package com.shop.liquibase.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private Long id;

    private String firstName;

    private String secondName;

    private String phone;

    private String email;

    private Boolean deleted;

    private List<CartDto> carts;
}
