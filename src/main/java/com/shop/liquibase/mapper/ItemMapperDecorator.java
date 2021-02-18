/*
package com.shop.liquibase.mapper;

import com.shop.liquibase.dto.plainDto.ItemPlainDto;
import com.shop.liquibase.entity.ItemEntity;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class ItemMapperDecorator implements ItemMapper {

    @Autowired
    private ItemMapper delegate;

    public ItemPlainDto toPlainDto(ItemEntity itemEntity) {
        ItemPlainDto item = delegate.toPlainDto(itemEntity);
        item.setDepartment(itemEntity.getDepartment().getId());
        return item;
    }
}
*/
