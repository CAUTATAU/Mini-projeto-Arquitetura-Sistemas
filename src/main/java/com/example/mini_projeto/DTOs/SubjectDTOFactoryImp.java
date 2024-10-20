package com.example.mini_projeto.DTOs;


import org.springframework.stereotype.Component;

@Component
public class SubjectDTOFactoryImp implements SubjectDTOFactory {
    @Override
    public SubjectDTO createSubjectDTO(String nome, String curso) {
        return new SubjectDTO(nome, curso);
    }
}
