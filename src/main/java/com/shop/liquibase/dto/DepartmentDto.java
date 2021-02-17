package com.shop.liquibase.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentDto {

    private Long id;

    private String name;

    private Boolean deleted;

    private List<ItemDto> items;
}
