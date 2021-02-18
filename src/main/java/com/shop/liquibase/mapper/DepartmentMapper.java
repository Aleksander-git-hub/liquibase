package com.shop.liquibase.mapper;

import com.shop.liquibase.dto.DepartmentDto;
import com.shop.liquibase.dto.creationDto.DepartmentCreationDto;
import com.shop.liquibase.dto.plainDto.DepartmentPlainDto;
import com.shop.liquibase.entity.DepartmentEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface DepartmentMapper {

    DepartmentEntity toEntity(DepartmentCreationDto departmentCreationDto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "deleted", ignore = true)
    void updateDepartmentEntityFromDepartmentCreationDto
            (DepartmentCreationDto source, @MappingTarget DepartmentEntity target);

    DepartmentDto toDto(DepartmentEntity departmentEntity);

    DepartmentPlainDto toPlainDto(DepartmentEntity departmentEntity);
}
