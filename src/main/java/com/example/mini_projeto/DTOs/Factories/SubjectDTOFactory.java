package com.example.mini_projeto.DTOs.Factories;

import com.example.mini_projeto.DTOs.SubjectDTO;

public interface SubjectDTOFactory {
    SubjectDTO createSubjectDTO(String nome, String curso);
}
