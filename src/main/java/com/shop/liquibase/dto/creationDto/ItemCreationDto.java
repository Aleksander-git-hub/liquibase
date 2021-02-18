package com.shop.liquibase.dto.creationDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemCreationDto {

    private Long id;

    private String name;

    private String description;

    private Double price;

    private Boolean deleted = false;

    private List<CartCreationDto> carts;

    private DepartmentCreationDto department;
}
