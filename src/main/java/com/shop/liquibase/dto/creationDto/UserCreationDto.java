package com.shop.liquibase.dto.creationDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserCreationDto {
    private Long id;

    private String firstName;

    private String secondName;

    private String phone;

    private String email;

    private Boolean deleted = false;

    private List<CartCreationDto> carts;
}
