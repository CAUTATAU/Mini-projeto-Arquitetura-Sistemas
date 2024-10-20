package com.example.mini_projeto.DTOs.Factories;


import com.example.mini_projeto.DTOs.SubjectDTO;
import org.springframework.stereotype.Component;

@Component
public class SubjectDTOFactoryImp implements SubjectDTOFactory {
    @Override
    public SubjectDTO createSubjectDTO(String nome, String curso) {
        return new SubjectDTO(nome, curso);
    }
}
