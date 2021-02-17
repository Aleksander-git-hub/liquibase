package com.shop.liquibase.mapper;

import com.shop.liquibase.dto.creationDto.CartCreationDto;
import com.shop.liquibase.entity.CartEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CartMapper {

    CartEntity toEntity(CartCreationDto cartCreationDto);
}
