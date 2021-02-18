package com.shop.liquibase.service;

import com.shop.liquibase.dto.creationDto.UserCreationDto;
import com.shop.liquibase.entity.CartEntity;
import com.shop.liquibase.entity.UserEntity;
import com.shop.liquibase.exceptions.AlreadyAssignException;
import com.shop.liquibase.exceptions.NotFoundException;
import com.shop.liquibase.mapper.UserMapper;
import com.shop.liquibase.repository.UserRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private CartService cartService;

    @Transactional
    public UserEntity saveUser(UserCreationDto userCreationDto) {
        userCreationDtoValidate(userCreationDto);
        return userRepository.save(userMapper.toEntity(userCreationDto));
    }

    public UserEntity getUserById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("User not found for id: " + userId));
    }

    public List<UserEntity> getAllUsers() {
        return userRepository.findAll();
    }

    @Transactional
    public UserEntity updateUserById(UserCreationDto userCreationDto, Long userId) {
        UserEntity existingUser = getUserById(userId);
        userCreationDtoValidate(userCreationDto);
        if (existingUser.getDeleted()) {
            throw new NotFoundException("User is deleted already for id: " + userId);
        }
        userMapper.updateUserEntityFromUserCreationDto(userCreationDto, existingUser);
        return userRepository.save(existingUser);
    }

    @Transactional
    public void deleteUserById(Long userId) {
        UserEntity existingUser = getUserById(userId);
        if (existingUser.getDeleted()) {
            throw new NotFoundException("User is deleted already for id: " + userId);
        }
        existingUser.setDeleted(true);
        userRepository.save(existingUser);
    }

    private void userCreationDtoValidate(UserCreationDto userCreationDto) {
        if (StringUtils.isEmpty(userCreationDto.getFirstName()) ||
            StringUtils.isEmpty(userCreationDto.getSecondName()) ||
            StringUtils.isEmpty(userCreationDto.getPhone()) ||
            StringUtils.isEmpty(userCreationDto.getEmail())) {
            throw new NotFoundException("Fields are empty! Please, check this!");
        }
    }

    @Transactional
    public UserEntity addCartToUser(Long userId, Long cartId) {
        UserEntity existingUser = getUserById(userId);
        CartEntity existingCart = cartService.getCartById(cartId);
        if (existingUser.getDeleted()) {
            throw new NotFoundException
                    ("Can't add cart! User was deleted for id: " + userId);
        }
        if (existingUser.getCarts().contains(existingCart)) {
            throw new AlreadyAssignException
                    ("User with id: " + userId + " already has this cart for id :" + cartId);
        }
        if (existingCart.getBroken()) {
            throw new NotFoundException
                    ("Can't add cart! Cart was broken for id: " + cartId);
        }
        existingUser.getCarts().add(existingCart);
        return userRepository.save(existingUser);
    }

    @Transactional
    public UserEntity removeCartFromUser(Long userId, Long cartId) {
        UserEntity existingUser = getUserById(userId);
        CartEntity existingCart = cartService.getCartById(cartId);
        if (!existingUser.getCarts().contains(existingCart)) {
            throw new NotFoundException
                    ("User with id: " + userId + " does not have a cart for id :" + cartId);
        }
        existingUser.getCarts().remove(existingCart);
        return userRepository.save(existingUser);
    }
}
