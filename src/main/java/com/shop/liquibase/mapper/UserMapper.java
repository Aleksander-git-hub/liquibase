package com.shop.liquibase.mapper;

import com.shop.liquibase.dto.creationDto.UserCreationDto;
import com.shop.liquibase.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserEntity toEntity(UserCreationDto userCreationDto);

    void updateUserEntityFromUserCreationDto(UserCreationDto source,
                                             @MappingTarget UserEntity target);
}
