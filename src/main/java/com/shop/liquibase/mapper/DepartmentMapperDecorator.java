package com.shop.liquibase.mapper;

import com.shop.liquibase.dto.plainDto.DepartmentPlainDto;
import com.shop.liquibase.entity.DepartmentEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;

import java.util.stream.Collectors;

public abstract class DepartmentMapperDecorator implements DepartmentMapper {

    @Autowired
    private DepartmentMapper delegate;

    @Autowired
    private ItemMapper itemMapper;

    @Override
    public DepartmentPlainDto toPlainDto(DepartmentEntity departmentEntity) {
        DepartmentPlainDto department = delegate.toPlainDto(departmentEntity);
        if (!CollectionUtils.isEmpty(departmentEntity.getItems())) {
            department.setItems(departmentEntity.getItems().stream()
            .map(itemMapper::toPlainDto)
            .collect(Collectors.toList()));
        }
        return department;
    }
}
