package com.example.mini_projeto.Models.Enums;

public enum StudentModality {
    Presencial("Presencial"),
    EAD("EAD");
    private String modality;
    StudentModality(String modality) {
        this.modality = modality;
    }
}
