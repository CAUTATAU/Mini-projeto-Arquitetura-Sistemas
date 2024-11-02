package com.example.mini_projeto.Services;

import com.example.mini_projeto.DTOs.EnrollDTO;
import com.example.mini_projeto.Models.Enums.StudentModality;
import com.example.mini_projeto.Models.Enums.StudentStatus;
import com.example.mini_projeto.Models.Student;
import com.example.mini_projeto.Models.Subject;
import com.example.mini_projeto.Repositories.StudentRepository;
import com.example.mini_projeto.Repositories.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EnrollService {
    @Autowired
    StudentRepository studentRepository;
    @Autowired
    SubjectRepository subjectRepository;

    public void enrollStudentInSubject(EnrollDTO data) {
        // Recupera o estudante pelo ID
        Student student = studentRepository.findById(data.studentId()).orElse(null);
        if (student == null) {
            throw new RuntimeException("Estudante não encontrado");
        }


        // Recupera a disciplina pelo ID
        Subject subject = subjectRepository.findByNome(data.subjectName());
        if (subject == null) {
            throw new RuntimeException("Disciplina não encontrada");
        }

        student.getSubjects().add(subject);
        subject.getStudents().add(student);
        studentRepository.save(student);
        subjectRepository.save(subject);
    }
}
