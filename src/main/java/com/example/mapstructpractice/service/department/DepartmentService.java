package com.example.mapstructpractice.service.department;

import java.util.List;
import com.example.mapstructpractice.dto.department.CreateDepartmentRequestDto;
import com.example.mapstructpractice.dto.department.DepartmentDto;

public interface DepartmentService {
    List<DepartmentDto> findAll();

    DepartmentDto save(CreateDepartmentRequestDto requestDto);
}
