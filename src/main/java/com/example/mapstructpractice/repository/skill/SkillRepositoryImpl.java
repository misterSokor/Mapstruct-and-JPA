package com.example.mapstructpractice.repository.skill;

import java.util.List;
import com.example.mapstructpractice.model.Skill;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class SkillRepositoryImpl implements SkillRepository {


    @Override
    public Skill save(Skill skill) {
        return null;
    }

    @Override
    public List<Skill> findAll() {
        return null;
    }
}
