package com.example.mini_projeto.Services;


import com.example.mini_projeto.Models.Enums.StudentModality;
import com.example.mini_projeto.Models.Student;
import com.example.mini_projeto.Models.Subject;
import com.example.mini_projeto.Repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
@Service
public class StudentService {
    RestTemplate restTemplate = new RestTemplate();

    @Autowired
    StudentRepository studentRepository;

    private List<Student> getStudents() {
        ResponseEntity<List<Student>> response = restTemplate.exchange(
                "https://rmi6vdpsq8.execute-api.us-east-2.amazonaws.com/msAluno",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Student>>() {}
        );
        return response.getBody();
    }

    private List<Student>getAllHistoryStudents(){
        List<Student> students = getStudents();
        List<Student> historyStudents = new ArrayList<Student>();
        for(Student student : students){
            if(student.getCurso().equals("História") && student.getModalidade().equals(StudentModality.Presencial)){
                historyStudents.add(student);
            }
        }
        return historyStudents;
    }

    private void syncDbWithApi(){
        List<Student> students = getAllHistoryStudents();
        for(Student student : students){
            if(studentRepository.findById(student.getId()).isEmpty()){
                studentRepository.save(student);
            }
        }
    }

    public List<Student> getAllStudents(){
        this.syncDbWithApi();
        return studentRepository.findAll();
    }

    public Student getStudentById(long id){
        return studentRepository.findById(id).orElse(null);
    }

    public Student getStudentByName(String nome) {
        return studentRepository.findByNome(nome);
    }

    public List<Subject> getSubjectsByStudentId(long studentId) {
        // Recupera o estudante pelo ID
        Student student = studentRepository.findById(studentId).orElse(null);
        if (student == null) {
            throw new RuntimeException("Estudante não encontrado");
        }

        // Retorna as disciplinas nas quais o estudante está matriculado
        return new ArrayList<>(student.getSubjects());
    }
}
