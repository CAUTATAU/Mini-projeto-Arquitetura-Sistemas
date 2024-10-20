package com.example.mini_projeto.Models;

import com.example.mini_projeto.Models.Enums.StudentModality;
import com.example.mini_projeto.Models.Enums.StudentStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Student {
    long id;
    String nome;
    String curso;
    StudentModality modalidade;
    StudentStatus status;
    List<Subject> disciplinas;
}
