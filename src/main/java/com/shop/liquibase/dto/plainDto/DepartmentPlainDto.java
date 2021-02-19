package com.shop.liquibase.dto.plainDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentPlainDto {

    private Long id;

    private String name;

    private Boolean deleted;

    private List<ItemPlainDto> items;
}
