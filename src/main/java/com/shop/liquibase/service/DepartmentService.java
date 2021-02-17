package com.shop.liquibase.service;

import com.shop.liquibase.dto.creationDto.DepartmentCreationDto;
import com.shop.liquibase.entity.DepartmentEntity;
import com.shop.liquibase.exceptions.NotFoundException;
import com.shop.liquibase.mapper.DepartmentMapper;
import com.shop.liquibase.repository.DepartmentRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private DepartmentMapper departmentMapper;

    public DepartmentEntity saveDepartment(DepartmentCreationDto departmentCreationDto) {
        departmentCreationDtoValidate(departmentCreationDto);
        return departmentRepository.save(departmentMapper.toEntity(departmentCreationDto));
    }

    public DepartmentEntity getDepartmentById(Long departmentId) {
        return departmentRepository.findById(departmentId)
                .orElseThrow(() -> new NotFoundException("Department not found for id: " + departmentId));
    }

    public List<DepartmentEntity> getAllDepartments() {
        return departmentRepository.findAll();
    }

    public DepartmentEntity updateDepartmentById(DepartmentCreationDto departmentCreationDto,
                                                 Long departmentId) {
        DepartmentEntity existingDepartment = getDepartmentById(departmentId);
        departmentCreationDtoValidate(departmentCreationDto);
        if (existingDepartment.getDeleted()) {
            throw new NotFoundException("Department is deleted already for id: " + departmentId);
        }
        departmentMapper
                .updateDepartmentEntityFromDepartmentCreationDto
                        (departmentCreationDto, existingDepartment);
        return departmentRepository.save(existingDepartment);
    }

    public void deleteDepartmentById(Long departmentId) {
        DepartmentEntity existingDepartment = getDepartmentById(departmentId);
        if (existingDepartment.getDeleted()) {
            throw new NotFoundException("Department is deleted already for id: " + departmentId);
        }
        existingDepartment.setDeleted(true);
        departmentRepository.save(existingDepartment);
    }

    private void departmentCreationDtoValidate(DepartmentCreationDto departmentCreationDto) {
        if (StringUtils.isEmpty(departmentCreationDto.getName())) {
            throw new NotFoundException("Fields are empty or incorrect! Please, check this!");
        }
    }
}
