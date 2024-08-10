package com.example.mapstructpractice.dto.employee;

import java.util.List;
import lombok.Data;

@Data
public class EmployeeDto {
    private Long id;
    private String name;
    private String email;
    private Long employeeDepartmentId;
//    List<String> skills; // for fetching skills names
    List<Long> skills; // for fetching skills ids
}
