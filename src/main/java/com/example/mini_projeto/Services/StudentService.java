package com.example.mini_projeto.Services;


import com.example.mini_projeto.DTOs.Factories.ListFactory;
import com.example.mini_projeto.DTOs.SubjectDTO;
import com.example.mini_projeto.Models.Student;
import com.example.mini_projeto.Models.Subject;
import com.example.mini_projeto.Repositories.StudentRepository;
import com.example.mini_projeto.Services.Interface.ModelsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
@Service
public class StudentService implements ModelsService<Student> {
    @Autowired
    ExternalAPIService externalAPIService;

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    ModelsFilter modelFilter;

    @Autowired
    ListFactory<Student> studentListFactory;


    @Override
    public List<Student> getAPI() {
        List<Student> students = externalAPIService.getStudents();
        List<Student> historyStudents = studentListFactory.createNewList();
        for(Student student : students){
            if(modelFilter.checkIfIsHistoryStudentAndPresencial(student)){
                historyStudents.add(student);
            }
        }
        return historyStudents;
    }

    @Override
    public void syncWithDatabase() {
        List<Student> students = getAPI();
        for(Student student : students){
            if(studentRepository.findById(student.getId()).isEmpty()){
                studentRepository.save(student);
            }
        }
    }

    @Override
    public List<Student> getAll() {
        this.syncWithDatabase();
        return studentRepository.findAll();
    }

    @Override
    public Student getById(long id){
        return studentRepository.findById(id).orElse(null);
    }

    @Override
    public Student getByName(String nome) {
        return studentRepository.findByNome(nome);
    }


    public List<Subject> getSubjectsByStudentId(long studentId) {
        // Recupera o estudante pelo ID
        Student student = studentRepository.findById(studentId).orElse(null);
        if (student == null) {
            throw new RuntimeException("Estudante não encontrado");
        }

        // Retorna as disciplinas nas quais o estudante está matriculado
        return new ArrayList<>(student.getSubjects());
    }



}
