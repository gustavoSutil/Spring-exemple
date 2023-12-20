package com.example.demo.student;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class StudentService {
    private StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getStudents(){
        return studentRepository.findAll();
    }

    public void addNewStudent(Student student) {
        Optional<Student> studentByEmail = studentRepository.findStudentByEmail(student.getEmail());
        if (studentByEmail.isPresent()){
            throw new IllegalStateException("O email já existe");
        }else{
            studentRepository.save(student);
        }
    }

    public void deleteStudent(Long studentId){
        boolean exist = studentRepository.existsById(studentId);
        if(!exist){
            throw new IllegalStateException("Id não encontrado");
        }else{
            studentRepository.deleteById(studentId);
        }
    }

    @Transactional
    public void updateStudent(Long studentId,String name, String email) {
        Student student = studentRepository.findById(studentId).orElseThrow(() -> new IllegalStateException("asas"));
        if(!Objects.equals(student.getName(),name)){
            throw new IllegalStateException("Nome igual");
        }else if (!Objects.equals(student.getEmail(),studentRepository.findStudentByEmail(email))){
            throw new IllegalStateException("O email já existe");
        }else{
            student.setEmail(email);
            if (name!="" && name!=null){
                student.setName(name);
            }
        }
    }
}
