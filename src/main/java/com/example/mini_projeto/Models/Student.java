package com.example.mini_projeto.Models;

import com.example.mini_projeto.Models.Enums.StudentModality;
import com.example.mini_projeto.Models.Enums.StudentStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
    @Column(name = "nome")
    String nome;
    @Column(name = "curso")
    String curso;
    @Column(name = "modalidade")
    @Enumerated(EnumType.STRING)
    StudentModality modalidade;
    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    StudentStatus status;
    @ManyToMany(mappedBy = "students")
    private Set<Subject> subjects = new HashSet<>();
}
