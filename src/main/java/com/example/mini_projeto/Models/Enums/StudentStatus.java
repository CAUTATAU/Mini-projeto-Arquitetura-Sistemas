package com.example.mini_projeto.Models.Enums;

public enum StudentStatus {
    Ativo("Ativo"),
    Trancado("Trancado");
    private String status;
    StudentStatus(String status) {
        this.status = status;
    }
}
