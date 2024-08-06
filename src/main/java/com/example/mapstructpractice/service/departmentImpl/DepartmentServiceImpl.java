package com.example.mapstructpractice.service.departmentImpl;

import java.util.List;
import com.example.mapstructpractice.dto.department.CreateDepartmentRequestDto;
import com.example.mapstructpractice.dto.department.DepartmentDto;
import com.example.mapstructpractice.mapper.DepartmentMapper;
import com.example.mapstructpractice.model.Department;
import com.example.mapstructpractice.repository.DepartmentRepository;
import com.example.mapstructpractice.service.DepartmentService;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {
    private final DepartmentRepository repository;
    private final DepartmentMapper mapper;

    @Override
    public List<DepartmentDto> findAll() {
        return repository
                .findAll()
                .stream()
                .map(mapper::toDto)
                .toList();
    }

    @Override
    public DepartmentDto save(CreateDepartmentRequestDto requestDto) {
        Department department = mapper.toModel(requestDto);
        Department savedDepartment = repository.save(department);
        return mapper.toDto(savedDepartment);
    }
}
