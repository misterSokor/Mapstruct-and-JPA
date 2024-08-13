package com.example.mapstructpractice.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import com.example.mapstructpractice.dto.employee.CreateEmployeeRequestDto;
import com.example.mapstructpractice.dto.employee.EmployeeDto;
import com.example.mapstructpractice.dto.employee.EmployeeWithoutSkillsDto;
import com.example.mapstructpractice.model.Employee;
import com.example.mapstructpractice.model.Skill;
import com.example.mapstructpractice.repository.skill.SkillRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

//this class is a custom mapper in case I need to use it instead of mapstruct
// library, and this class works the same as EmployeeMapper with mapstruct


@Component
@RequiredArgsConstructor
public class EmployeeMapperImpl implements EmployeeMapper {
    private final DepartmentMapper departmentMapper;

    @Override
    public EmployeeDto toDto(Employee employee) {
        if (employee == null) {
            return null;
        }

        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setId(employee.getId());
        employeeDto.setName(employee.getName());
        employeeDto.setEmail(employee.getEmail());
        if (employee.getDepartment() != null) {
            employeeDto.setEmployeeDepartmentId(employee.getDepartment().getId());
        }
        setSkillIds(employeeDto, employee);
        return employeeDto;
    }

    // method setSkillIds using stream for Long id of skill with custom
    // mapper class
    private void setSkillIds(EmployeeDto employeeDto, Employee employee) {
        List<Long> skillIds = employee.getSkillList()
                .stream()
                .map(skill -> skill.getId())
                .collect(Collectors.toList());
        employeeDto.setSkills(skillIds);
    }


        // method setSkillIds using stream for skill names
    /*
    private void setSkillIds(EmployeeDto employeeDto, Employee employee) {
        if (employee.getSkillList() != null) {
            List<String> skillNames = employee.getSkillList()
                    .stream()
                    .map(skill -> skill.getName())
                    .collect(Collectors.toList());
            employeeDto.setSkills(skillNames);
        } else {
            employeeDto.setSkills(new ArrayList<>());
        }
    }
    */

    // the same method setSkillIds without using stream and lambda
    /*
        private void setSkillIds(EmployeeDto employeeDto, Employee employee) {
        if (employee.getSkillList() != null) {
            List<String> skillNames = new ArrayList<>();
            for (Skill skill : employee.getSkillList()) {
                String name = skill.getName();
                skillNames.add(name);
            }
            employeeDto.setSkills(skillNames);
        } else {
            employeeDto.setSkills(new ArrayList<>());
        }
    }
     */
    // method toEmployeeWithoutSkillsDto using stream for Long id of skill
    public EmployeeWithoutSkillsDto toEmployeeWithoutSkillsDto(Employee employee) {
        EmployeeWithoutSkillsDto employeeDto = new EmployeeWithoutSkillsDto();
        employeeDto.setId(employee.getId());
        employeeDto.setName(employee.getName());
        employeeDto.setEmail(employee.getEmail());
        if (employee.getDepartment() != null) {
            employeeDto.setEmployeeDepartmentId(employee.getDepartment().getId());
        }
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
        employee.setDepartment(departmentMapper.getById(requestDto.departmentId()));
        setAllSkillNumbers(requestDto.skills(), employee);
        return employee;
    }

//     method setAllSkills using stream for Long id of skill
//    private void setAllSkillNumbers(List<Long> skillIds, Employee employee) {
//        List<Skill> skills = skillIds
//                .stream()
//                .map(id -> new Skill(id))
//                .toList();
//        employee.setSkillList(skills);
//    }

    // the same method setAllSkills without using stream and lambda

        private void setAllSkillNumbers(List<Long> skillIds, Employee employee) {
        List<Skill> skills = new ArrayList<>();
        for (Long id : skillIds) {
            Skill skill = new Skill(id);
            skills.add(skill);
        }
        employee.setSkillList(skills);
    }
}



