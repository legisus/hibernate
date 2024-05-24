package com.codesoft.edu;

import com.codesoft.edu.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ToDoListApplication implements CommandLineRunner {

    @Autowired
    TaskService taskService;


    public static void main(String[] args) {
        SpringApplication.run(ToDoListApplication.class, args);
    }

    @Override
    public void run(String... args) {
        System.out.println("Running Spring Boot Application");

        System.out.println(taskService.getByTodoId(7L));




    }
}

