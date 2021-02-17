package com.shop.liquibase.dto.creationDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentCreationDto {

    private Long id;

    private String name;

    private Boolean deleted = false;
}
