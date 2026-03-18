package com.example.employee_api.mapper;

import com.example.employee_api.dto.request.DepartmentDTO;
import com.example.employee_api.model.Department;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DepartmentMapper {

    Department toEntity(DepartmentDTO dto);

}