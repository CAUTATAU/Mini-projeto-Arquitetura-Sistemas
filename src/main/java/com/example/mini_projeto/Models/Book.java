package com.example.mini_projeto.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Book {
    @Id
    long id;
    @Column(name = "titulo")
    String titulo;
    @Column(name = "autor")
    String autor;
    @Column(name = "ano")
    int ano;
    @Column(name = "status")
    String status;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;
}
