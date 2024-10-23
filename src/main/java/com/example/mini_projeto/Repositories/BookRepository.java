package com.example.mini_projeto.Repositories;

import com.example.mini_projeto.Models.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
