package com.example.mini_projeto.Controllers;

import com.example.mini_projeto.DTOs.EnrollDTO;
import com.example.mini_projeto.Services.EnrollService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/enroll")
public class EnrollController {
    @Autowired
    EnrollService enrollService;
    @PostMapping()
    public ResponseEntity<String> enrollStudentInSubject(@RequestBody EnrollDTO data) {
        try {
            enrollService.enrollStudentInSubject(data);
            return ResponseEntity.ok("Estudante matriculado com sucesso na disciplina");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
