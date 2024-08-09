package com.example.mapstructpractice.service.skill;

import java.util.List;
import com.example.mapstructpractice.dto.skill.CreateSkillRequestDto;
import com.example.mapstructpractice.dto.skill.SkillDto;
import com.example.mapstructpractice.mapper.SkillMapper;
import com.example.mapstructpractice.model.Skill;
import com.example.mapstructpractice.repository.skill.SkillRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class SkillServiceImpl implements SkillService {
    private final SkillRepository repository;
    private final SkillMapper mapper;

    @Override
    public SkillDto save(CreateSkillRequestDto requestDto) {
        Skill skill = mapper.toModel(requestDto);
        Skill saved = repository.save(skill);
        return mapper.toDto(saved);
    }

    @Override
    public List<SkillDto> findAll() {
        return repository.findAll()
                .stream()
                .map(mapper::toDto)
                .toList();
    }
}
