package com.example.mapstructpractice.repository.skill;

import java.util.List;
import java.util.Optional;
import com.example.mapstructpractice.model.Skill;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class SkillRepositoryImpl implements SkillRepository {
    private final EntityManagerFactory entityManagerFactory;

    @Override
    public Skill save(Skill skill) {
        EntityManager entityManager = null;
        EntityTransaction transaction = null;
        try {
            entityManager = entityManagerFactory.createEntityManager();
            transaction = entityManager.getTransaction();
            transaction.begin();
            entityManager.persist(skill);
            transaction.commit();
            return skill;
        } catch (RuntimeException e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            throw new RuntimeException("Can't insert skill into database", e);
        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }
    }

    @Override
    public List<Skill> findAll() {
        try (EntityManager entityManager =
                     entityManagerFactory.createEntityManager()) {
            return entityManager.createQuery("from Skill ", Skill.class).getResultList();
        } catch (RuntimeException e) {
            throw new RuntimeException("Can't get all skills entities", e);
        }
    }

    @Override
    public Optional<Skill> findById(Long id) {
        try (EntityManager entityManager = entityManagerFactory.createEntityManager()) {
            Skill skill = entityManager.find(Skill.class, id);
            return Optional.ofNullable(skill);
        } catch (RuntimeException e) {
            throw new RuntimeException("Can't find skill by id", e);
        }
    }

    //to fetch names of skills from the database
//    @Override
//    public List<Skill> findAllById(List<Long> skills) {
//        try (EntityManager entityManager = entityManagerFactory.createEntityManager()) {
//            return entityManager.createQuery("SELECT s FROM Skill s where s"
//                                             + ".id in "
//                                             + ":skills", Skill.class)
//                    .setParameter("skills", skills)
//                    .getResultList();
//        } catch (RuntimeException e) {
//            throw new RuntimeException("Can't get all skills entities", e);
//        }
//    }
}
