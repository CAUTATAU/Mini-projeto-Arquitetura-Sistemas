package com.example.mini_projeto.Services;

import com.example.mini_projeto.Models.Enums.StudentModality;
import com.example.mini_projeto.Models.Student;
import com.example.mini_projeto.Models.Subject;
import org.springframework.stereotype.Service;

@Service
public class ModelsFilter {
    public boolean checkIfIsHistoryStudentAndPresencial(Student student) {
        return student.getCurso().equals("História") && student.getModalidade() == StudentModality.Presencial;
    }

    public boolean checkIfSubjectIsHistory(Subject subject) {
        return subject.getCurso().equals("História");
    }
}
