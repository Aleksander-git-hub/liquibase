package com.shop.liquibase.dto.plainDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemPlainDto {
    private Long id;

    private String name;

    private Long department; // department id
}
