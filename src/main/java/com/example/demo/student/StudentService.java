package com.example.demo.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Component
public class StudentService {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository){
        this.studentRepository = studentRepository;
    }

    public List<Student> getStudents(){
        return studentRepository.findAll();
    }

    public void addNewStudent(Student student) {
        Optional<Student> studentOptional = studentRepository.findStudentByEmail(student.getEmail());

        if (studentOptional.isPresent()){
            throw new IllegalStateException("email taken");
        }else {
            studentRepository.save(student);
            System.out.println("Student " + student + " was added");
        }
    }


    public void deleteStudent(Long id) {
        if(!studentRepository.existsById(id)){
            throw new IllegalStateException("Student with id " + id + " does not exist");
        }else {
            studentRepository.deleteById(id);
        }
    }

    @Transactional
    public void updateEmail(Long id, String email, String name) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("Student with id "+id+" does not exist"));

        if (name != null &&
                name.length() > 0 &&
                !Objects.equals(student.getName(), name)){
            student.setName(name);
        }

        if ( email != null &&
                email.length() > 0 &&
                !Objects.equals(student.getEmail(), email)){
            Optional<Student> studentOptional = studentRepository.findStudentByEmail(email);
            if (studentOptional.isPresent()){
                throw new IllegalStateException("email taken");
            }
            student.setEmail(email);
        }
    }
}
