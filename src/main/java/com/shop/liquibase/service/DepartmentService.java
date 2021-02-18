package com.shop.liquibase.service;

import com.shop.liquibase.dto.creationDto.DepartmentCreationDto;
import com.shop.liquibase.dto.plainDto.DepartmentPlainDto;
import com.shop.liquibase.entity.CartEntity;
import com.shop.liquibase.entity.DepartmentEntity;
import com.shop.liquibase.entity.ItemEntity;
import com.shop.liquibase.entity.UserEntity;
import com.shop.liquibase.exceptions.AlreadyAssignException;
import com.shop.liquibase.exceptions.NotFoundException;
import com.shop.liquibase.mapper.DepartmentMapper;
import com.shop.liquibase.repository.DepartmentRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private DepartmentMapper departmentMapper;

    @Autowired
    private ItemService itemService;

    @Transactional
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

    @Transactional
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

    @Transactional
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

    @Transactional
    public DepartmentEntity addItemToDepartment(Long departmentId, Long itemId) {
        DepartmentEntity existingDepartment = getDepartmentById(departmentId);
        ItemEntity existingItem = itemService.getItemById(itemId);
        if (existingDepartment.getDeleted()) {
            throw new NotFoundException
                    ("Can't add item! Department was deleted for id: " + departmentId);
        }
        if (existingDepartment.getItems().contains(existingItem)) {
            throw new AlreadyAssignException
                    ("Department with id: " + departmentId +
                            " already has this item for id :" + itemId);
        }
        if (existingItem.getDeleted()) {
            throw new NotFoundException
                    ("Can't add item! Item was deleted for id: " + itemId);
        }
        existingDepartment.getItems().add(existingItem);
        existingItem.setDepartment(existingDepartment);
        return departmentRepository.save(existingDepartment);
    }

    @Transactional
    public DepartmentEntity removeItemFromDepartment(Long departmentId, Long itemId) {
        DepartmentEntity existingDepartment = getDepartmentById(departmentId);
        ItemEntity existingItem = itemService.getItemById(itemId);
        if (!existingDepartment.getItems().contains(existingItem)) {
            throw new NotFoundException
                    ("Department with id: " + departmentId +
                            " does not have a item for id :" + itemId);
        }
        existingDepartment.getItems().remove(existingItem);
        return departmentRepository.save(existingDepartment);
    }
}
