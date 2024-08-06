package com.example.mapstructpractice.repository;

import java.util.List;
import com.example.mapstructpractice.dto.department.CreateDepartmentRequestDto;
import com.example.mapstructpractice.model.Department;

public interface DepartmentRepository {
    Department save(Department department);
    List<Department> findAll();
}
