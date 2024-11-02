package com.example.mini_projeto.Services;

import com.example.mini_projeto.Models.Book;
import com.example.mini_projeto.Models.Subject;
import com.example.mini_projeto.Repositories.BookRepository;
import com.example.mini_projeto.Services.Interface.ModelsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService implements ModelsService<Book> {

    @Autowired
    ExternalAPIService externalAPIService;
    @Autowired
    BookRepository bookRepository;

    @Override
    public List<Book> getAPI() {
        return externalAPIService.getBooks();
    }

    @Override
    public void syncWithDatabase() {
        List<Book> books = this.getAPI();
        for(Book book : books){
            if(bookRepository.findById(book.getId()).isEmpty()){
                bookRepository.save(book);
            }
        }
    }

    @Override
    public List<Book> getAll() {
        this.syncWithDatabase();
        return bookRepository.findAll();
    }



    @Override
    public Book getById(long id) {
        return null;
    }

    @Override
    public Book getByName(String nome) {
        return null;
    }

    @Override
    public List<Subject> getSubjectsByStudentId(long studentId) {
        return null;
    }

    public List<Book> getAllBooks(){
        this.syncWithDatabase();
        return bookRepository.findAll();
    }

}
