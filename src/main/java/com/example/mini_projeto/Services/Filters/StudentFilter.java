package com.example.mini_projeto.Services.Filters;

import com.example.mini_projeto.Models.Enums.StudentModality;
import com.example.mini_projeto.Models.Enums.StudentStatus;
import com.example.mini_projeto.Models.Student;
import org.springframework.stereotype.Component;

@Component
public class StudentFilter {
    public boolean checkIfIsHistoryStudent(Student student) {
        return student.getCurso().equals("História");
    }

    public boolean checkIfStudentIsPresencial(Student student) {
        return student.getModalidade() == StudentModality.Presencial;
    }

    public boolean checkIfStudentIsActive(Student student) {
        return student.getStatus().equals(StudentStatus.Ativo) ;
    }
}
