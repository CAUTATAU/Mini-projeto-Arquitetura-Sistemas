package com.example.mini_projeto.Models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
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
    /*@Column(name = "status")
    String status;*/
}
