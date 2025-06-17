package com.examportal;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.examportal.model.User;
import com.examportal.repository.UserRepository;

@SpringBootApplication
public class ExamPortalApplication {

    public static void main(String[] args) {
        SpringApplication.run(ExamPortalApplication.class, args);
        System.out.println("Exam Portal Application is running...");
        System.out.println("Welcome to the Online Exam Portal!");
        System.out.println("You can register, login, and take exams.");

    }
}
