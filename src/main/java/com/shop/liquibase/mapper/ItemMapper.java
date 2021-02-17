package com.shop.liquibase.mapper;

import com.shop.liquibase.dto.creationDto.ItemCreationDto;
import com.shop.liquibase.entity.ItemEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ItemMapper {

    ItemEntity toEntity(ItemCreationDto itemCreationDto);

    void updateItemEntityFromItemCreationDto(ItemCreationDto source,
                                             @MappingTarget ItemEntity target);
}
