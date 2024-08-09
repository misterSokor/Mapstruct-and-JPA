package com.example.mapstructpractice.dto.employee;

import lombok.Data;

@Data
public class EmployeeWithoutSkillsDto {
    private Long id;
    private String name;
    private String email;
    private Long employeeDepartmentId;
}
