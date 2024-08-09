package com.example.mapstructpractice.model;

import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@Entity
@Table(name = "employees")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(unique = true)
    private String email;

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @ManyToOne
    private Department department;

    @ToString.Exclude
    @Column(name = "social_security_number", unique = true)
    private String socialSecurityNumber;

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @ManyToMany
    @JoinTable(name = "employee_skills",
              joinColumns = @JoinColumn(name = "employee_id"),
                inverseJoinColumns = @JoinColumn(name = "skill_id"))
    private List<Skill> skillList;
    //but if I need to avoid NullPointerException I have to initialize the list
   // private List<Skill> skillList = new ArrayList<>();

}
