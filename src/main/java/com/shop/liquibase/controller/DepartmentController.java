package com.shop.liquibase.controller;

import com.shop.liquibase.dto.DepartmentDto;
import com.shop.liquibase.dto.creationDto.DepartmentCreationDto;
import com.shop.liquibase.dto.plainDto.DepartmentPlainDto;
import com.shop.liquibase.entity.DepartmentEntity;
import com.shop.liquibase.mapper.DepartmentMapper;
import com.shop.liquibase.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private DepartmentMapper departmentMapper;

    @PostMapping(value = "/department")
    public DepartmentDto saveDepartment
            (@RequestBody DepartmentCreationDto departmentCreationDto) {
        return departmentMapper.toDto(departmentService.saveDepartment(departmentCreationDto));
    }

    @GetMapping(value = "/department/{departmentId}")
    public DepartmentPlainDto getDepartmentById(@PathVariable Long departmentId) {
        return departmentMapper.toPlainDto(departmentService.getDepartmentById(departmentId));
    }

    @GetMapping(value = "/departments")
    public List<DepartmentPlainDto> getAllDepartments() {
        List<DepartmentEntity> departments = departmentService.getAllDepartments();
        return departments.stream()
                .map(departmentMapper::toPlainDto)
                .collect(Collectors.toList());
    }

    @PutMapping(value = "/department/{departmentId}")
    public DepartmentPlainDto updateDepartmentById
            (@RequestBody DepartmentCreationDto departmentCreationDto,
             @PathVariable Long departmentId) {
        return departmentMapper
        .toPlainDto(departmentService.updateDepartmentById(departmentCreationDto, departmentId));
    }

    @DeleteMapping(value = "/department/{departmentId}")
    public ResponseEntity<?> deleteDepartmentById(@PathVariable Long departmentId) {
        departmentService.deleteDepartmentById(departmentId);
        return ResponseEntity.ok().build();
    }

    @PostMapping(value = "/department/{departmentId}/item/{itemId}")
    public DepartmentPlainDto addItemToDepartment
            (@PathVariable Long departmentId, @PathVariable Long itemId) {
        return departmentMapper
        .toPlainDto(departmentService.addItemToDepartment(departmentId, itemId));
    }

    @DeleteMapping(value = "/department/{departmentId}/item/{itemId}")
    public DepartmentPlainDto removeItemFromDepartment
            (@PathVariable Long departmentId, @PathVariable Long itemId) {
        return departmentMapper
                .toPlainDto(departmentService.removeItemFromDepartment(departmentId, itemId));
    }
}
