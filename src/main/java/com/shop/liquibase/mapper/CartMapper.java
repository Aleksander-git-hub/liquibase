package com.shop.liquibase.mapper;

import com.shop.liquibase.dto.CartDto;
import com.shop.liquibase.dto.creationDto.CartCreationDto;
import com.shop.liquibase.dto.plainDto.CartPlainDto;
import com.shop.liquibase.entity.CartEntity;
import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
@DecoratedWith(CartMapperDecorator.class)
public interface CartMapper {

    CartEntity toEntity(CartCreationDto cartCreationDto);

    CartDto toDto(CartEntity cartEntity);

    CartCreationDto toCreationDto(CartEntity cartEntity);

    @Mapping(target = "items", ignore = true)
    CartPlainDto toPlainDto(CartEntity cartEntity);
}
