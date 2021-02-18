package com.shop.liquibase.dto.plainDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartPlainDto {

    private Long id;

    private Boolean broken;

    private UserPlainDto user;

    private List<ItemPlainDto> items;
}
