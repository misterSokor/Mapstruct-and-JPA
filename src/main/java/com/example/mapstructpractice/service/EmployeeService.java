package com.example.mapstructpractice.service;

import java.util.List;
import com.example.mapstructpractice.dto.employee.CreateEmployeeRequestDto;
import com.example.mapstructpractice.dto.employee.EmployeeDto;

public interface EmployeeService {
    List<EmployeeDto> findAll();

    EmployeeDto findById(Long id);

    EmployeeDto getByName(String name);

    EmployeeDto save(CreateEmployeeRequestDto requestDto);
}
