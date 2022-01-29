package com.example.demo.student;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Component
public class StudentService {

    public List<Student> getStudents(){
        return List.of(
                new Student(
                        1l,
                        "Mari",
                        "mari@mail.com",
                        LocalDate.of(2000, Month.DECEMBER, 29),
                        23
                )
        );
    }
}
