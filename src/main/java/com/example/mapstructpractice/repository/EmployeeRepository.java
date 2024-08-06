package com.example.mapstructpractice.repository;

import java.util.List;
import java.util.Optional;
import com.example.mapstructpractice.model.Employee;

public interface EmployeeRepository {
    Employee save(Employee employee);
    Optional<Employee> findById(Long id);
    Employee findByName(String name);
    List<Employee> findAll();
}
