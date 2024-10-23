package com.example.mini_projeto.Services;


import com.example.mini_projeto.DTOs.SubjectDTO;
import com.example.mini_projeto.DTOs.Factories.SubjectDTOFactory;
import com.example.mini_projeto.Models.Subject;
import com.example.mini_projeto.Repositories.SubjectRepository;
import com.example.mini_projeto.Services.Interface.ModelsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class SubjectService implements ModelsService<Subject> {

    @Autowired
    ExternalAPIService externalAPIService;
    @Autowired
    SubjectDTOFactory subjectDTOFactory;
    @Autowired
    SubjectRepository subjectRepository;
    @Autowired
    ModelsFilter modelFilter;




    @Override
    public List<Subject> getAll() {
        List<Subject> subjects = externalAPIService.getSubjects();
        List<Subject> historySubjects = new ArrayList<>();
        for (Subject subject : subjects) {
            if(modelFilter.checkIfSubjectIsHistory(subject)) {
                historySubjects.add(subject);
            }
        }
        return historySubjects;
    }

    @Override
    public void syncWithDatabase() {
        List<Subject> subjects = getAll();
        for(Subject subject : subjects){
            if(subjectRepository.findById(subject.getId()).isEmpty()){
                subjectRepository.save(subject);
            }
        }
    }

    public List<SubjectDTO> getAllSubjects(){
        this.syncWithDatabase();
        List<Subject> subjects = subjectRepository.findAll();
        List<SubjectDTO> subjectDTOs = new ArrayList<>();
        for(Subject subject : subjects){
            subjectDTOs.add(subjectDTOFactory.createSubjectDTO(subject.getNome(), subject.getCurso()));
        }
        return subjectDTOs;
    }


}
