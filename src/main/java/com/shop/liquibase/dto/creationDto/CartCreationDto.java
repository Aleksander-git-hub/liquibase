package com.shop.liquibase.dto.creationDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartCreationDto {

    private Long id;

    private Boolean broken = false;
}
