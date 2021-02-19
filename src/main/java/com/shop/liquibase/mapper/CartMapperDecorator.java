package com.shop.liquibase.mapper;

import com.shop.liquibase.dto.plainDto.CartPlainDto;
import com.shop.liquibase.entity.CartEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;

import java.util.stream.Collectors;

public abstract class CartMapperDecorator implements CartMapper {

    @Autowired
    private CartMapper delegate;

    @Autowired
    private ItemMapper itemMapper;

    @Override
    public CartPlainDto toPlainDto(CartEntity cartEntity) {
        CartPlainDto cart = delegate.toPlainDto(cartEntity);
        if (!CollectionUtils.isEmpty(cartEntity.getItems())) {
            cart.setItems(cartEntity.getItems().stream()
                    .map(itemMapper::toPlainDto)
                    .collect(Collectors.toList()));
        }
        return cart;
    }
}
