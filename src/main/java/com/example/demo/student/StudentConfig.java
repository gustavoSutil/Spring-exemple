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
    CommandLineRunner commandLineRunner(StudentRepository repository){
        return args -> {
            Student mari = new Student("mari","email", LocalDate.of(2002, Month.NOVEMBER,1));
            Student alex =  new Student("alex","email", LocalDate.of(2000, Month.NOVEMBER,1));
            repository.saveAll(List.of(mari,alex));
        };
    }
}
