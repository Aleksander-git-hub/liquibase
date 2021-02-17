package com.shop.liquibase.service;

import com.shop.liquibase.dto.creationDto.ItemCreationDto;
import com.shop.liquibase.entity.ItemEntity;
import com.shop.liquibase.exceptions.NotFoundException;
import com.shop.liquibase.mapper.ItemMapper;
import com.shop.liquibase.repository.ItemRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemService {

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private ItemMapper itemMapper;

    public ItemEntity saveItem(ItemCreationDto itemCreationDto) {
        itemCreationDtoValidate(itemCreationDto);
        return itemRepository.save(itemMapper.toEntity(itemCreationDto));
    }

    public ItemEntity getItemById(Long itemId) {
        return itemRepository.findById(itemId)
                .orElseThrow(() -> new NotFoundException("Item not found for id: " + itemId));
    }

    public List<ItemEntity> getAllItems() {
        return itemRepository.findAll();
    }

    public ItemEntity updateItemById(ItemCreationDto itemCreationDto, Long itemId) {
        ItemEntity existingItem = getItemById(itemId);
        itemCreationDtoValidate(itemCreationDto);
        if (existingItem.getDeleted()) {
            throw new NotFoundException("Item is deleted already for id: " + itemId);
        }
        itemMapper.updateItemEntityFromItemCreationDto(itemCreationDto, existingItem);
        return itemRepository.save(existingItem);
    }

    public void deleteItemById(Long itemId) {
        ItemEntity existingItem = getItemById(itemId);
        if (existingItem.getDeleted()) {
            throw new NotFoundException("Item is deleted already for id: " + itemId);
        }
        existingItem.setDeleted(true);
        itemRepository.save(existingItem);
    }

    private void itemCreationDtoValidate(ItemCreationDto itemCreationDto) {
        if (StringUtils.isEmpty(itemCreationDto.getName()) ||
            itemCreationDto.getPrice() == null ||
            itemCreationDto.getPrice() <= 0) {
            throw new NotFoundException("Fields are empty or incorrect! Please, check this!");
        }
    }
}
