package com.example.mini_projeto.Services;


import com.example.mini_projeto.Models.Student;
import com.example.mini_projeto.Models.Subject;
import com.example.mini_projeto.Repositories.StudentRepository;
import com.example.mini_projeto.Services.Interface.ModelsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
@Service
public class StudentService implements ModelsService {
    @Autowired
    ExternalAPIService externalAPIService;

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    ModelsFilter modelFilter;


    @Override
    public List<Student> getAll() {
        List<Student> students = externalAPIService.getStudents();
        List<Student> historyStudents = new ArrayList<Student>();
        for(Student student : students){
            if(modelFilter.checkIfIsHistoryStudentAndPresencial(student)){
                historyStudents.add(student);
            }
        }
        return historyStudents;
    }

    @Override
    public void syncWithDatabase() {
        List<Student> students = getAll();
        for(Student student : students){
            if(studentRepository.findById(student.getId()).isEmpty()){
                studentRepository.save(student);
            }
        }
    }

    public List<Student> getAllStudents(){
        this.syncWithDatabase();
        return studentRepository.findAll();
    }

    public Student getStudentById(long id){
        return studentRepository.findById(id).orElse(null);
    }

    public Student getStudentByName(String nome) {
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
