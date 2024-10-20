package com.example.mini_projeto.Controllers;


import com.example.mini_projeto.Models.Student;
import com.example.mini_projeto.Services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {
    @Autowired
    StudentService studentService = new StudentService();
    @GetMapping
    public ResponseEntity<List<Student>> getAllStudents() {
        return ResponseEntity.ok(studentService.getAllStudents());
    }
    @GetMapping("/{valor}")
    public ResponseEntity<?> getStudentByIdOrName(@PathVariable String valor) throws Exception {
        try {
            long id = Long.parseLong(valor);
            return ResponseEntity.ok(studentService.getStudentById(id));
        } catch (NumberFormatException e) {
            return ResponseEntity.ok(studentService.getStudentByName(valor));
        }
    }

}
