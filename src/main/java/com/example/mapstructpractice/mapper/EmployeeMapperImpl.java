package com.example.mapstructpractice.mapper;

import com.example.mapstructpractice.dto.employee.CreateEmployeeRequestDto;
import com.example.mapstructpractice.dto.employee.EmployeeDto;
import com.example.mapstructpractice.model.Department;
import com.example.mapstructpractice.model.Employee;
import org.springframework.stereotype.Component;

//this class is a custom mapper in case I need to use it instead of mapstruct
// library, and this class works the same as EmployeeMapper with mapstruct


//@Component

public class EmployeeMapperImpl implements EmployeeMapper {
    private final DepartmentMapper mapper;

    public EmployeeMapperImpl(DepartmentMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public EmployeeDto toDto(Employee employee) {
        if (employee == null) {
            return null;
        }

        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setId(employee.getId());
        employeeDto.setName(employee.getName());
        employeeDto.setEmail(employee.getEmail());

        return employeeDto;
    }

    @Override
    public Employee toModel(CreateEmployeeRequestDto requestDto) {
        if (requestDto == null) {
            return null;
        }

        Employee employee = new Employee();
        employee.setName(requestDto.name());
        employee.setEmail(requestDto.email());
        employee.setDepartment(mapper.getById(requestDto.departmentId()));
        return employee;
    }
}
