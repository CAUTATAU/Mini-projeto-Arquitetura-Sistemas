package com.example.mini_projeto.Services.Interface;

import com.example.mini_projeto.Models.Subject;

import java.util.List;

public interface ModelsService<T> {
     List<T> getAPI();
     void syncWithDatabase();
     List<T> getAll();
     T getById(long id);
     T getByName(String nome);

     List<Subject> getSubjectsByStudentId(long studentId);
}
