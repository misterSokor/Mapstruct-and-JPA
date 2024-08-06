package com.example.mapstructpractice.repository.departmentRepositoryImpl;

import java.util.List;
import com.example.mapstructpractice.model.Department;
import com.example.mapstructpractice.repository.DepartmentRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class DepartmentRepositoryImpl implements DepartmentRepository {
    private final EntityManagerFactory entityManagerFactory;

    @Override
    public Department save(Department department) {
        EntityManager entityManager = null;
        EntityTransaction transaction = null;
        try {
            entityManager = entityManagerFactory.createEntityManager();
            transaction = entityManager.getTransaction();
            transaction.begin();
            entityManager.persist(department);
            transaction.commit();
            return department;
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            throw new RuntimeException("Can't insert the department", e);
        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }
    }

    @Override
    public List<Department> findAll() {
        try (EntityManager entityManager =
                     entityManagerFactory.createEntityManager()) {
            return entityManager.createQuery(
                    "from Department ", Department.class).getResultList();
        } catch (RuntimeException e) {
            throw new RuntimeException("Can't find all departments", e);
        }
    }
}
