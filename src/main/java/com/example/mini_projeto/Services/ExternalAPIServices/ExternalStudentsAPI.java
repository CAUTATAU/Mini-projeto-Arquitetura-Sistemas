package com.example.mini_projeto.Services.ExternalAPIServices;

import com.example.mini_projeto.Models.Student;
import com.example.mini_projeto.Services.Interface.ExternalAPI;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
public class ExternalStudentsAPI implements ExternalAPI<Student> {
    RestTemplate restTemplate = new RestTemplate();

    @Override
    public List<Student> getAll() {
        ResponseEntity<List<Student>> response = restTemplate.exchange(
                "https://rmi6vdpsq8.execute-api.us-east-2.amazonaws.com/msAluno",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Student>>() {}
        );
        return response.getBody();
    }
}
