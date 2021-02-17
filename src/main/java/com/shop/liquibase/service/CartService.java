package com.shop.liquibase.service;

import com.shop.liquibase.dto.creationDto.CartCreationDto;
import com.shop.liquibase.entity.CartEntity;
import com.shop.liquibase.exceptions.NotFoundException;
import com.shop.liquibase.mapper.CartMapper;
import com.shop.liquibase.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CartMapper cartMapper;

    public CartEntity saveCart(CartCreationDto cartCreationDto) {
        return cartRepository.save(cartMapper.toEntity(cartCreationDto));
    }

    public CartEntity getCartById(Long cartId) {
        return cartRepository.findById(cartId)
                .orElseThrow(() -> new NotFoundException("Cart not found for id: " + cartId));
    }

    public List<CartEntity> getAllCarts() {
        return cartRepository.findAll();
    }

    public void brakeDownCartById(Long cartId) {
        CartEntity existingCart = getCartById(cartId);
        if (existingCart.getBroken()) {
            throw new NotFoundException("Cart is broken for id: " + cartId);
        }
        existingCart.setBroken(true);
        cartRepository.save(existingCart);
    }
}
