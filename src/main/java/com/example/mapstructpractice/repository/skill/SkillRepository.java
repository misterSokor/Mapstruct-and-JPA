package com.example.mapstructpractice.repository.skill;

import java.util.List;
import com.example.mapstructpractice.model.Skill;

public interface SkillRepository {
    Skill save(Skill skill);

    List<Skill> findAll();
}
