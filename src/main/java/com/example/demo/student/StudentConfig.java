package com.example.demo.student;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Configuration
public class StudentConfig {

    @Bean
    CommandLineRunner commandLineRunner(StudentRepository studentRepository) {
        return args -> {
            Student mariam = new Student(
                    "Mariam",
                    "mariam@mail.com",
                    LocalDate.of(2000, Month.SEPTEMBER, 9)
            );
            Student bob = new Student(
                    "Bob",
                    "Bob@mail.com",
                    LocalDate.of(2004, Month.SEPTEMBER, 9)
            );

            studentRepository.saveAll(List.of(bob, mariam));
        };
    }
}
