package com.example.mini_projeto.Controllers;


import com.example.mini_projeto.Models.Student;
import com.example.mini_projeto.Models.Subject;
import com.example.mini_projeto.Services.Interface.ModelsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {
    @Autowired
    ModelsService<Student> studentService;
    @GetMapping
    public ResponseEntity<List<Student>> getAllStudents() {
        return ResponseEntity.ok(studentService.getAll());
    }
    @GetMapping("/{valor}")
    public ResponseEntity<?> getStudentByIdOrName(@PathVariable String valor) throws Exception {
        try {
            long id = Long.parseLong(valor);
            return ResponseEntity.ok(studentService.getById(id));
        } catch (NumberFormatException e) {
            return ResponseEntity.ok(studentService.getByName(valor));
        }
    }

    @GetMapping("/{studentId}/subjects")
    public ResponseEntity<List<Subject>> getSubjectsByStudentId(@PathVariable long studentId) {
        try {
            List<Subject> subjects = studentService.getSubjectsByStudentId(studentId);
            return ResponseEntity.ok(subjects);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

}
