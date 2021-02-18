package com.shop.liquibase.mapper;

import com.shop.liquibase.dto.UserDto;
import com.shop.liquibase.dto.creationDto.UserCreationDto;
import com.shop.liquibase.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserEntity toEntity(UserCreationDto userCreationDto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "deleted", ignore = true)
    void updateUserEntityFromUserCreationDto(UserCreationDto source,
                                             @MappingTarget UserEntity target);

    UserDto toDto(UserEntity userEntity);

    UserCreationDto toCreationDto(UserEntity userEntity);
}
