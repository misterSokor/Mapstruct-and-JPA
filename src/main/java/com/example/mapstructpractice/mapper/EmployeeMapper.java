/* package com.example.mapstructpractice.mapper;

import com.example.mapstructpractice.config.MapperConfig;
import com.example.mapstructpractice.dto.employee.CreateEmployeeRequestDto;
import com.example.mapstructpractice.dto.employee.EmployeeDto;
import com.example.mapstructpractice.model.Employee;
import org.mapstruct.Mapper;

@Mapper(config = MapperConfig.class)
public interface EmployeeMapper {
    EmployeeDto toDto(Employee employee);
    Employee toModel(CreateEmployeeRequestDto employeeDto);
}
*/

// for custom mapper

package com.example.mapstructpractice.mapper;

import com.example.mapstructpractice.config.MapperConfig;
import com.example.mapstructpractice.dto.employee.CreateEmployeeRequestDto;
import com.example.mapstructpractice.dto.employee.EmployeeDto;
import com.example.mapstructpractice.model.Employee;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MapperConfig.class, uses = DepartmentMapper.class)
public interface EmployeeMapper {
    @Mapping(target = "employeeDepartmentId", source = "department.id")
    EmployeeDto toDto(Employee employee);

    @Mapping(target = "department", source = "departmentId", qualifiedByName
            = "getById" )
    Employee toModel(CreateEmployeeRequestDto employeeDto);
}









/*
@Mapper(config = MapperConfig.class, uses = DepartmentMapper.class)
public interface EmployeeMapper {
    @Mapping(target = "employeeDepartmentId", source = "department.id")
    EmployeeDto toDto(Employee employee);

    @Mapping(target = "department",
            source = "departmentId",
            qualifiedByName = "departmentById")
    Employee toModel(CreateEmployeeRequestDto employeeDto);
}


 */