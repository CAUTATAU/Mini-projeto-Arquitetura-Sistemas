package com.example.mini_projeto.Services;


import com.example.mini_projeto.DTOs.SubjectDTO;
import com.example.mini_projeto.DTOs.Factories.SubjectDTOFactory;
import com.example.mini_projeto.Models.Student;
import com.example.mini_projeto.Models.Subject;
import com.example.mini_projeto.Repositories.SubjectRepository;
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
    @Autowired
    SubjectRepository subjectRepository;

    private List<Subject> getSubjects() {
        ResponseEntity<List<Subject>> response = restTemplate.exchange(
                "https://sswfuybfs8.execute-api.us-east-2.amazonaws.com/disciplinaServico/msDisciplina",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Subject>>() {}
        );
        return response.getBody();
    }

    private List<Subject> getallHistorySubjects() {
        List<Subject> subjects = getSubjects();
        List<Subject> historySubjects = new ArrayList<>();
        for (Subject subject : subjects) {
            if(subject.getCurso().equals("História")){
                historySubjects.add(subject);
            }
        }
        return historySubjects;
    }

    private void syncDbWithApi(){
        List<Subject> subjects = getallHistorySubjects();
        for(Subject subject : subjects){
            if(subjectRepository.findById(subject.getId()).isEmpty()){
                subjectRepository.save(subject);
            }
        }
    }

    public List<SubjectDTO> getAllSubjects(){
        this.syncDbWithApi();
        List<Subject> subjects = subjectRepository.findAll();
        List<SubjectDTO> subjectDTOs = new ArrayList<>();
        for(Subject subject : subjects){
            subjectDTOs.add(subjectDTOFactory.createSubjectDTO(subject.getNome(), subject.getCurso()));
        }
        return subjectDTOs;
    }
}
