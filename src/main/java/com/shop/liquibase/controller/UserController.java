package com.shop.liquibase.controller;

import com.shop.liquibase.dto.UserDto;
import com.shop.liquibase.dto.creationDto.UserCreationDto;
import com.shop.liquibase.entity.UserEntity;
import com.shop.liquibase.mapper.UserMapper;
import com.shop.liquibase.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserMapper userMapper;

    @PostMapping(value = "/user")
    public UserDto saveUser(@RequestBody UserCreationDto userCreationDto) {
        return userMapper.toDto(userService.saveUser(userCreationDto));
    }

    @GetMapping(value = "/user/{userId}")
    public UserCreationDto getUserById(@PathVariable Long userId) {
        return userMapper.toCreationDto(userService.getUserById(userId));
    }

    @GetMapping(value = "/users")
    public List<UserCreationDto> getAllUsers() {
        List<UserEntity> users = userService.getAllUsers();
        return users.stream()
                .map(userMapper::toCreationDto)
                .collect(Collectors.toList());
    }

    @PutMapping(value = "/user/{userId}")
    public UserCreationDto updateUserById(@RequestBody UserCreationDto userCreationDto,
                                          @PathVariable Long userId) {
        return userMapper.toCreationDto(userService.updateUserById(userCreationDto, userId));
    }

    @DeleteMapping(value = "/user/{userId}")
    public ResponseEntity<?> deleteUserById(@PathVariable Long userId) {
        userService.deleteUserById(userId);
        return ResponseEntity.ok().build();
    }

    @PostMapping(value = "/user/{userId}/cart/{cartId}")
    public UserCreationDto addCartToUser(@PathVariable Long userId,
                                         @PathVariable Long cartId) {
        return userMapper.toCreationDto(userService.addCartToUser(userId, cartId));
    }

    @DeleteMapping(value = "/user/{userId}/cart/{cartId}")
    public UserCreationDto removeCartFromUser(@PathVariable Long userId,
                                         @PathVariable Long cartId) {
        return userMapper.toCreationDto(userService.removeCartFromUser(userId, cartId));
    }
}
