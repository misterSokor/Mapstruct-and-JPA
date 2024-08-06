package com.example.mapstructpractice.mapper;

import com.example.mapstructpractice.config.MapperConfig;
import com.example.mapstructpractice.dto.skill.CreateSkillRequestDto;
import com.example.mapstructpractice.dto.skill.SkillDto;
import com.example.mapstructpractice.model.Skill;
import org.mapstruct.Mapper;

@Mapper(config = MapperConfig.class)
public interface SkillMapper {
    SkillDto toDto(Skill skill);
    Skill toModel(CreateSkillRequestDto requestDto);
}
