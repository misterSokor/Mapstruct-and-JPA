package com.example.mapstructpractice.service.skill;

import java.util.List;
import com.example.mapstructpractice.dto.skill.CreateSkillRequestDto;
import com.example.mapstructpractice.dto.skill.SkillDto;

public interface SkillService {
    SkillDto save(CreateSkillRequestDto requestDto);

    List<SkillDto> findAll();
}
