package com.example.mapstructpractice.repository.employee;

import java.util.List;
import java.util.Optional;
import com.example.mapstructpractice.model.Employee;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class EmployeeRepositoryImpl implements EmployeeRepository {
    private final EntityManagerFactory entityManagerFactory;
    @Override
    public Employee save(Employee employee) {
        EntityManager entityManager = null;
        EntityTransaction transaction = null;

        try {
            entityManager = entityManagerFactory.createEntityManager();
            transaction = entityManager.getTransaction();
            transaction.begin();
            entityManager.persist(employee);
            transaction.commit();
            return employee;
        } catch (RuntimeException e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            throw new RuntimeException("can't insert employee" + e);
        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }
    }

    @Override
    public Optional<Employee> findById(Long id) {
        try (EntityManager entityManager =
                     entityManagerFactory.createEntityManager()) {
            Employee employee = entityManager.find(Employee.class, id);
            return employee != null ? Optional.of(employee) : Optional.empty();
        }
    }

    @Override
    public Employee findByName(String name) {
        String lowerCase = name.toLowerCase();
        try (EntityManager entityManager =
                     entityManagerFactory.createEntityManager()) {
                return entityManager.createQuery("SELECT e FROM Employee e WHERE "
                                             + "lower(e"
                                      + ".name) LIKE : name", Employee.class)
                    .setParameter("name", "%" + lowerCase + "%")
                    .getSingleResult();
        } catch (RuntimeException e) {
            throw new RuntimeException("Can not find the employee with name: " + name, e);
        }
    }

    @Override
    public List<Employee> findAll() {
        try (EntityManager entityManager =
                     entityManagerFactory.createEntityManager()) {
            return entityManager.createQuery(
                    "SELECT e FROM Employee e JOIN FETCH e.skillList",
                            Employee.class)
                    .getResultList();
        } catch (RuntimeException e) {
            throw new RuntimeException("Can't get all Employees entities", e);
        }
    }
}



/*
package com.example.mapstructpractice.repository.employeeRepositoryImpl;

import java.util.List;
import java.util.Optional;
import com.example.mapstructpractice.model.Employee;
import com.example.mapstructpractice.repository.employee.EmployeeRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class EmployeeRepositoryImpl implements EmployeeRepository {
    private final EntityManagerFactory entityManagerFactory;
    @Override
    public Employee save(Employee employee) {
        EntityManager entityManager = null;
        EntityTransaction transaction = null;

        try {
            entityManager = entityManagerFactory.createEntityManager();
            transaction = entityManager.getTransaction();
            transaction.begin();
            entityManager.persist(employee);
            transaction.commit();
            return employee;
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            throw new RuntimeException("Can't insert Employee", e);
        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }

    }

    @Override
    public Optional<Employee> findById(Long id) {
        try (EntityManager entityManager =
                     entityManagerFactory.createEntityManager()) {
            Employee employee = entityManager.find(Employee.class, id);
            return employee != null ? Optional.of(employee) : Optional.empty();
        }
    }

    @Override
    public Employee findByName(String name) {
        String lowerCaseName = name.toLowerCase();
        try (EntityManager entityManager =
                     entityManagerFactory.createEntityManager()) {
           return entityManager.createQuery("SELECT e FROM Employee e where "
                                         + "lower(e"
                                      + ".name) LIKE :name", Employee.class)
                    .setParameter("name", "%" + lowerCaseName + "%")
                   .getSingleResult();
        } catch (RuntimeException e) {
            throw new RuntimeException("There are no employees with name: " + name, e);
        }
    }

    @Override
    public List<Employee> findAll() {
        try (EntityManager entityManager =
                     entityManagerFactory.createEntityManager()) {
            return entityManager.createQuery("from Employee", Employee.class)
                    .getResultList();
        } catch (Exception e) {
            throw new RuntimeException("Can't get all Employees entities", e);
        }
    }
}

 */