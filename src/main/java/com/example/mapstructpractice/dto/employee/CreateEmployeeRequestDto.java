package com.example.mapstructpractice.dto.employee;

public record CreateEmployeeRequestDto(String name, String email,
                                       Long departmentId) {
}
