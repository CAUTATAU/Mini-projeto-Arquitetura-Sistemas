package com.example.mini_projeto.DTOs.Factories;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ListFactoryImp<T> implements ListFactory<T> {
    @Override
    public List<T> createNewList() {
        return new ArrayList<T>();
    }
}
