package com.example.mini_projeto.Services;


import com.example.mini_projeto.DTOs.SubjectDTO;
import com.example.mini_projeto.DTOs.Factories.SubjectDTOFactory;
import com.example.mini_projeto.Models.Subject;
import org.springframework.beans.factory.annotation.Autowired;
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
    @Autowired
    SubjectDTOFactory subjectDTOFactory;

    private List<Subject> getSubjects() {
        ResponseEntity<List<Subject>> response = restTemplate.exchange(
                "https://sswfuybfs8.execute-api.us-east-2.amazonaws.com/disciplinaServico/msDisciplina",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Subject>>() {}
        );
        return response.getBody();
    }

    public List<SubjectDTO> getallHistorySubjects() {
        List<Subject> subjects = getSubjects();
        List<SubjectDTO> historySubjects = new ArrayList<>();
        for (Subject subject : subjects) {
            if(subject.getCurso().equals("História")){
                historySubjects.add(subjectDTOFactory.createSubjectDTO(subject.getNome(),subject.getCurso()));
            }
        }
        return historySubjects;
    }
}
