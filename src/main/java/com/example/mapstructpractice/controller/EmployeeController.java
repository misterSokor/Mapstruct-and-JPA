package com.example.mapstructpractice.controller;

import java.util.List;
import com.example.mapstructpractice.dto.employee.CreateEmployeeRequestDto;
import com.example.mapstructpractice.dto.employee.EmployeeDto;
import com.example.mapstructpractice.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/employees")
@RequiredArgsConstructor
public class EmployeeController {
    private final EmployeeService employeeService;

    @GetMapping
    public List<EmployeeDto> findAll() {
        return employeeService.findAll();
    }

    @GetMapping("/{id}")
    public EmployeeDto findById(@PathVariable Long id) {
        return employeeService.findById(id);
    }

    @GetMapping("/by-name")
    public EmployeeDto getAllByName(@RequestParam String name) {
        return employeeService.getByName(name);
    }

    @PostMapping
    public EmployeeDto save(@RequestBody CreateEmployeeRequestDto requestDto) {
        return employeeService.save(requestDto);
    }
}
