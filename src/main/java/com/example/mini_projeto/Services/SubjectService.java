package com.example.mini_projeto.Services;


import com.example.mini_projeto.Models.Student;
import com.example.mini_projeto.Models.Subject;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class SubjectService {

    RestTemplate restTemplate = new RestTemplate();

    private List<Subject> getSubjects() {
        ResponseEntity<List<Subject>> response = restTemplate.exchange(
                "https://sswfuybfs8.execute-api.us-east-2.amazonaws.com/disciplinaServico/msDisciplina",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Subject>>() {}
        );
        return response.getBody();
    }

    public List<Subject> getallHistorySubjects() {
        List<Subject> subjects = getSubjects();
        List<Subject> historySubjects = new ArrayList<>();
        for (Subject subject : subjects) {
            if(subject.getCurso().equals("História")){
                historySubjects.add(subject);
            }
        }
        return historySubjects;
    }
}
