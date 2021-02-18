package com.shop.liquibase.service;

import com.shop.liquibase.dto.creationDto.CartCreationDto;
import com.shop.liquibase.entity.CartEntity;
import com.shop.liquibase.entity.ItemEntity;
import com.shop.liquibase.exceptions.AlreadyAssignException;
import com.shop.liquibase.exceptions.NotFoundException;
import com.shop.liquibase.mapper.CartMapper;
import com.shop.liquibase.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CartMapper cartMapper;

    @Autowired
    private ItemService itemService;

    @Transactional
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

    @Transactional
    public void brakeDownCartById(Long cartId) {
        CartEntity existingCart = getCartById(cartId);
        if (existingCart.getBroken()) {
            throw new NotFoundException("Cart is broken for id: " + cartId);
        }
        existingCart.setBroken(true);
        cartRepository.save(existingCart);
    }

    @Transactional
    public CartEntity addItemToCart(Long cartId, Long itemId) {
        CartEntity existingCart = getCartById(cartId);
        ItemEntity existingItem = itemService.getItemById(itemId);
        if (existingItem.getDeleted()) {
            throw new NotFoundException
                    ("Can't add item! Item was deleted for id: " + itemId);
        }
        if (existingItem.getCarts().contains(existingCart)) {
            throw new AlreadyAssignException
                    ("Item with id: " + itemId + " already add to this cart for id :" + cartId);
        }
        if (existingCart.getBroken()) {
            throw new NotFoundException
                    ("Can't add item! Cart was broken for id: " + cartId);
        }
        existingCart.getItems().add(existingItem);
        return cartRepository.save(existingCart);
    }

    @Transactional
    public CartEntity removeItemFromCart(Long cartId, Long itemId) {
        CartEntity existingCart = getCartById(cartId);
        ItemEntity existingItem = itemService.getItemById(itemId);
        if (!existingCart.getItems().contains(existingItem)) {
            throw new NotFoundException
                    ("Cart with id: " + cartId + " does not have a item for id :" + itemId);
        }
        existingCart.getItems().remove(existingItem);
        return cartRepository.save(existingCart);
    }
}
