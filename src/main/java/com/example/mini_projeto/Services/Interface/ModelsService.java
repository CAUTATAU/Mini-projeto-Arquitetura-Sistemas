package com.example.mini_projeto.Services.Interface;

import java.util.List;

public interface ModelsService<T> {
     List<T> getAll();
     void syncWithDatabase();
}
