package com.shop.liquibase.dto;

import com.shop.liquibase.dto.creationDto.UserCreationDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartDto {

    private Long id;

    private Boolean broken;

    private UserCreationDto user;

    private List<CartDto> items;
}
