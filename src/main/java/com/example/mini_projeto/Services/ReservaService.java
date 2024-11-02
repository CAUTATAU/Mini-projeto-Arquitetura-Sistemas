package com.example.mini_projeto.Services;

import com.example.mini_projeto.DTOs.ReservaDTO;
import com.example.mini_projeto.Models.Book;
import com.example.mini_projeto.Models.Student;
import com.example.mini_projeto.Repositories.BookRepository;
import com.example.mini_projeto.Repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReservaService {
    @Autowired
    StudentRepository studentRepository;
    @Autowired
    BookRepository bookRepository;

    public void reserveBook(ReservaDTO data) {

        Student student = studentRepository.findById(data.studentId()).orElse(null);
        if (student == null) {
            throw new RuntimeException("Estudante não encontrado");
        }

        Book book = bookRepository.findByTitulo(data.bookTitle());
        if (book == null) {
            throw new RuntimeException("Livro não encontrado");
        }
        if(!book.getStatus().equals("Disponível")){
            throw new RuntimeException("Livro não disponível para reserva");
        }

        student.getBooks().add(book);
        book.setStudent(student);
        book.setStatus("Reservado");
        studentRepository.save(student);
        bookRepository.save(book);
    }

    public void cancelBookReservation(ReservaDTO data) {

        Student student = studentRepository.findById(data.studentId()).orElse(null);
        if (student == null) {
            throw new RuntimeException("Estudante não encontrado");
        }

        Book book = bookRepository.findByTitulo(data.bookTitle());
        if (book == null) {
            throw new RuntimeException("Livro não encontrado");
        }

        if(!student.getBooks().contains(book)){
            throw new RuntimeException("Estudante não possui este livro reservado");
        }

        student.getBooks().remove(book);
        book.setStudent(null);
        book.setStatus("Disponível");
        studentRepository.save(student);
        bookRepository.save(book);
    }
}
