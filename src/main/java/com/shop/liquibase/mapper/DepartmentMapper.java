package com.shop.liquibase.mapper;

import com.shop.liquibase.dto.creationDto.DepartmentCreationDto;
import com.shop.liquibase.entity.DepartmentEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface DepartmentMapper {

    DepartmentEntity toEntity(DepartmentCreationDto departmentCreationDto);

    void updateDepartmentEntityFromDepartmentCreationDto
            (DepartmentCreationDto source, @MappingTarget DepartmentEntity target);
}
