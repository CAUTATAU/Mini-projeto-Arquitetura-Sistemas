package com.example.mini_projeto.Controllers;

import com.example.mini_projeto.DTOs.SubjectDTO;
import com.example.mini_projeto.Models.Subject;
import com.example.mini_projeto.Services.Interface.ModelsService;
import com.example.mini_projeto.Services.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/subjects")
public class SubjectController {

    @Autowired
    ModelsService<Subject> subjectService;
    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping
    public ResponseEntity<List<Subject>> getAllSubjects() {
        return ResponseEntity.ok(subjectService.getAll());
    }
}
