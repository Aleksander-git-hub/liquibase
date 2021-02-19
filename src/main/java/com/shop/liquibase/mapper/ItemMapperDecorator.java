package com.shop.liquibase.mapper;

import com.shop.liquibase.dto.plainDto.ItemPlainDto;
import com.shop.liquibase.entity.ItemEntity;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class ItemMapperDecorator implements ItemMapper {

    @Autowired
    private ItemMapper delegate;

    @Override
    public ItemPlainDto toPlainDto(ItemEntity itemEntity) {
        ItemPlainDto item = delegate.toPlainDto(itemEntity);
        if (itemEntity.getDepartment() != null) {
            item.setDepartment(itemEntity.getDepartment().getId());
        }
        return item;
    }
}
