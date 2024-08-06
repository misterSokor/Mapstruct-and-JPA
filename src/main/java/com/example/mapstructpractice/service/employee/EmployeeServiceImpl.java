package com.example.mapstructpractice.service.employee;

import java.util.List;
import java.util.Optional;
import java.util.Random;
import com.example.mapstructpractice.dto.employee.CreateEmployeeRequestDto;
import com.example.mapstructpractice.dto.employee.EmployeeDto;
import com.example.mapstructpractice.mapper.EmployeeMapper;
import com.example.mapstructpractice.model.Employee;
import com.example.mapstructpractice.repository.employee.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeMapper mapper;
    private final EmployeeRepository repository;


    @Override
    public List<EmployeeDto> findAll() {
        return repository
                .findAll()
                .stream()
                .map(mapper::toDto)
                .toList();
    }

    @Override
    public EmployeeDto findById(Long id) {
        Optional<Employee> empById = repository.findById(id);
        if (empById.isPresent()) {
            return mapper.toDto(empById.get());
        }
        throw new RuntimeException("was not found employee with id: " + id);
    }

    @Override
    public EmployeeDto getByName(String name) {
        Employee employeeByName = repository.findByName(name);
        EmployeeDto employeeDto = mapper.toDto(employeeByName);
        return employeeDto;
    }

    @Override
    public EmployeeDto save(CreateEmployeeRequestDto requestDto) {
        Employee employee = mapper.toModel(requestDto);
        employee.setSocialSecurityNumber("abc " + (new Random().nextInt(1000)));
        Employee savedEmployee = repository.save(employee);
        return mapper.toDto(savedEmployee);
    }
}



/*
package com.example.mapstructpractice.service.employeeImpl;

import java.util.List;
import java.util.Optional;
import com.example.mapstructpractice.dto.employee.CreateEmployeeRequestDto;
import com.example.mapstructpractice.dto.employee.EmployeeDto;
import com.example.mapstructpractice.mapper.EmployeeMapper;
import com.example.mapstructpractice.model.Employee;
import com.example.mapstructpractice.repository.employee.EmployeeRepository;
import com.example.mapstructpractice.service.employee.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final EmployeeMapper employeeMapper;
    @Override
    public List<EmployeeDto> findAll() {
        return employeeRepository
                .findAll()
                .stream()
                .map(employeeMapper::toDto)
                .toList();
    }

    @Override
    public EmployeeDto findById(Long id) {
        Optional<Employee> employee = employeeRepository.findById(id);
        if (employee.isPresent()) {
            return employeeMapper.toDto(employee.get());
        } else {
            throw new RuntimeException("Employee whit id " + id + " not found");
        }
    }

    @Override
    public EmployeeDto getByName(String name) {
        Employee byName = employeeRepository.findByName(name);
        EmployeeDto employeeDto = employeeMapper.toDto(byName);
        return employeeDto;
    }

    @Override
    public EmployeeDto save(CreateEmployeeRequestDto requestDto) {
        Employee employee = employeeMapper.toModel(requestDto);
        Employee savedEmployee = employeeRepository.save(employee);
        return employeeMapper.toDto(savedEmployee);
    }
}

 */