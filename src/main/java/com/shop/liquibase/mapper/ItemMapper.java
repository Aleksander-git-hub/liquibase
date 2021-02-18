package com.shop.liquibase.mapper;

import com.shop.liquibase.dto.ItemDto;
import com.shop.liquibase.dto.creationDto.ItemCreationDto;
import com.shop.liquibase.dto.plainDto.ItemPlainDto;
import com.shop.liquibase.entity.ItemEntity;
import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
@DecoratedWith(ItemMapperDecorator.class)
public interface ItemMapper {

    ItemEntity toEntity(ItemCreationDto itemCreationDto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "deleted", ignore = true)
    void updateItemEntityFromItemCreationDto(ItemCreationDto source,
                                             @MappingTarget ItemEntity target);

    ItemDto toDto(ItemEntity itemEntity);

    ItemPlainDto toPlainDto(ItemEntity itemEntity);
}
