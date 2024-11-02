package com.example.mini_projeto.Services.Filters;

import com.example.mini_projeto.Models.Subject;
import org.springframework.stereotype.Component;

@Component
public class SubjectFilter {
    public boolean checkIfSubjectIsHistory(Subject subject) {
        return subject.getCurso().equals("História");
    }
}
