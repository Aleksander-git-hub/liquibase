package com.shop.liquibase.dto;

import com.shop.liquibase.entity.DepartmentEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemDto {

    private Long id;

    private String name;

    private String description;

    private Double price;

    private Boolean deleted;

    private List<CartDto> carts;

    private DepartmentEntity department;
}
