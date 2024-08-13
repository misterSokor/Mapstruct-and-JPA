package com.example.mapstructpractice.repository.employee;

import java.util.List;
import java.util.Optional;
import com.example.mapstructpractice.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Employee save(Employee employee);
    Optional<Employee> findById(Long id);
    Employee findByName(String name);
    List<Employee> findAll();
}
