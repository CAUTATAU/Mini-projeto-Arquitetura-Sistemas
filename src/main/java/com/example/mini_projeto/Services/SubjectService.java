package com.example.mini_projeto.Services;


import com.example.mini_projeto.DTOs.Factories.ListFactory;
import com.example.mini_projeto.Models.Subject;
import com.example.mini_projeto.Repositories.SubjectRepository;
import com.example.mini_projeto.Services.Filters.SubjectFilter;
import com.example.mini_projeto.Services.Interface.ExternalAPI;
import com.example.mini_projeto.Services.Interface.ModelsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubjectService implements ModelsService<Subject> {

    @Autowired
    ExternalAPI<Subject> externalAPI;
    @Autowired
    SubjectRepository subjectRepository;
    @Autowired
    SubjectFilter subjectFilter;
    @Autowired
    ListFactory<Subject> subjectListFactory;


    @Override
    public List<Subject> getAPI() {
        List<Subject> subjects = externalAPI.getAll();
        List<Subject> historySubjects = subjectListFactory.createNewList();
        for (Subject subject : subjects) {
            if(subjectFilter.checkIfSubjectIsHistory(subject)) {
                historySubjects.add(subject);
            }
        }
        return historySubjects;
    }

    @Override
    public void syncWithDatabase() {
        List<Subject> subjects = getAPI();
        for(Subject subject : subjects){
            if(subjectRepository.findById(subject.getId()).isEmpty()){
                subjectRepository.save(subject);
            }
        }
    }

    @Override
    public List<Subject> getAll() {
        this.syncWithDatabase();
        return subjectRepository.findAll();

    }

    @Override
    public Subject getById(long id) {
        return subjectRepository.findById(id).orElse(null);
    }

    @Override
    public Subject getByName(String nome) {
        return subjectRepository.findByNome(nome);
    }

    @Override
    public List<Subject> getSubjectsByStudentId(long studentId) {
        return null;
    }


}
