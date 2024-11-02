package com.example.mini_projeto.Services.ExternalAPIServices;

import com.example.mini_projeto.Models.Subject;
import com.example.mini_projeto.Services.Interface.ExternalAPI;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
public class ExternalSubjectsAPI implements ExternalAPI<Subject> {
    RestTemplate restTemplate = new RestTemplate();
    @Override
    public List<Subject> getAll() {
        ResponseEntity<List<Subject>> response = restTemplate.exchange(
                "https://sswfuybfs8.execute-api.us-east-2.amazonaws.com/disciplinaServico/msDisciplina",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Subject>>() {}
        );
        return response.getBody();
    }
}
