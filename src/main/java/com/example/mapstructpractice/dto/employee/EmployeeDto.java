package com.example.mapstructpractice.dto.employee;

import lombok.Data;

@Data
public class EmployeeDto {
    private Long id;
    private String name;
    private String email;
    private Long employeeDepartmentId;
}
