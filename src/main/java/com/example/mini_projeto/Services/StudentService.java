package com.example.mini_projeto.Services;


import com.example.mini_projeto.DTOs.SearchStudentDTO;
import com.example.mini_projeto.Models.Enums.StudentModality;
import com.example.mini_projeto.Models.Student;
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

    public List<Student> getStudents() {
        ResponseEntity<List<Student>> response = restTemplate.exchange(
                "https://rmi6vdpsq8.execute-api.us-east-2.amazonaws.com/msAluno",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Student>>() {}
        );
        return response.getBody();
    }

    public List<Student>getAllHistoryStudents(){
        List<Student> students = getStudents();
        List<Student> historyStudents = new ArrayList<Student>();
        for(Student student : students){
            if(student.getCurso().equals("História") && student.getModalidade().equals(StudentModality.Presencial)){
                historyStudents.add(student);
            }
        }
        return historyStudents;
    }

    public Student getStudentById(long id) throws Exception{
        for(Student student : getAllHistoryStudents()){
            if(student.getId() == id) return student;
        }
        throw new Exception("Usuário não encontrado");
    }

    public Student getStudentByName(String nome) throws Exception {
        for(Student student : getAllHistoryStudents()) {
            if(student.getNome().equals(nome)) {  // Corrigido para usar a variável nome
                return student;
            }
        }
        throw new Exception("Usuário não encontrado");
    }
}
