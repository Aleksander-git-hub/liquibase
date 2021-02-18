package com.shop.liquibase.controller;

import com.shop.liquibase.dto.ItemDto;
import com.shop.liquibase.dto.creationDto.ItemCreationDto;
import com.shop.liquibase.dto.plainDto.ItemPlainDto;
import com.shop.liquibase.entity.ItemEntity;
import com.shop.liquibase.mapper.ItemMapper;
import com.shop.liquibase.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api")
public class ItemController {

    @Autowired
    private ItemService itemService;

    @Autowired
    private ItemMapper itemMapper;

    @PostMapping(value = "/item")
    public ItemDto saveItem(@RequestBody ItemCreationDto itemCreationDto) {
        return itemMapper.toDto(itemService.saveItem(itemCreationDto));
    }

    @GetMapping(value = "/item/{itemId}")
    public ItemPlainDto getItemById(@PathVariable Long itemId) {
        return itemMapper.toPlainDto(itemService.getItemById(itemId));
    }

    @GetMapping(value = "/items")
    public List<ItemPlainDto> getAllItems() {
        List<ItemEntity> items = itemService.getAllItems();
        return items.stream()
                .map(itemMapper::toPlainDto)
                .collect(Collectors.toList());
    }

    @PutMapping(value = "/item/{itemId}")
    public ItemPlainDto updateItemById(@RequestBody ItemCreationDto itemCreationDto,
                                  @PathVariable Long itemId) {
        return itemMapper.toPlainDto(itemService.updateItemById(itemCreationDto, itemId));
    }

    @DeleteMapping(value = "/item/{itemId}")
    public ResponseEntity<?> deleteItemById(@PathVariable Long itemId) {
        itemService.deleteItemById(itemId);
        return ResponseEntity.ok().build();
    }
}
