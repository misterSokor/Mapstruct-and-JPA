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

import java.util.List;
import java.util.stream.Collectors;
import com.example.mapstructpractice.config.MapperConfig;
import com.example.mapstructpractice.dto.employee.CreateEmployeeRequestDto;
import com.example.mapstructpractice.dto.employee.EmployeeDto;
import com.example.mapstructpractice.dto.employee.EmployeeWithoutSkillsDto;
import com.example.mapstructpractice.model.Employee;
import com.example.mapstructpractice.model.Skill;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(config = MapperConfig.class, uses = DepartmentMapper.class)
public interface EmployeeMapper {
    @Mapping(target = "employeeDepartmentId", source = "department.id")
    @Mapping(target = "skills", ignore = true)
    EmployeeDto toDto(Employee employee);

    @AfterMapping
    default void setSkillIds(
            @MappingTarget EmployeeDto employeeDto, Employee employee) {
        List<Long> skillIds = employee.getSkillList()
                .stream()
                .map(skill -> skill.getId())
                .collect(Collectors.toList());
        employeeDto.setSkills(skillIds);
    }

    EmployeeWithoutSkillsDto toEmployeeWithoutSkillsDto(Employee employee);

    @Mapping(target = "department", source = "departmentId", qualifiedByName
            = "getById" )
    @Mapping(target = "skillList", ignore = true)
    Employee toModel(CreateEmployeeRequestDto requestDto);

    @AfterMapping
    default void setAllSkillNumbers(
            CreateEmployeeRequestDto requestDto,
            @MappingTarget Employee employee) {
        List<Skill> skills = requestDto.skills()
                .stream()
                .map(id -> new Skill(id))
                .toList();
        employee.setSkillList(skills);
    }
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