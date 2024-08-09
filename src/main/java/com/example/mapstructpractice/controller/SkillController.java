package com.example.mapstructpractice.controller;

import java.util.List;
import com.example.mapstructpractice.dto.skill.CreateSkillRequestDto;
import com.example.mapstructpractice.dto.skill.SkillDto;
import com.example.mapstructpractice.service.skill.SkillService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/skills")
public class SkillController {
    private final SkillService skillService;

    @GetMapping
    public List<SkillDto> findAll() {
        return skillService.findAll();
    }

    @PostMapping
    public SkillDto save(@RequestBody CreateSkillRequestDto requestDto) {
        return skillService.save(requestDto);
    }
}
