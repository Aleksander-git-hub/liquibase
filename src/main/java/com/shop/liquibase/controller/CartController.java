package com.shop.liquibase.controller;

import com.shop.liquibase.dto.CartDto;
import com.shop.liquibase.dto.creationDto.CartCreationDto;
import com.shop.liquibase.dto.plainDto.CartPlainDto;
import com.shop.liquibase.entity.CartEntity;
import com.shop.liquibase.mapper.CartMapper;
import com.shop.liquibase.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api")
public class CartController {

    @Autowired
    private CartService cartService;

    @Autowired
    private CartMapper cartMapper;

    @PostMapping(value = "/cart")
    public CartDto saveCart(@RequestBody CartCreationDto cartCreationDto) {
        return cartMapper.toDto(cartService.saveCart(cartCreationDto));
    }

    @GetMapping(value = "/cart/{cartId}")
    public CartPlainDto getCartById(@PathVariable Long cartId) {
        return cartMapper.toPlainDto(cartService.getCartById(cartId));
    }

    @GetMapping(value = "/carts")
    public List<CartCreationDto> getAllCarts() {
        List<CartEntity> carts = cartService.getAllCarts();
        return carts.stream()
                .map(cartMapper::toCreationDto)
                .collect(Collectors.toList());
    }

    @DeleteMapping(value = "/cart/{cartId}")
    public ResponseEntity<?> breakDownCartById(@PathVariable Long cartId) {
        cartService.brakeDownCartById(cartId);
        return ResponseEntity.ok().build();
    }

    @PostMapping(value = "/cart/{cartId}/item/{itemId}")
    public CartPlainDto addItemToCart(@PathVariable Long cartId,
                                      @PathVariable Long itemId) {
        return cartMapper.toPlainDto(cartService.addItemToCart(cartId, itemId));
    }

    @DeleteMapping(value = "/cart/{cartId}/item/{itemId}")
    public CartPlainDto removeItemFromCart(@PathVariable Long cartId,
                                           @PathVariable Long itemId) {
        return cartMapper.toPlainDto(cartService.removeItemFromCart(cartId, itemId));
    }
}
