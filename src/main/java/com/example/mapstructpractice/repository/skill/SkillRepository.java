package com.example.mapstructpractice.repository.skill;

import java.util.List;
import java.util.Optional;
import com.example.mapstructpractice.model.Skill;

public interface SkillRepository {
    Skill save(Skill skill);

    List<Skill> findAll();

    Optional<Skill> findById(Long id);

//    List<Skill> findAllById(List<Long> skills);
}
