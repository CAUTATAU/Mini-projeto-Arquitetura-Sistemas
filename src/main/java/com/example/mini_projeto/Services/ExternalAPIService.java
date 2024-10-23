package com.example.mini_projeto.Services;

import com.example.mini_projeto.Models.Book;
import com.example.mini_projeto.Models.Student;
import com.example.mini_projeto.Models.Subject;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class ExternalAPIService {
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

    public List<Subject> getSubjects() {
        ResponseEntity<List<Subject>> response = restTemplate.exchange(
                "https://sswfuybfs8.execute-api.us-east-2.amazonaws.com/disciplinaServico/msDisciplina",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Subject>>() {}
        );
        return response.getBody();
    }

    public List<Book> getBooks() {
        ResponseEntity<List<Book>> response = restTemplate.exchange(
                "https://qiiw8bgxka.execute-api.us-east-2.amazonaws.com/acervo/biblioteca",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Book>>() {}
        );
        return response.getBody();
    }
}
