package com.shop.liquibase.dto.plainDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserPlainDto {

    private Long id;

    private String firstName;

    private String secondName;
}
