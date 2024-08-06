package com.example.mapstructpractice.controller;

import java.util.List;
import com.example.mapstructpractice.dto.department.CreateDepartmentRequestDto;
import com.example.mapstructpractice.dto.department.DepartmentDto;
import com.example.mapstructpractice.service.department.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/departments")
public class DepartmentController {
    private final DepartmentService departmentService;

    @GetMapping
    public List<DepartmentDto> findAll() {
        return departmentService.findAll();
    }

    @PostMapping
    public DepartmentDto save(@RequestBody CreateDepartmentRequestDto requestDto) {
        return departmentService.save(requestDto);
    }
}
