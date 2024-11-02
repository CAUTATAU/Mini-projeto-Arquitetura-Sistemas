package com.example.mini_projeto.Services.ExternalAPIServices;

import com.example.mini_projeto.Models.Book;
import com.example.mini_projeto.Services.Interface.ExternalAPI;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
public class ExternalBooksAPI implements ExternalAPI<Book> {
    RestTemplate restTemplate = new RestTemplate();
    @Override
    public List<Book> getAll() {
        ResponseEntity<List<Book>> response = restTemplate.exchange(
                "https://qiiw8bgxka.execute-api.us-east-2.amazonaws.com/acervo/biblioteca",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Book>>() {}
        );
        return response.getBody();
    }
}
