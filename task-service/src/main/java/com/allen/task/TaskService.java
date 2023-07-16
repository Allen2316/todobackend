package com.allen.task;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication
public class TaskService {

	public static void main(String[] args) {
		SpringApplication.run(TaskService.class, args);
	}

}
